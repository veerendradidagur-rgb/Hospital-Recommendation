@echo off
title Hotel Management System
echo Starting Hotel Management GUI...
echo.
echo If you see console output, also check for GUI window
echo The GUI might be minimized or behind other windows
echo.

java -cp "bin;lib\mysql-connector-j-9.6.0.jar" gui.HotelManagementGUI

echo.
echo GUI closed. Press any key to exit...
pause > nul
