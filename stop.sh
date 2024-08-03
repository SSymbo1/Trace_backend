#!/bin/sh

# 获取脚本所在的目录
SCRIPT_PATH=$(cd "$(dirname "$0")" && pwd)

# 假设jar包的名字是trace_backend-0.0.1-SNAPSHOT.jar，并且与脚本在同一个目录下
JAR_FILE="$SCRIPT_PATH/trace_backend 0.0.1-SNAPSHOT.jar"

# 使用pgrep查找与jar文件相关的Java进程ID
PID=$(pgrep -f "java -jar $JAR_FILE")

# 检查是否找到了进程ID
if [ -z "$PID" ]; then
    echo "Error: No running process found for $JAR_FILE." >&2
    exit 1
fi

# 使用kill命令停止进程
echo "Stopping Spring Boot application with PID $PID..."
kill "$PID"

sleep 2

if kill -0 "$PID" 2>/dev/null; then
    echo "Error: Process $PID is still running." >&2
    exit 1
else
    echo "Spring Boot application stopped successfully."
fi
