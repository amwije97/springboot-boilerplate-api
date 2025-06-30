# Spring Boot Boilerplate API

## 🚀 Project Description

This is a production-ready **Spring Boot boilerplate application** designed to serve as a robust starting point for building modern REST APIs. It comes pre-configured with essential development tools, code quality standards, and best practices to accelerate your Spring Boot project development.

### ✨ What's Included

- **Spring Boot 3.x** - Latest stable version with modern Java features
- **RESTful API Structure** - Well-organized controller, service, and DTO layers
- **Code Quality Tools** - Checkstyle, Spotless formatting, and comprehensive git hooks
- **Testing Framework** - Unit tests, integration tests, and test configuration
- **Exception Handling** - Global exception handler with standardized API responses
- **CORS Configuration** - Pre-configured for web application integration
- **Gradle Build System** - Efficient dependency management and build automation

### 🎯 Perfect For

- Starting new Spring Boot microservices
- Building REST APIs for web and mobile applications
- Learning Spring Boot best practices and project structure
- Creating proof-of-concepts and MVPs
- Enterprise-grade application development

This boilerplate eliminates the initial setup complexity and provides a solid foundation that follows industry standards and Spring Boot best practices.

## 🛠️ Local Setup

### Prerequisites

Before setting up the project locally, ensure you have the following installed:

- **Java 24** - Required for this Spring Boot 3.5.3 application
- **Git** - For cloning the repository

> **Note:** The project includes the Gradle Wrapper, so you don't need to install Gradle separately.

### Setup Instructions

1. **Create New Repository from Template**
   
   Click the **"New"** button at the top of this repository page, then:
   - Choose a template as "springboot_boilerplate_api" in the Repository template section
   - Choose a repository name for your new project
   - Select public or private visibility
   - Click **"Create repository"**
   
   This will create a clean copy without git history.

2. **Clone Your New Repository**
   ```bash
   git clone <your-repository-url>
   cd your-new-project-name
   ```

3. **Transform for Your Project** *(Optional - Skip if using as-is)*
   
   If you want to customize this boilerplate for your own project:
   
   ```bash
   # 1. Update the configuration variables in the script
   nano scripts/rename-project.sh
   
   # 2. Run the transformation script
   chmod +x scripts/rename-project.sh
   ./scripts/rename-project.sh
   ```
   
   The script will automatically:
   - Update project name and package structure
   - Rename the main application class
   - Move all Java files to new package locations
   - Update all imports and package declarations
   
   > 📝 **Note**: Update the variables at the top of `scripts/rename-project.sh` before running.

4. **Build and Setup the Project**
   ```bash
   ./gradlew clean build
   ```
   
   This command will:
   - Download all dependencies
   - Install git hooks for code quality
   - Run code quality checks (Checkstyle)
   - Execute all tests
   - Create the application JAR file

5. **Run the Application**
   ```bash
   ./gradlew bootRun
   ```
   
   The application will start on `http://localhost:8080`

6. **Verify the Setup**
   
   Test the sample endpoint:
   ```bash
   curl http://localhost:8080/api/hello
   ```
   
   You should receive a JSON response from the Hello controller.

7. **Clean Up Template Files**
   
   Once everything is working, remove the template-specific files:
   ```bash
   # Remove the scripts folder (no longer needed)
   rm -rf scripts/
   
   # Update this README for your project
   # Replace the content with your project's documentation
   ```
   
   > 📝 **Important**: Update this README.md file to document your specific project, API endpoints, and requirements.

### Development Commands

- **Run tests only:** `./gradlew test`
- **Check code quality:** `./gradlew checkstyleMain checkstyleTest`
- **Build without tests:** `./gradlew build -x test`

> **Windows Users:** Use `gradlew.bat` instead of `./gradlew` for all commands.

## 🏗️ Project Structure

This boilerplate follows a **domain-driven design** approach with clear separation of concerns. Here's how the project is organized:

```
src
├── main
│   ├── java
│   │   └── com
│   │       └── example
│   │           └── springboot_boilerplate_api
│   │               ├── SpringbootBoilerplateApiApplication.java
│   │               ├── config/                       # Configuration classes (Security, CORS, Swagger, etc.)
│   │               ├── common/                       # Shared utilities, exceptions, DTOs, constants
│   │               ├── modules/                      # Grouped by business feature/domain
│   │               │   └── <module-name>/
│   │               │       ├── controller/           # REST controllers (API layer)
│   │               │       ├── service/              # Business logic
│   │               │       │    └── impl/            # Business logic for the service goes here
│   │               │       │    ├── <service-name>   # Interface for the service
│   │               │       ├── repository/           # Spring Data JPA Repositories
│   │               │       ├── model/                # Entity, DTO, Request/Response Models
│   │               │       └── mapper/               # MapStruct or manual mappers (optional)
│   │               └── security/                     # JWT, RBAC, UserDetailsService
│   └── resources
│       ├── application.yml                           # Main config
│       ├── application-dev.yml                       # Dev profile config
│       ├── application-prod.yml                      # Prod profile config
│
├── test
│   └── java
│       └── com.example.springboot_boilerplate_api
│           └── unit/                           # Unit tests
```

### 📁 Directory Guidelines

#### **config/**
Place all configuration classes here:
- `WebConfig.java` - CORS, MVC configuration
- `SecurityConfig.java` - Spring Security setup
- `SwaggerConfig.java` - API documentation
- `DatabaseConfig.java` - Database connection settings

#### **common/**
Shared components used across modules:
- `constants/` - Application-wide constants
- `dto/` - Common DTOs and API response models
- `exception/` - Global exception handlers and custom exceptions
- `util/` - Utility classes and helper methods

#### **modules/`<module-name>`/**
Organize features by business domain (e.g., `user/`, `product/`, `order/`):
- **`controller/`** - REST endpoints and request handling
- **`service/`** - Business logic interfaces
- **`service/impl/`** - Business logic implementations
- **`repository/`** - Data access layer (JPA repositories)
- **`model/`** - Entities, DTOs, request/response objects
- **`mapper/`** - Object mapping between layers

#### **security/**
Authentication and authorization components:
- JWT token handling
- User authentication services
- Role-based access control (RBAC)
- Custom security filters

#### **resources/**
Configuration files and static resources:
- **`application.yml`** - Default configuration
- **`application-dev.yml`** - Development environment settings
- **`application-prod.yml`** - Production environment settings

### 🎯 Best Practices

- **Keep modules independent** - Each module should be self-contained
- **Follow naming conventions** - Use clear, descriptive names for classes and packages
- **Separate concerns** - Keep controllers thin, put business logic in services
- **Use interfaces** - Define service contracts with interfaces
- **Group related functionality** - Organize code by business domain, not technical layer

## 📋 Git Guidelines

This project enforces strict Git workflows and quality standards through automated git hooks and repository settings. Follow these guidelines to ensure smooth collaboration.

### 🌳 Branching Strategy

We follow a **Feature Branch Workflow** with the following branch naming conventions:

#### **Branch Naming Pattern** *(Enforced by git hooks)*
```
type/description-in-kebab-case
```

**Allowed Types:**
- `feat/` or `feature/` - New features
- `fix/` or `bugfix/` - Bug fixes  
- `hotfix/` - Critical production fixes
- `docs/` - Documentation updates
- `test/` - Test-related changes
- `chore/` - Maintenance tasks
- `refactor/` - Code refactoring
- `patch/` - Minor fixes

**Examples:**
```bash
feat/user-authentication
feature/payment-integration
fix/login-validation-error
bugfix/null-pointer-exception
hotfix/critical-security-patch
docs/api-documentation-update
test/integration-test-coverage
chore/dependency-upgrades
refactor/service-layer-cleanup
```

#### **Protected Branches**
- `main` - Production-ready code
- `develop` - Integration branch for features
- `release-*` - Release preparation branches

> ⚠️ **Direct pushing to protected branches is prohibited**

### 💬 Commit Message Format *(Enforced by git hooks)*

We use **Conventional Commits** specification for consistent commit history:

#### **Format:**
```
type(scope): description

[optional body]

[optional footer(s)]
```

#### **Types:**
- `feat` - New feature
- `fix` - Bug fix
- `docs` - Documentation changes
- `style` - Code style changes (formatting, missing semi-colons, etc.)
- `refactor` - Code refactoring
- `test` - Adding or updating tests
- `chore` - Build process, dependency updates
- `perf` - Performance improvements
- `ci` - CI/CD configuration changes
- `build` - Build system changes
- `revert` - Reverting previous commits

#### **Examples:**
```bash
feat: add user login functionality
feat(auth): implement JWT token validation
fix: resolve null pointer exception in user service
fix(api): handle invalid request parameters
docs: update API documentation for user endpoints
chore: upgrade Spring Boot to version 3.2.0
```

### 🔄 Workflow Process

1. **Create Feature Branch**
   ```bash
   git checkout develop
   git pull origin develop
   git checkout -b feat/your-feature-name
   ```

2. **Make Changes & Commit**
   ```bash
   git add .
   git commit -m "feat: add user registration endpoint"
   ```

3. **Push & Create PR**
   ```bash
   git push origin feat/your-feature-name
   # Create Pull Request via GitHub/GitLab interface
   ```

4. **Code Review & Merge**
   - At least **1 approval required** from code reviewers
   - All automated checks must pass
   - Squash and merge to maintain clean history

### 🛡️ Repository Protection Rules

#### **Enforced Rules:**
- ✅ **Branch Protection** - No direct pushes to `main`, `develop`, `release-*`
- ✅ **Required Reviews** - Minimum 1 approving review for PRs
- ✅ **Status Checks** - All CI/CD checks must pass
- ✅ **Git Hooks** - Pre-commit, commit-msg, and pre-push validation
- ✅ **Up-to-date Branches** - PRs must be up-to-date before merging

#### **Pre-Push Validation *(Automated)***
Before any push, the following checks run automatically:
- 🧪 **All tests pass** (`./gradlew test`)
- 🔨 **Build succeeds** (`./gradlew build`)
- 🔍 **Code quality checks** (Checkstyle, Spotless)
- 🌳 **Branch naming validation**

### 📈 Additional Best Practices *(Recommended Setup)*

Consider implementing these industry-standard practices:

#### **Advanced Repository Settings:**
- **Require signed commits** - Enhanced security
- **Auto-delete head branches** - Clean up merged feature branches
- **Linear history** - Enforce rebase or squash merging
- **Restrict force pushes** - Prevent history rewriting
- **Require status checks to be up-to-date** - Ensure latest code integration

#### **PR Templates & Automation:**
- **Pull Request templates** - Standardize PR descriptions
- **Issue templates** - Consistent bug reports and feature requests
- **Automated labeling** - Auto-categorize PRs based on changes
- **Dependency updates** - Automated dependency bump PRs

#### **Release Management:**
- **Semantic versioning** - Automated version bumping
- **Release notes generation** - Auto-generate from conventional commits
- **Automated releases** - Deploy from tags
- **Hotfix workflow** - Fast-track critical fixes

#### **Code Quality Gates:**
- **Coverage thresholds** - Minimum test coverage requirements
- **Security scanning** - Automated vulnerability detection
- **Performance benchmarks** - Prevent performance regressions
- **Documentation checks** - Ensure API docs are updated

### 🚨 Troubleshooting

**Git hook failures:**
```bash
# If pre-commit fails
./gradlew spotlessApply checkstyleMain

# If commit message format is wrong
git commit --amend -m "feat: your properly formatted message"

# If pre-push fails
./gradlew clean build
```

**Branch naming issues:**
```bash
# Rename current branch
git branch -m old-name feat/new-name
```
