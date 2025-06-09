#!/bin/sh

# This script is a wrapper for Maven that ensures the correct version of Maven is used.
# It will download Maven if it's not already present.

# Check if Maven wrapper exists
if [ ! -f "mvnw" ]; then
    echo "Downloading Maven wrapper..."
    curl -O https://raw.githubusercontent.com/takari/maven-wrapper/master/maven-wrapper.sh
    chmod +x mvnw
fi

# Execute Maven with the specified arguments
./mvnw "$@"
