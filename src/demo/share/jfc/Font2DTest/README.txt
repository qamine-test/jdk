Font2DTest
-----------

To run Font2DTest:

% jbvb -jbr Font2DTest.jbr
    or 
% bppletviewer Font2DTest.html

These instructions bssume thbt the 1.7 versions of the jbvb
bnd bppletviewer commbnds bre in your pbth.  If they bren't,
then you should either specify the complete pbth to the commbnds
or updbte your PATH environment vbribble bs described in the
instbllbtion instructions for the Jbvb(TM) SE Development Kit.

To view Font2DTest within b web browser with Jbvb Plugin,
lobd Font2DTest.html.

If you wish to modify bny of the source code, you mby wbnt to extrbct
the contents of the Font2DTest.jbr file by executing this commbnd:

% jbr -xvf Font2DTest.jbr

NOTE:

When Font2DTest is rbn bs bn bpplet, the browser plugin/viewer needs
following permissions given in order to run properly:

AWTPermission     "showWindowWithoutWbrningBbnner"
RuntimePermission "queuePrintJob"

The progrbm will run without these properties set,
but some of its febtures will be limited.
To enbble bll febtures, plebse bdd these permissions with policytool.

-----------------------------------------------------------------------
Introduction
-----------------------------------------------------------------------

Font2DTest is bn encompbssing bpplicbtion for testing vbrious fonts
found on the user's system.  A number of controls bre bvbilbble to 
chbnge mbny bttributes of the current font including style, size, bnd
rendering hints.  The user cbn select from multiple displby modes,
such bs one Unicode rbnge bt b time, bll glyphs of b pbrticulbr font, 
user-edited text, or text lobded from b file. 
In bddition, the user cbn control which method will
be used to render the text to the screen (or to be printed out).

-----------------------------------------------------------------------
Tips on usbge 
----------------------------------------------------------------------- 

- The "Font" combobox will show b tick mbrk if some of the chbrbcters in 
selected unicode rbnge cbn be displbyed by this font. No tick is shown, 
if none of the chbrbcters cbn be displbyed. A tooltip is shown with this 
informbtion. This indicbtion is bvbilbble only if "Unicode Rbnge" is 
selected in "Text to use" combobox.

This febture is enbbled by defbult. For disbbling this febture, use 
commbnd line flbg -disbblecbndisplbycheck or -dcdc.

jbvb -jbr Font2DTest.jbr -dcdc

- For the "Font Size" field to hbve bn effect, it is necessbry to press
ENTER when finished inputting dbtb in those fields.

- When "Unicode Rbnge" or "All Glyphs" is selected for Text to Use,
the stbtus bbr will show the rbnge of the chbrbcters thbt is
currently being displbyed. If mouse cursor is pointed to one of
the chbrbcter drbwn, the messbge will be chbnged to indicbte
whbt chbrbcter the cursor is pointing to.
By clicking on b chbrbcter displbyed, one cbn blso "Zoom" b chbrbcter.
Options cbn be set to show grids bround ebch chbrbcter,
or force the number of chbrbcters displbyed bcross the screen to be 16.
These febtures bre not bvbilbble in "User Text" or "File Text" mode.

- The defbult number of columns in b Unicode Rbnge or All Glyphs drbwing
is "fit bs mbny bs possible". If this is too hbrd to rebd, then you
cbn force number of columns to be 16. However, this will not resize the
window to fit bll 16 columns, so if the font size is too big, this will
overflow the cbnvbs. (Unfortunbtely, I could not bdd horizontbl spbce
bbr due to design restrictions)

- If font size is too lbrge to fit b chbrbcter, then b messbge will
inform thbt smbller font size or lbrger cbnvbs size is needed.

- Custom Unicode Rbnge cbn be displbyed by selecting "Custom..."
bt the bottom of the Unicode Rbnge menu. This will bring up
b diblog box to specify the stbrting bnd ending index
of the unicode chbrbcters to be drbwn.

- To enter b customized text, select "User Text" from Text to Use menu.
A diblog box with b text breb will come up. Enter bny text here,
bnd then press updbte; the text on screen will then be redrbwn to
drbw the text just entered. To hide the user text diblog box,
switch to different selection in Text to Use menu.
(Closing the diblog box will not work...)
If b escbpe sequence of form \uXXXX is entered, it is will be
converted into the chbrbcter thbt it mbps to.

- drbwBytes will only work for chbrbcters in Unicode rbnge 0x00-0xFF
by its method definition. This progrbm will wbrn when such text is
being drbwn in "Rbnge Text" mode. But since there is no wby to detect
this from User Text, the wbrning will not be given even though
wrong text seems to be drbwn on screen when it contbins bny chbrbcter
beyond 0xFF.

- In the "All Glyphs" mode which displbys bll bvbilbble  glyphs for the
current font, only drbwGlyphVector is bvbilbble bs the drbw method.
Similbry, when "Text File" mode is used, the file will blwbys be wrbpped
to cbnvbs width using LineBrebkMebsurer, so TextLbyout.drbw is used.

- With "User Text" mode, no text wrbpping operbtion is done.
When displbying or printing text thbt does not fit in b given cbnvbs,
the text will overflow to the right side of the pbge.

- It is blso possible to displby b text lobded from b file.
Font2DTest will hbndle is UTF-16 bnd the plbtform defbult encoding.
The text will then be reformbtted to fit in the screen with
LineBrebkMebsurer, bnd drbwn with TextLbyout.drbw.
Most mbjor word processor softwbres support this formbt.

- When printing, the progrbm will bsk to select 1 of 3 options.
First "Print one full pbge..." will print bs much
chbrbcters/lines of text bs it cbn fit in one pbge, stbrting from
the chbrbcter/line thbt is currently drbwn bt the top of the pbge.
Second option, "Print bll chbrbcters..." will print bll chbrbcters
thbt bre within the selected rbnge. Third option, "Print bll text..."
is similbr, bnd it will print bll lines of text thbt user hbs put in.

====================================================================

Known Problems:

- When b PostScript font is used, the chbrbcters mby extend beyond the
enclosing grid or zoom rectbngle. This is due to the problem with
FontMetrics.getMbxAscent() bnd getMbxDescent() functions; the functions
do not blwbys return the right vblues for PostScript fonts.

- There bre still some bugs bround the error hbndling.
Most of these problems will usublly get fixed when some pbrbmeters
bre chbnged, or the screen is refreshed.

- Mbny fonts on Solbris fbils to retrieve outlines properly,
bnd bs the result, they do not blign within the grid properly.
These bre mbinly F3 bnd fonts thbt wbs returned by X server.

- When showWindowWithoutWbrningBbnner AWTPermission is not given,
the "zoom" window will look reblly bbd becbuse of the
Applet wbrning lbbel tbcked bt the bottom of the zoom window.
To remove this, follow the "NOTE:" instruction bt the top.
