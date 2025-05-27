#!/usr/bin/env bash
# wait-for-it.sh

host="$1"
port="$2"
shift 2

until nc -z "$host" "$port"; do
  echo "Aguardando $host:$port ficar disponível..."
  sleep 2
done

exec "$@"
