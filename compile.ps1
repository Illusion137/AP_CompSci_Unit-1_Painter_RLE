clang++ src/main.cpp `
    -o build/rle.exe;
if(!$LASTEXITCODE){
    ./build/rle.exe;
    Write-Host -NoNewLine 'FINSIHED RUNNING...';
    $null = $Host.UI.RawUI.ReadKey('NoEcho,IncludeKeyDown');
    Clear-Host;
}