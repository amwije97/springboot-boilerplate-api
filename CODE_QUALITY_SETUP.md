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

# Windows (with Scoop) - Recommended for Windows
scoop install lefthook

# Windows (with Chocolatey)
choco install lefthook

# Windows (with Winget)
winget install evilmartians.lefthook

# Manual installation
npm install -g @evilmartians/lefthook@latest
lefthook install
```

**⚠️ Windows Users with nvm4w:**
If you're using nvm4w (Node Version Manager for Windows), npm installs lefthook as `lefthook.cmd` and `lefthook.ps1` files. Git hooks may not find these automatically. The project includes a fix for this - just run `./gradlew setupHooks` and it will handle the PATH issue automatically.

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
scoop install lefthook         # Windows (recommended)
choco install lefthook         # Windows (alternative)
winget install evilmartians.lefthook  # Windows (alternative)

# Then setup hooks
./gradlew setupHooks
```

**Windows-specific issues:**

**nvm4w users getting "Can't find lefthook in PATH":**
```bash
# Check which files were installed
dir C:\nvm4w\nodejs\lefthook*

# You should see: lefthook, lefthook.cmd, lefthook.ps1
# The project's Gradle setupHooks task automatically handles this

# Verify the fix worked
./gradlew setupHooks
git commit -m "test: verify hooks are working"
```

**Alternative Windows solutions if PATH issues persist:**
```bash
# Option 1: Add npm global bin to Windows PATH
# 1. Run: npm config get prefix
# 2. Add that directory to Windows PATH environment variable
# 3. Restart terminal

# Option 2: Use full path in environment variable
set LEFTHOOK_BIN=C:\nvm4w\nodejs\lefthook.cmd

# Option 3: Install via package manager instead of npm
scoop install lefthook
```

**Performance issues:**
- Pre-commit hooks now run sequentially for better reliability
- Use `--no-daemon` flag for Gradle in CI environments
- Consider skipping hooks in emergency situations with `--no-verify`

**Windows + nvm4w specific notes:**
- The project automatically detects Windows with nvm4w and uses the correct `lefthook.cmd` path
- If you see "Can't find lefthook in PATH" errors, run `./gradlew setupHooks` to apply the fix
- Git hooks have been patched to use the full path: `C:/nvm4w/nodejs/lefthook.cmd`
- This ensures compatibility regardless of Windows PATH configuration

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

## 🔧 Technical Implementation Notes

### Windows Compatibility Fix
The project includes automatic handling for Windows users with nvm4w:

**Issue**: npm with nvm4w installs lefthook as:
- `lefthook` (Unix shell script)
- `lefthook.cmd` (Windows batch file)
- `lefthook.ps1` (PowerShell script)

Git hooks look for `lefthook.exe` and `lefthook.bat` but not `lefthook.cmd`.

**Solution**: The setupHooks Gradle task and Git hooks are configured to use the full path to `lefthook.cmd` on Windows systems, ensuring reliable execution regardless of PATH configuration.

### Configuration Files
- **lefthook.yml**: min_version set to 1.11.14 for latest features
- **build.gradle**: setupHooks task with Windows detection
- **.git/hooks/**: Patched hook files with full path resolution

## 📚 Additional Resources

- [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
- [Checkstyle Documentation](https://checkstyle.org/)
- [Spotless Documentation](https://github.com/diffplug/spotless)
- [Lefthook Documentation](https://github.com/evilmartians/lefthook)
- [Conventional Commits](https://www.conventionalcommits.org/)
- [nvm4w (Node Version Manager for Windows)](https://github.com/coreybutler/nvm-windows)

---

*This setup ensures consistent, high-quality code across the entire development team. 🎯*
