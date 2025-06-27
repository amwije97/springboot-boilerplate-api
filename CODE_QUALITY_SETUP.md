# Code Quality Setup Guide

This project uses industry-standard code quality tools to ensure consistent, maintainable, and high-quality code.

## 🛠️ Tools Overview

### 1. **Spotless** - Auto-Formatting
- **Purpose**: Automatically formats Java code, Gradle files, and other text files
- **Configuration**: Google Java Format (AOSP variant) with custom rules
- **Features**:
  - Removes unused imports
  - Organizes imports
  - Formats annotations
  - Trims trailing whitespace
  - Ensures files end with newlines

### 2. **Checkstyle** - Code Linting
- **Purpose**: Enforces coding standards and identifies potential issues
- **Configuration**: Comprehensive ruleset based on industry best practices
- **Features**:
  - Naming conventions
  - Code structure validation
  - Javadoc requirements (public APIs)
  - Security checks
  - Spring Boot specific rules

### 3. **Lefthook** - Git Hooks
- **Purpose**: Automated quality checks before commits/pushes
- **Features**:
  - Pre-commit: Format check, linting, unit tests
  - Pre-push: Full test suite, build verification
  - Commit message validation (Conventional Commits)
  - Branch naming conventions

## 🚀 Quick Setup

### Prerequisites
- Java 24
- Node.js and npm (for Lefthook)
- Git

### Installation

**Recommended (Gradle-based):**
```bash
# 1. Install Lefthook (one-time)
npm install -g @evilmartians/lefthook@latest

# 2. Setup Git hooks using Gradle task
./gradlew setupHooks
```

**Alternative Installation Methods:**
```bash
# macOS
brew install lefthook

# Windows (with Scoop)
scoop install lefthook

# Manual installation
npm install -g @evilmartians/lefthook@latest
lefthook install
```

## 📋 Available Commands

### Setup
```bash
# Setup/reinstall Git hooks
./gradlew setupHooks
```

### Formatting (Spotless)
```bash
# Check formatting
./gradlew spotlessCheck

# Auto-fix formatting issues
./gradlew spotlessApply

# Format only Java files
./gradlew spotlessJavaApply
```

### Linting (Checkstyle)
```bash
# Check main source code
./gradlew checkstyleMain

# Check test code
./gradlew checkstyleTest

# Check all code
./gradlew checkstyle
```

### Git Hooks (Lefthook)
```bash
# Run pre-commit hooks manually
lefthook run pre-commit

# Run pre-push hooks manually
lefthook run pre-push

# Skip hooks (emergency use only)
git commit --no-verify
git push --no-verify
```

### Combined Quality Checks
```bash
# Run all quality checks
./gradlew check

# Clean build with all checks
./gradlew clean build
```

## 🔧 Configuration Details

### Spotless Configuration
- **Line Length**: 100 characters (Google Java Format default)
- **Indentation**: 4 spaces (Java), tabs (Gradle)
- **Import Order**: java, javax, org, com, others
- **Format**: Google Java Format (AOSP variant)

### Checkstyle Rules
- **Line Length**: 100 characters (aligned with Spotless/Google Java Format)
- **Method Length**: Max 50 lines
- **Parameters**: Max 7 parameters
- **Javadoc**: Required for public APIs
- **Naming**: Standard Java conventions

### Git Hook Triggers

**Pre-commit** (runs sequentially on every commit):
- 1️⃣ **Auto-format code** with Spotless
- 2️⃣ **Verify formatting** is correct
- 3️⃣ **Checkstyle linting** checks
- 4️⃣ **Unit tests** execution
- 5️⃣ **Security vulnerability** scan
- ⚠️ **TODO/FIXME comment** warnings (non-blocking)

**Pre-push** (runs on every push):
- ✅ Full test suite
- ✅ Complete build
- ✅ Static analysis
- ✅ Branch naming validation

**Commit-msg** (validates commit message):
- ✅ Conventional Commits format
- ✅ Proper structure and length

## 📝 Branch Naming Conventions

Follow this pattern for branch names:
```
feature/description-here
bugfix/issue-description
hotfix/critical-fix
release/version-number
chore/maintenance-task
```

**Examples:**
- `feature/user-authentication`
- `bugfix/login-validation-error`
- `hotfix/security-vulnerability`

## 💬 Commit Message Format

Use [Conventional Commits](https://www.conventionalcommits.org/) format:

```
type(scope): description

Examples:
feat(auth): add user login functionality
fix(api): resolve null pointer exception in user service
docs(readme): update installation instructions
test(user): add integration tests for user service
```

**Types:**
- `feat`: New features
- `fix`: Bug fixes
- `docs`: Documentation changes
- `style`: Code formatting (no logic changes)
- `refactor`: Code restructuring
- `test`: Adding/updating tests
- `chore`: Maintenance tasks
- `perf`: Performance improvements
- `ci`: CI/CD changes
- `build`: Build system changes

## 🔍 IDE Integration

### IntelliJ IDEA
1. Install the Checkstyle plugin
2. Configure Checkstyle: Settings → Tools → Checkstyle
3. Add configuration file: `config/checkstyle/checkstyle.xml`
4. Install the Spotless plugin for formatting

### VS Code
1. Install the Checkstyle extension
2. Install the Java formatting extensions
3. Configure workspace settings for Spotless integration

## 🚨 Troubleshooting

### Common Issues

**Spotless formatting failures:**
```bash
# Fix formatting issues automatically
./gradlew spotlessApply
```

**Checkstyle violations:**
- Review the console output for specific violations
- Fix issues manually or suppress specific rules if needed
- Check `config/checkstyle/checkstyle-suppressions.xml` for examples

**Git hooks not running:**
```bash
# Reinstall hooks using Gradle task
./gradlew setupHooks

# Or manually reinstall
lefthook install

# Check hook status
lefthook version
```

**Setup issues:**
```bash
# If Lefthook is not installed
npm install -g @evilmartians/lefthook@latest

# Or use alternative package managers
brew install lefthook          # macOS
scoop install lefthook         # Windows

# Then setup hooks
./gradlew setupHooks
```

**Performance issues:**
- Pre-commit hooks now run sequentially for better reliability
- Use `--no-daemon` flag for Gradle in CI environments
- Consider skipping hooks in emergency situations with `--no-verify`

### Disabling Checks (Use Sparingly)

**Skip specific Checkstyle rules:**
```java
// Suppress specific warnings
@SuppressWarnings("checkstyle:MagicNumber")
private static final int MAX_RETRIES = 3;
```

**Skip Spotless for specific files:**
Add to `build.gradle`:
```gradle
spotless {
    java {
        targetExclude '**/generated/**'
    }
}
```

## 📊 Quality Metrics

The setup enforces these quality standards:
- **Code Coverage**: Run tests to ensure adequate coverage
- **Complexity**: Checkstyle rules limit method complexity
- **Maintainability**: Consistent formatting and naming
- **Security**: Dependency vulnerability scanning
- **Documentation**: Javadoc requirements for public APIs

## 🤝 Contributing

1. Follow the established coding standards
2. Ensure all quality checks pass before committing
3. Write meaningful commit messages
4. Add tests for new functionality
5. Update documentation as needed

## 📚 Additional Resources

- [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
- [Checkstyle Documentation](https://checkstyle.org/)
- [Spotless Documentation](https://github.com/diffplug/spotless)
- [Lefthook Documentation](https://github.com/evilmartians/lefthook)
- [Conventional Commits](https://www.conventionalcommits.org/)

---

*This setup ensures consistent, high-quality code across the entire development team. 🎯* 