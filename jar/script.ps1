$build = $args[0];
#$test = $args[1];
#$isRunTest = ($args[2] -eq "true" -or $args[2] -eq $null);

#echo $isRunTest

#remove downloads
#adb shell rm -r /sdcard/NOOK
$commandServer = "java -jar .\selendroid-standalone-0.10.0-with-dependencies.jar -app $build -noClearData"
Start-Process powershell -ArgumentList $commandServer
Start-Sleep -Seconds 5
java -jar .\AlchemyUISelStressTest.jar
