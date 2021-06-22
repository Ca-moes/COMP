#!/usr/local/bin/ruby
require 'fileutils'

def grammars(*defs)
  grammars = []
  defs.each do |hash|
    grammars << GrammarDef.new do |g|
      g.name = hash[:name]
      g.parser = hash[:parser]
      g.test_file = hash[:test_file]
      g.start_symbol = hash[:start_symbol]
      g.support = hash[:support] || {}
    end
  end  
  grammars
end

def specs(*defs)
  specs = []
  defs.each do |hash|
    specs << BenchmarkSpec.new do |b|
      b.opt = hash[:opt]
    end
  end  
  specs
end

class BenchmarkSpec
  attr_accessor :opt, :res
  def initialize(&block)
    @res = {}
    yield self
  end
end

class GrammarDef
  attr_accessor :name, :test_file, :parser, :start_symbol, :support
  def initialize(&block)
    yield self
  end
end

def run(spec, g)
  [spec.opt, "no#{spec.opt}"].each do |opt|
    #puts "Running #{opt} on #{g.name}"
    FileUtils.rm_f(Dir.glob("*.{java,class}"))
    if opt == "static"
      cmd = "javacc -#{opt} #{g.name}"
    else
      cmd = "javacc -nostatic -#{opt} #{g.name}"
    end
    `#{cmd}`
    g.support.each do |f|
      FileUtils.cp(f + ".java.bkp", f + ".java") 
    end
static_version = <<-EOS1
      try {
        if (!initted) {
          new $parser(new java.io.FileInputStream("$test_file"));
          initted = true;
        }
        $parser.ReInit(new java.io.FileInputStream("$test_file"));
        $parser.$start_symbol();
      } catch (Exception e) {
        e.printStackTrace();
        return;
      }
EOS1
no_static_version = <<-EOS2
      try {
        $parser parser = new $parser(new java.io.FileInputStream("$test_file"));
        parser.$start_symbol();
        rpts.add(parser);
      } catch (Exception e) {
        e.printStackTrace();
        return;
      }
EOS2
runner = <<-EOS3
import java.util.*;
public class Runner {
  static List rpts = new ArrayList();
  public static void main(String args[]) {
    long[] times = new long[10];
    boolean initted = false;
    for (int j = 0; j<10; j++) {
      long start = System.currentTimeMillis();
      $creation
      long end = System.currentTimeMillis();
      times[j] = end-start;
    }
    StringBuffer res = new StringBuffer();
    for (int i=0; i<10; i++) {
      res.append(String.valueOf(times[i]) + ",");
    }
    System.out.println(res);
  }
}
EOS3
    File.open("Runner.java", "w") do |f|
      if opt == "static"
        runner.gsub!(/\$creation/, static_version.gsub(/\$parser/, g.parser).gsub(/\$test_file/, g.test_file).gsub(/\$start_symbol/, g.start_symbol))
      else
        runner.gsub!(/\$creation/, no_static_version.gsub(/\$parser/, g.parser).gsub(/\$test_file/, g.test_file).gsub(/\$start_symbol/, g.start_symbol))
      end
      runner = runner.gsub(/\$parser/, g.parser).gsub(/\$test_file/, g.test_file).gsub(/\$start_symbol/, g.start_symbol)
      f.syswrite(runner)
    end
    `javac *.java`
    spec.res[opt] = `java Runner`.chop.split(",").collect {|x| x.to_i}.sort.slice(2,6)
  end
end


g = grammars(
  {:name => "slow.jj", :test_file => "java_data.dat", :parser => "JavaParser", :start_symbol => "CompilationUnit"}
#  {:name => "Java1.5.jj", :test_file => "java_data.dat", :parser => "JavaParser", :start_symbol => "CompilationUnit", :support => %w{Token}},
#  {:name => "c.jj", :test_file => "c_data.dat", :parser => "CParser", :start_symbol => "TranslationUnit"},
#  {:name => "idl.jj", :test_file => "idl_data.dat", :parser => "IDLParser", :start_symbol => "specification"} 
            )
s = specs(
          {}
#          {:opt => "static"},
#          {:opt => "cache_tokens"}, 
#          {:opt => "keep_line_column"}
#          {:opt => "lookahead:60"}
          
         )

s.each do |spec|
  g.each do |grammar| 
    run(spec, grammar) 
    spec.res.keys.each do |k|
      puts "Grammar: #{grammar.name}"
      puts "Option: #{k}"
      puts "Slowest: #{spec.res[k].sort.last}"
      puts "Fastest: #{spec.res[k].sort.first}"
      sum = 0
      spec.res[k].collect {|x| sum += x }
      puts "Average:#{spec.res[k].join(',')}: #{sum}/8 = #{sum.to_f/8.0}"
      puts "==========================="
    end
    spec.res.clear
  end
end

