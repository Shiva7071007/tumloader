# tumloader
A simple java program to create a download list for all images and gifs from a tumblr blog which can be downloaded using any downloader like idm (windows), uget (linux), ADM(android), etc.


-----------------------------method to use----------------------------------

1> enter the blog address like example.tumblr.com (dont enter with http://)

2> enter the starting position ( 0 for start from begginning )

3> enter the ending postion ( must be less than or equal to total no post on that blog)



------------------------------NOTES---------------------------------------------

-->this program uses tumblr api v1 ( for details goto https://www.tumblr.com/docs/en/api/v1 )

-->change the download path in file /src/main.java at line "File myFile = new File("C:/downloads/downloadList.txt");"

-->search google for how to import downloads links from text file for the respected downloader.

-->downloader is not included cause these downloaders can handle large downloads efficiently and provide better resume and queue compability


