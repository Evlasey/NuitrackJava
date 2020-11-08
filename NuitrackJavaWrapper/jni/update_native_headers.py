from subprocess import check_output
from os import path, getenv
import glob

SCRIPT_DIR = path.dirname(path.realpath(__file__))
JAVA_COMPILER = path.join(getenv('JAVA_HOME'), 'bin/javac')

SOURCE_DIR = path.join(SCRIPT_DIR, '../src/main/java/')
SOURCE_PREFIX = 'NuitrackJavaWrapper/Native/'
SOURCE_SUFFIX = '*.java'

COMPILE_HEADERS_DIR = path.join(SCRIPT_DIR, 'include/')
COMPILE_CLASSES_DIR = path.join(SCRIPT_DIR, '../target/jni')

sources = glob.glob(path.join(SOURCE_DIR, path.join(SOURCE_PREFIX, SOURCE_SUFFIX)))

sources_line = " "
for file in sources:
    sources_line = sources_line +  ' "' + file + '"'

command = JAVA_COMPILER + ' -h "' + COMPILE_HEADERS_DIR + '" -d "' + COMPILE_CLASSES_DIR + '" --source-path "' + SOURCE_DIR + '"' + sources_line

check_output(command, shell=True).decode()