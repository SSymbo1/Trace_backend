#!/bin/sh

# 获取脚本所在的目录
SCRIPT_PATH=$(cd "$(dirname "$0")" && pwd)

# 假设jar包的名字是trace_backend-0.0.1-SNAPSHOT.jar，并且与脚本在同一个目录下
JAR_FILE="$SCRIPT_PATH/trace_backend 0.0.1-SNAPSHOT.jar"

# 检查jar文件是否存在
if [ ! -f "$JAR_FILE" ]; then
    echo "Error: Unable to find JAR file '$JAR_FILE'." >&2
    exit 1
fi

# 使用java命令来启动jar包
echo "Starting Spring Boot application..."
java -jar "$JAR_FILE"
