@echo off
set path=C:\Users\HeshamAbdElhamedGhar\.m2\repository\allure\allure-2.19.0\bin;C:\Program Files\Java\jdk-17.0.2\bin;%path%
allure serve allure-results
pause
exit