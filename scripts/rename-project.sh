
#!/bin/bash

# ========================================
# Spring Boot Project Rename Script
# ========================================
# This script automates the process of transforming this boilerplate
# for use in a new project with custom naming and package structure.

# Configuration - UPDATE THESE VARIABLES FOR YOUR PROJECT
OLD_PACKAGE="com.example.springboot_boilerplate_api"
NEW_PACKAGE="com.yourcompany.your_project_name"
OLD_CLASS="SpringbootBoilerplateApiApplication"
NEW_CLASS="YourProjectNameApplication"
NEW_PROJECT_NAME="your-new-project-name"

echo "🔄 Renaming Spring Boot project..."
echo "📦 Old package: $OLD_PACKAGE"
echo "📦 New package: $NEW_PACKAGE"
echo "🏷️  New project name: $NEW_PROJECT_NAME"
echo ""

# Convert packages to path format
OLD_PACKAGE_PATH=$(echo $OLD_PACKAGE | tr '.' '/')
NEW_PACKAGE_PATH=$(echo $NEW_PACKAGE | tr '.' '/')

# 1. Update settings.gradle
sed -i "s/rootProject.name = 'springboot-boilerplate-api'/rootProject.name = '$NEW_PROJECT_NAME'/" settings.gradle

# 2. Update build.gradle group
sed -i "s/group = 'com.example'/group = '${NEW_PACKAGE%.*}'/" build.gradle

# 3. Create new package structure
mkdir -p "src/main/java/$NEW_PACKAGE_PATH"
mkdir -p "src/test/java/$NEW_PACKAGE_PATH"

# 4. Move Java files and update package declarations
find src -name "*.java" -type f | while read file; do
    echo "Processing: $file"
    
    # Update package declarations in the file
    sed -i "s|package $OLD_PACKAGE|package $NEW_PACKAGE|g" "$file"
    
    # Update imports in the file
    sed -i "s|import $OLD_PACKAGE|import $NEW_PACKAGE|g" "$file"
    
    # Move file to new location if it's in the old package path
    if [[ $file == *"$OLD_PACKAGE_PATH"* ]]; then
        # Calculate new file path
        new_file=${file/$OLD_PACKAGE_PATH/$NEW_PACKAGE_PATH}
        echo "Moving $file -> $new_file"
        
        # Create directory structure for new file
        mkdir -p "$(dirname "$new_file")"
        
        # Move the file
        mv "$file" "$new_file"
    fi
done

# 5. Rename main application class in the moved file
NEW_APP_FILE="src/main/java/$NEW_PACKAGE_PATH/$OLD_CLASS.java"
FINAL_APP_FILE="src/main/java/$NEW_PACKAGE_PATH/$NEW_CLASS.java"

if [ -f "$NEW_APP_FILE" ]; then
    echo "Renaming main application class..."
    sed -i "s/public class $OLD_CLASS/public class $NEW_CLASS/" "$NEW_APP_FILE"
    sed -i "s/$OLD_CLASS.class/$NEW_CLASS.class/" "$NEW_APP_FILE"
    mv "$NEW_APP_FILE" "$FINAL_APP_FILE"
    echo "Main class renamed: $FINAL_APP_FILE"
fi

# 6. Clean up old package structure (only if new structure exists and has files)
if [ -d "src/main/java/$NEW_PACKAGE_PATH" ] && [ "$(find "src/main/java/$NEW_PACKAGE_PATH" -name "*.java" | wc -l)" -gt 0 ]; then
    echo "Cleaning up old package structure..."
    rm -rf "src/main/java/com/example/springboot_boilerplate_api"
    rm -rf "src/test/java/com/example/springboot_boilerplate_api"
    
    # Only remove the parent directories if they're empty
    rmdir "src/main/java/com/example" 2>/dev/null || true
    rmdir "src/test/java/com/example" 2>/dev/null || true
    rmdir "src/main/java/com" 2>/dev/null || true
    rmdir "src/test/java/com" 2>/dev/null || true
fi

echo "✅ Project renamed successfully!"
echo "📝 Don't forget to:"
echo "   - Update README.md with your project details"
echo "   - Update application.properties if needed"
echo "   - Delete this script: rm scripts/rename-project.sh"
echo "   - Test the build: ./gradlew clean build"