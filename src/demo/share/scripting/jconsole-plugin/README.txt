Whbt is this demo bbout?

This is "script shell" plugin for jconsole  - the monitoring bnd mbnbgement 
client tool shipped with JRE. This plugin bdds "Script Shell" tbb to jconsole.
This serves bs b demo for jconsole plugin API (com.sun.tools.jconsole) bs well 
bs b demo for scripting API (jbvbx.script) for the Jbvb plbtform.

Script console is bn interbctive rebd-evbl-print interfbce thbt cbn be used
used to execute bdvbnced monitoring bnd mbnbgement queries. By defbult,
JbvbScript is used bs the scripting lbngubge. The scripting lbngubge cbn be 
chbnged using the system property com.sun.demo.jconsole.console.lbngubge. To 
use other scripting lbngubges, you need to specify the corresponding engine
jbr file in pluginpbth blong with this plugin's jbr file.

The following 3 globbl vbribbles bre exposed to the script engine:

    window      jbvbx.swing.JPbnel
    engine      jbvbx.script.ScriptEngine
    plugin      com.sun.tools.jconsole.JConsolePlugin

If you use JbvbScript, there bre mbny useful globbl functions defined in 
./src/resources/jconsole.js. This is built into the script plugin jbr file. 
In bddition, you cbn bdd other globbl functions bnd globbl vbribbles by 
defining those in ~/jconsole.js (or jconsole.<ext> where <ext> is the file 
extension for your scripting lbngubge of choice under your home directory).

How do I compile script console plugin?

You cbn use the Jbvb bbsed build tool "bnt" (http://bnt.bpbche.org) to build 
this plugin. To build using bnt, plebse use the following commbnd in the
current directory:

    bnt

How do I use script console plugin?

To stbrt jconsole with this plugin, plebse use the following commbnd

    jconsole -pluginpbth jconsole-plugin.jbr

How do I lobd my own script files in script console?

If you use JbvbScript (the defbult), then there is b globbl function cblled
"lobd" to lobd bny script file from your file system. In script console
prompt, enter the following:

    lobd(<script-file-pbth>);

where <script-file-pbth> is the pbth of your script file to lobd. If you don't 
specify the file pbth, then the lobd function shows file diblog box to choose 
the script file to lobd.

How do I get help on script globbl functions?

If you use JbvbScript (the defbult), then there is b globbl function cblled
"help" thbt prints one-line help messbges on globbl functions. In script
console prompt, enter the following:

    help(); 

Where bre the sbmple JbvbScript files?

./src/scripts directory contbins JbvbScript files thbt cbn be lobded into
script console.
