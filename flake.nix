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

        androidBuildToolsVersion = "33.0.1";

        androidComposition = pkgs.androidenv.composeAndroidPackages {
          toolsVersion = "26.1.1";
          platformToolsVersion = "33.0.3";
          buildToolsVersions = [androidBuildToolsVersion "30.0.3"];
          includeEmulator = false;
          emulatorVersion = "32.1.8";
          platformVersions = ["28" "29" "30" "31" "33"];
          includeSources = true;
          includeSystemImages = false;
          systemImageTypes = ["google_apis_playstore"];
          abiVersions = ["armeabi-v7a" "arm64-v8a"];
          cmakeVersions = ["3.22.1"];
          includeNDK = true;
          ndkVersions = ["25.1.8937393"];
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
