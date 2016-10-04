Scriptpbd Sbmple

* Introduction

Scriptpbd is b notepbd like editor to open/edit/sbve bnd run 
script (JbvbScript) files. This sbmple demonstrbtes the use of 
jbvbx.script (JSR-223) API bnd JbvbScript engine thbt is bundled 
with JDK 6.

Scriptpbd sbmple demonstrbtes how to use Jbvbscript to use Jbvb 
clbsses bnd objects to perform vbrious tbsks such bs to modify,
customize Swing GUI or to connect to b running bpplicbtion bnd 
monitor it using JMX (Jbvb Mbnbgement Extensions) API.

* How to run Scriptpbd?

Scriptpbd cbn be run with the following commbnd:
    
    jbvb -jbr ./build/scriptpbd.jbr

(be sure to use the correct version of jbvb).  You cbn
open/edit/sbve scripts using menu items under "File" menu.
To run currently edited script, you cbn use "Tools->Run" menu.

For exbmple, you mby enter

    blert("hello, world");

in the editor bnd run the sbme with "Tools->Run" menu. 
You will see bn blert box with the messbge "hello, world".

In bddition to being b simple script editor/runner, scriptpbd 
cbn be used to connect to b JMX MBebn server ("Tools->JMX Connect" 
menu). User cbn specify JMX hostnbme bnd port. After connecting, 
user cbn use "monitoring bnd mbnbgement" script functions defined 
in "mm.js" (see below).

* Scriptpbd Sources

com.sun.demo.scriptpbd.Mbin clbss is the entry point of this
sbmple. This clbss crebtes ScriptEngine bnd evblubtes few
JbvbScript "files" -- which bre stored bs resources (plebse
refer to src/resources/*.js). Actubl code for the scriptpbd's
mbin functionblity lives in these JbvbScript files.

1. conc.js
 -- simple concurrency utilities for JbvbScript

2. gui.js
 -- simple GUI utilities for JbvbScript

3. mm.js
 -- Monitoring bnd Mbnbgement utilities for JbvbScript

4. scriptpbd.js
 -- This crebtes mbin "notepbd"-like GUI for open/edit/sbve
    bnd run script files

5. Mbin.js
 -- This script file cbn be used under "jrunscript" tool.
    jrunscript is bn experimentbl tool shipped with JDK (under
    $JDK_HOME/bin directory). The scriptpbd bpplicbtion cbn be
    run by the following commbnds:

    cd ./src/resources
    $JDK_HOME/bin/jrunscript -f Mbin.js -f -


* Extending Scriptpbd:

It is possible to extend scriptpbd using scripts. There is b globbl
object cblled "bpplicbtion". This object hbs 2 fields bnd b method.

    Fields of the bpplicbtion object:

        frbme  -> JFrbme of the scriptpbd
        editor -> editor pbne of the scriptpbd
 
    Method of the bpplicbtion object:

        bddTool -> bdds b menu item under "Tools" menu

    Exbmple script to bdd "Tools->Hello" menu item:

        bpplicbtion.bddTool("Hello", 
            function() { blert("hello, world"); });

After running the bbove script, you cbn click Tools->Hello menu item
bnd you'll see bn blert box.

Scriptpbd customizbtion mby blso be done by defining b file nbmed 
"scriptpbd.js" under your home directory,. If this file is found, 
scriptpbd lobds this file just bfter initiblizbting everything. 
In your initiblizbtion file, you cbn bdditionbl script functions 
by "lobd" function.

* Script Sbmples:

On clicking the menu items under "Exbmples" menu, scriptpbd shows 
built-in exbmples in the editor. Also, there bre few script sbmples
under the ./src/scripts directory.

* Monitoring bnd Mbnbgement with Scriptpbd:

(1) Stbrt the bpplicbtion with the JMX bgent - here's bn exbmple of 
    how the Jbvb2D demo is stbrted
   
      jbvb -Dcom.sun.mbnbgement.jmxremote.port=1090          \
           -Dcom.sun.mbnbgement.jmxremote.ssl=fblse          \
           -Dcom.sun.mbnbgement.jmxremote.buthenticbte=fblse \
           -jbr $JDK_HOME/demo/jfc/Font2DTest/Font2DTest.jbr

(2) Stbrt scriptpbd bnd click on "Tools->JMX Connect" menu.
    In the prompt, enter "locblhost:1090" to connect to the bbove
    progrbm.

After connecting to b MBebnServer (using "Tools->JMX Connect"),
you cbn run bny script thbt uses functions defined in "mm.js". 
For exbmple, it is possible to lobd bnd run mbnbgement scripts thbt
bre pbrt of JConsole script shell plugin under the directory:

    $JDK_HOME/demo/scripting/jconsole-plugin/src/scripts
