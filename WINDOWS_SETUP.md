# Windows Setup Guide for Lefthook

This document explains how to properly configure Lefthook on Windows, particularly when using nvm4w (Node Version Manager for Windows).

## The Problem

On Windows with nvm4w, npm installs create `lefthook.cmd` files, but Git hooks look for `lefthook.exe` or `lefthook.bat`. This causes "Can't find lefthook in PATH" errors.

## Solution (Recommended)

We use the `LEFTHOOK_BIN` environment variable to tell lefthook exactly where to find the executable. This is the **standard solution** recommended by the lefthook community.

### Automatic Setup (Preferred)

The project's `build.gradle` file automatically handles this for you:

```bash
./gradlew setupHooks
```

This will:
1. Detect if you're on Windows with nvm4w
2. Set the `LEFTHOOK_BIN` environment variable automatically
3. Install the Git hooks with the correct configuration

### Manual Setup (If Needed)

If you need to run lefthook commands manually outside of Gradle, set the environment variable:

**Command Prompt:**
```cmd
set LEFTHOOK_BIN=C:\nvm4w\nodejs\lefthook.cmd
```

**PowerShell:**
```powershell
$env:LEFTHOOK_BIN = "C:\nvm4w\nodejs\lefthook.cmd"
```

**Permanent Setup (Optional):**
Add `LEFTHOOK_BIN=C:\nvm4w\nodejs\lefthook.cmd` to your Windows environment variables.

## Installation Requirements

1. **Install Node.js via nvm4w**
2. **Install lefthook globally:**
   ```bash
   npm install -g @evilmartians/lefthook@latest
   ```
3. **Run the setup:**
   ```bash
   ./gradlew setupHooks
   ```

## Verification

Test that everything works:

```bash
# This should work without errors
git add .
git commit -m "test: verify lefthook setup"
```

## Troubleshooting

### "Can't find lefthook in PATH" Error

1. Verify lefthook is installed: `npm list -g @evilmartians/lefthook`
2. Check if the file exists: `dir C:\nvm4w\nodejs\lefthook.cmd`
3. Re-run setup: `./gradlew setupHooks`

### Different nvm4w Installation Path

If your nvm4w is installed in a different location, update the path in `build.gradle`:

```gradle
def lefthookPath = 'C:\\your\\custom\\path\\lefthook.cmd'
```

## Why This Solution?

- ✅ **Standard practice** - Uses lefthook's built-in `LEFTHOOK_BIN` support
- ✅ **Team-friendly** - Automatically configured for all developers
- ✅ **Version-controlled** - Setup is in `build.gradle`
- ✅ **Portable** - Works across different Windows setups
- ✅ **Future-proof** - Won't break with lefthook updates

## Alternative Solutions (Not Recommended)

- ❌ Modifying Git hook files directly (not portable)
- ❌ Adding lefthook to system PATH (can conflict with other versions)
- ❌ Using symbolic links (requires admin privileges)

---

For more information, see the [lefthook documentation](https://github.com/evilmartians/lefthook) or ask the team.
