# 130-springboot3-dev-repo
SpringBoot 3

## About Maven Structure

This top level should be as simple as possible. ONLY purpose is to run mvn commands and build all submodules together.

- This is a parent pom file, but only for mvn together.
- If there are multiple modules need to be under a common parent, then create a parent pom file separately.
- There can be multiple parent pom files.