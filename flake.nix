{
  description = "A very basic flake";

  inputs = {
    utils.url = "github:numtide/flake-utils";
  };

  outputs = { self, nixpkgs, utils }:
    utils.lib.eachDefaultSystem (system:
      let
        pkgs = import nixpkgs {
          inherit system;
          config = {
            allowUnfree = true;
            android_sdk.accept_license = true;
          };
        };

        androidComposition = pkgs.androidenv.composeAndroidPackages {
          toolsVersion = "26.1.1";
          platformToolsVersion = "34.0.5";
          buildToolsVersions = ["34.0.0"];
          includeEmulator = true;
          emulatorVersion = "34.1.9";
          platformVersions = ["34"];
          includeSources = true;
          includeSystemImages = false;
          systemImageTypes = ["google_apis_playstore"];
          abiVersions = ["armeabi-v7a" "arm64-v8a"];
          cmakeVersions = ["3.22.1"];
          includeNDK = true;
          ndkVersions = ["26.1.10909125"];
          useGoogleAPIs = false;
          useGoogleTVAddOns = false;
          includeExtras = [
            "extras;google;gcm"
          ];
          extraLicenses = [
            "android-googletv-license"
            "android-sdk-arm-dbt-license"
            "android-sdk-license"
            "android-sdk-preview-license"
            "google-gdk-license"
            "intel-android-extra-license"
            "intel-android-sysimage-license"
            "mips-android-sysimage-license"
          ];
        };
      in
      {
        devShell = with pkgs; mkShell {
          buildInputs = [
            androidComposition.androidsdk
            androidComposition.platform-tools
            android-studio

            gradle
            
            kotlin-language-server
            jdt-language-server
          ];
          RUST_SRC_PATH = rustPlatform.rustLibSrc;
        };
      });
}
