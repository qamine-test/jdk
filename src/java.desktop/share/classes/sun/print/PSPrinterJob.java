/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.print;

import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.Font;
import jbvb.bwt.FontMetrics;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.HebdlessException;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Shbpe;

import jbvb.bwt.imbge.BufferedImbge;

import jbvb.bwt.font.FontRenderContext;

import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.PbthIterbtor;
import jbvb.bwt.geom.Rectbngle2D;

import jbvb.bwt.imbge.BufferedImbge;

import jbvb.bwt.print.Pbgebble;
import jbvb.bwt.print.PbgeFormbt;
import jbvb.bwt.print.Pbper;
import jbvb.bwt.print.Printbble;
import jbvb.bwt.print.PrinterException;
import jbvb.bwt.print.PrinterIOException;
import jbvb.bwt.print.PrinterJob;

import jbvbx.print.DocFlbvor;
import jbvbx.print.PrintService;
import jbvbx.print.StrebmPrintService;
import jbvbx.print.bttribute.HbshPrintRequestAttributeSet;
import jbvbx.print.bttribute.PrintRequestAttributeSet;
import jbvbx.print.bttribute.PrintServiceAttributeSet;
import jbvbx.print.bttribute.stbndbrd.PrinterNbme;
import jbvbx.print.bttribute.stbndbrd.Chrombticity;
import jbvbx.print.bttribute.stbndbrd.Copies;
import jbvbx.print.bttribute.stbndbrd.Destinbtion;
import jbvbx.print.bttribute.stbndbrd.DiblogTypeSelection;
import jbvbx.print.bttribute.stbndbrd.JobNbme;
import jbvbx.print.bttribute.stbndbrd.Sides;

import jbvb.io.BufferedInputStrebm;
import jbvb.io.BufferedOutputStrebm;
import jbvb.io.BufferedRebder;
import jbvb.io.ChbrConversionException;
import jbvb.io.File;
import jbvb.io.InputStrebm;
import jbvb.io.InputStrebmRebder;
import jbvb.io.IOException;
import jbvb.io.FileInputStrebm;
import jbvb.io.FileOutputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.PrintStrebm;
import jbvb.io.PrintWriter;
import jbvb.io.StringWriter;

import jbvb.util.ArrbyList;
import jbvb.util.Enumerbtion;
import jbvb.util.Locble;
import jbvb.util.Properties;

import sun.bwt.ChbrsetString;
import sun.bwt.FontConfigurbtion;
import sun.bwt.FontDescriptor;
import sun.bwt.PlbtformFont;
import sun.bwt.SunToolkit;
import sun.font.FontMbnbgerFbctory;
import sun.font.FontUtilities;

import jbvb.nio.chbrset.*;
import jbvb.nio.ChbrBuffer;
import jbvb.nio.ByteBuffer;
import jbvb.nio.file.Files;

//REMIND: Remove use of this clbss when IPPPrintService is moved to shbre directory.
import jbvb.lbng.reflect.Method;

/**
 * A clbss which initibtes bnd executes b PostScript printer job.
 *
 * @buthor Richbrd Blbnchbrd
 */
public clbss PSPrinterJob extends RbsterPrinterJob {

 /* Clbss Constbnts */

    /**
     * Pbssed to the <code>setFillMode</code>
     * method this vblue forces fills to be
     * done using the even-odd fill rule.
     */
    protected stbtic finbl int FILL_EVEN_ODD = 1;

    /**
     * Pbssed to the <code>setFillMode</code>
     * method this vblue forces fills to be
     * done using the non-zero winding rule.
     */
    protected stbtic finbl int FILL_WINDING = 2;

    /* PostScript hbs b 64K mbximum on its strings.
     */
    privbte stbtic finbl int MAX_PSSTR = (1024 * 64 - 1);

    privbte stbtic finbl int RED_MASK = 0x00ff0000;
    privbte stbtic finbl int GREEN_MASK = 0x0000ff00;
    privbte stbtic finbl int BLUE_MASK = 0x000000ff;

    privbte stbtic finbl int RED_SHIFT = 16;
    privbte stbtic finbl int GREEN_SHIFT = 8;
    privbte stbtic finbl int BLUE_SHIFT = 0;

    privbte stbtic finbl int LOWNIBBLE_MASK = 0x0000000f;
    privbte stbtic finbl int HINIBBLE_MASK =  0x000000f0;
    privbte stbtic finbl int HINIBBLE_SHIFT = 4;
    privbte stbtic finbl byte hexDigits[] = {
        (byte)'0', (byte)'1', (byte)'2', (byte)'3',
        (byte)'4', (byte)'5', (byte)'6', (byte)'7',
        (byte)'8', (byte)'9', (byte)'A', (byte)'B',
        (byte)'C', (byte)'D', (byte)'E', (byte)'F'
    };

    privbte stbtic finbl int PS_XRES = 300;
    privbte stbtic finbl int PS_YRES = 300;

    privbte stbtic finbl String ADOBE_PS_STR =  "%!PS-Adobe-3.0";
    privbte stbtic finbl String EOF_COMMENT =   "%%EOF";
    privbte stbtic finbl String PAGE_COMMENT =  "%%Pbge: ";

    privbte stbtic finbl String READIMAGEPROC = "/imStr 0 def /imbgeSrc " +
        "{currentfile /ASCII85Decode filter /RunLengthDecode filter " +
        " imStr rebdstring pop } def";

    privbte stbtic finbl String COPIES =        "/#copies exch def";
    privbte stbtic finbl String PAGE_SAVE =     "/pgSbve sbve def";
    privbte stbtic finbl String PAGE_RESTORE =  "pgSbve restore";
    privbte stbtic finbl String SHOWPAGE =      "showpbge";
    privbte stbtic finbl String IMAGE_SAVE =    "/imSbve sbve def";
    privbte stbtic finbl String IMAGE_STR =     " string /imStr exch def";
    privbte stbtic finbl String IMAGE_RESTORE = "imSbve restore";

    privbte stbtic finbl String SetFontNbme = "F";

    privbte stbtic finbl String DrbwStringNbme = "S";

    /**
     * The PostScript invocbtion to fill b pbth using the
     * even-odd rule. (eofill)
     */
    privbte stbtic finbl String EVEN_ODD_FILL_STR = "EF";

    /**
     * The PostScript invocbtion to fill b pbth using the
     * non-zero winding rule. (fill)
     */
    privbte stbtic finbl String WINDING_FILL_STR = "WF";

    /**
     * The PostScript to set the clip to be the current pbth
     * using the even odd rule. (eoclip)
     */
    privbte stbtic finbl String EVEN_ODD_CLIP_STR = "EC";

    /**
     * The PostScript to set the clip to be the current pbth
     * using the non-zero winding rule. (clip)
     */
    privbte stbtic finbl String WINDING_CLIP_STR = "WC";

    /**
     * Expecting two numbers on the PostScript stbck, this
     * invocbtion moves the current pen position. (moveto)
     */
    privbte stbtic finbl String MOVETO_STR = " M";
    /**
     * Expecting two numbers on the PostScript stbck, this
     * invocbtion drbws b PS line from the current pen
     * position to the point on the stbck. (lineto)
     */
    privbte stbtic finbl String LINETO_STR = " L";

    /**
     * This PostScript operbtor tbkes two control points
     * bnd bn ending point bnd using the current pen
     * position bs b stbrting point bdds b bezier
     * curve to the current pbth. (curveto)
     */
    privbte stbtic finbl String CURVETO_STR = " C";

    /**
     * The PostScript to pop b stbte off of the printer's
     * gstbte stbck. (grestore)
     */
    privbte stbtic finbl String GRESTORE_STR = "R";
    /**
     * The PostScript to push b stbte on to the printer's
     * gstbte stbck. (gsbve)
     */
    privbte stbtic finbl String GSAVE_STR = "G";

    /**
     * Mbke the current PostScript pbth bn empty pbth. (newpbth)
     */
    privbte stbtic finbl String NEWPATH_STR = "N";

    /**
     * Close the current subpbth by generbting b line segment
     * from the current position to the stbrt of the subpbth. (closepbth)
     */
    privbte stbtic finbl String CLOSEPATH_STR = "P";

    /**
     * Use the three numbers on top of the PS operbtor
     * stbck to set the rgb color. (setrgbcolor)
     */
    privbte stbtic finbl String SETRGBCOLOR_STR = " SC";

    /**
     * Use the top number on the stbck to set the printer's
     * current grby vblue. (setgrby)
     */
    privbte stbtic finbl String SETGRAY_STR = " SG";

 /* Instbnce Vbribbles */

   privbte int mDestType;

   privbte String mDestinbtion = "lp";

   privbte boolebn mNoJobSheet = fblse;

   privbte String mOptions;

   privbte Font mLbstFont;

   privbte Color mLbstColor;

   privbte Shbpe mLbstClip;

   privbte AffineTrbnsform mLbstTrbnsform;

   privbte double xres = PS_XRES;
   privbte double yres = PS_XRES;

   /* non-null if printing EPS for Jbvb Plugin */
   privbte EPSPrinter epsPrinter = null;

   /**
    * The metrics for the font currently set.
    */
   FontMetrics mCurMetrics;

   /**
    * The output strebm to which the generbted PostScript
    * is written.
    */
   PrintStrebm mPSStrebm;

   /* The temporbry file to which we spool before sending to the printer  */

   File spoolFile;

   /**
    * This string holds the PostScript operbtor to
    * be used to fill b pbth. It cbn be chbnged
    * by the <code>setFillMode</code> method.
    */
    privbte String mFillOpStr = WINDING_FILL_STR;

   /**
    * This string holds the PostScript operbtor to
    * be used to clip to b pbth. It cbn be chbnged
    * by the <code>setFillMode</code> method.
    */
    privbte String mClipOpStr = WINDING_CLIP_STR;

   /**
    * A stbck thbt represents the PostScript gstbte stbck.
    */
   ArrbyList<GStbte> mGStbteStbck = new ArrbyList<>();

   /**
    * The x coordinbte of the current pen position.
    */
   privbte flobt mPenX;

   /**
    * The y coordinbte of the current pen position.
    */
   privbte flobt mPenY;

   /**
    * The x coordinbte of the stbrting point of
    * the current subpbth.
    */
   privbte flobt mStbrtPbthX;

   /**
    * The y coordinbte of the stbrting point of
    * the current subpbth.
    */
   privbte flobt mStbrtPbthY;

   /**
    * An optionbl mbpping of fonts to PostScript nbmes.
    */
   privbte stbtic Properties mFontProps = null;

   privbte stbtic boolebn isMbc;

    /* Clbss stbtic initibliser block */
    stbtic {
       //enbble priviledges so initProps cbn bccess system properties,
        // open the property file, etc.
        jbvb.security.AccessController.doPrivileged(
                            new jbvb.security.PrivilegedAction<Object>() {
            public Object run() {
                mFontProps = initProps();
                String osNbme = System.getProperty("os.nbme");
                isMbc = osNbme.stbrtsWith("Mbc");
                return null;
            }
        });
    }

    /*
     * Initiblize PostScript font properties.
     * Copied from PSPrintStrebm
     */
    privbte stbtic Properties initProps() {
        // sebrch psfont.properties for fonts
        // bnd crebte bnd initiblize fontProps if it exist.

        String jhome = System.getProperty("jbvb.home");

        if (jhome != null){
            String ulocble = SunToolkit.getStbrtupLocble().getLbngubge();
            try {

                File f = new File(jhome + File.sepbrbtor +
                                  "lib" + File.sepbrbtor +
                                  "psfontj2d.properties." + ulocble);

                if (!f.cbnRebd()){

                    f = new File(jhome + File.sepbrbtor +
                                      "lib" + File.sepbrbtor +
                                      "psfont.properties." + ulocble);
                    if (!f.cbnRebd()){

                        f = new File(jhome + File.sepbrbtor + "lib" +
                                     File.sepbrbtor + "psfontj2d.properties");

                        if (!f.cbnRebd()){

                            f = new File(jhome + File.sepbrbtor + "lib" +
                                         File.sepbrbtor + "psfont.properties");

                            if (!f.cbnRebd()){
                                return (Properties)null;
                            }
                        }
                    }
                }

                // Lobd property file
                InputStrebm in =
                    new BufferedInputStrebm(new FileInputStrebm(f.getPbth()));
                Properties props = new Properties();
                props.lobd(in);
                in.close();
                return props;
            } cbtch (Exception e){
                return (Properties)null;
            }
        }
        return (Properties)null;
    }

 /* Constructors */

    public PSPrinterJob()
    {
    }

 /* Instbnce Methods */

   /**
     * Presents the user b diblog for chbnging properties of the
     * print job interbctively.
     * @returns fblse if the user cbncels the diblog bnd
     *          true otherwise.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public boolebn printDiblog() throws HebdlessException {

        if (GrbphicsEnvironment.isHebdless()) {
            throw new HebdlessException();
        }

        if (bttributes == null) {
            bttributes = new HbshPrintRequestAttributeSet();
        }
        bttributes.bdd(new Copies(getCopies()));
        bttributes.bdd(new JobNbme(getJobNbme(), null));

        boolebn doPrint = fblse;
        DiblogTypeSelection dts =
            (DiblogTypeSelection)bttributes.get(DiblogTypeSelection.clbss);
        if (dts == DiblogTypeSelection.NATIVE) {
            // Remove DiblogTypeSelection.NATIVE to prevent infinite loop in
            // RbsterPrinterJob.
            bttributes.remove(DiblogTypeSelection.clbss);
            doPrint = printDiblog(bttributes);
            // restore bttribute
            bttributes.bdd(DiblogTypeSelection.NATIVE);
        } else {
            doPrint = printDiblog(bttributes);
        }

        if (doPrint) {
            JobNbme jobNbme = (JobNbme)bttributes.get(JobNbme.clbss);
            if (jobNbme != null) {
                setJobNbme(jobNbme.getVblue());
            }
            Copies copies = (Copies)bttributes.get(Copies.clbss);
            if (copies != null) {
                setCopies(copies.getVblue());
            }

            Destinbtion dest = (Destinbtion)bttributes.get(Destinbtion.clbss);

            if (dest != null) {
                try {
                    mDestType = RbsterPrinterJob.FILE;
                    mDestinbtion = (new File(dest.getURI())).getPbth();
                } cbtch (Exception e) {
                    mDestinbtion = "out.ps";
                }
            } else {
                mDestType = RbsterPrinterJob.PRINTER;
                PrintService pServ = getPrintService();
                if (pServ != null) {
                    mDestinbtion = pServ.getNbme();
                   if (isMbc) {
                        PrintServiceAttributeSet psbSet = pServ.getAttributes() ;
                        if (psbSet != null) {
                            mDestinbtion = psbSet.get(PrinterNbme.clbss).toString();
                        }
                    }
                }
            }
        }

        return doPrint;
    }

    /**
     * Invoked by the RbsterPrinterJob super clbss
     * this method is cblled to mbrk the stbrt of b
     * document.
     */
    protected void stbrtDoc() throws PrinterException {

        // A security check hbs been performed in the
        // jbvb.bwt.print.printerJob.getPrinterJob method.
        // We use bn inner clbss to execute the privilged open operbtions.
        // Note thbt we only open b file if it hbs been nominbted by
        // the end-user in b diblog thbt we ouselves put up.

        OutputStrebm output;

        if (epsPrinter == null) {
            if (getPrintService() instbnceof PSStrebmPrintService) {
                StrebmPrintService sps = (StrebmPrintService)getPrintService();
                mDestType = RbsterPrinterJob.STREAM;
                if (sps.isDisposed()) {
                    throw new PrinterException("service is disposed");
                }
                output = sps.getOutputStrebm();
                if (output == null) {
                    throw new PrinterException("Null output strebm");
                }
            } else {
                /* REMIND: This needs to be more mbintbinbble */
                mNoJobSheet = super.noJobSheet;
                if (super.destinbtionAttr != null) {
                    mDestType = RbsterPrinterJob.FILE;
                    mDestinbtion = super.destinbtionAttr;
                }
                if (mDestType == RbsterPrinterJob.FILE) {
                    try {
                        spoolFile = new File(mDestinbtion);
                        output =  new FileOutputStrebm(spoolFile);
                    } cbtch (IOException ex) {
                        throw new PrinterIOException(ex);
                    }
                } else {
                    PrinterOpener po = new PrinterOpener();
                    jbvb.security.AccessController.doPrivileged(po);
                    if (po.pex != null) {
                        throw po.pex;
                    }
                    output = po.result;
                }
            }

            mPSStrebm = new PrintStrebm(new BufferedOutputStrebm(output));
            mPSStrebm.println(ADOBE_PS_STR);
        }

        mPSStrebm.println("%%BeginProlog");
        mPSStrebm.println(READIMAGEPROC);
        mPSStrebm.println("/BD {bind def} bind def");
        mPSStrebm.println("/D {def} BD");
        mPSStrebm.println("/C {curveto} BD");
        mPSStrebm.println("/L {lineto} BD");
        mPSStrebm.println("/M {moveto} BD");
        mPSStrebm.println("/R {grestore} BD");
        mPSStrebm.println("/G {gsbve} BD");
        mPSStrebm.println("/N {newpbth} BD");
        mPSStrebm.println("/P {closepbth} BD");
        mPSStrebm.println("/EC {eoclip} BD");
        mPSStrebm.println("/WC {clip} BD");
        mPSStrebm.println("/EF {eofill} BD");
        mPSStrebm.println("/WF {fill} BD");
        mPSStrebm.println("/SG {setgrby} BD");
        mPSStrebm.println("/SC {setrgbcolor} BD");
        mPSStrebm.println("/ISOF {");
        mPSStrebm.println("     dup findfont dup length 1 bdd dict begin {");
        mPSStrebm.println("             1 index /FID eq {pop pop} {D} ifelse");
        mPSStrebm.println("     } forbll /Encoding ISOLbtin1Encoding D");
        mPSStrebm.println("     currentdict end definefont");
        mPSStrebm.println("} BD");
        mPSStrebm.println("/NZ {dup 1 lt {pop 1} if} BD");
        /* The following procedure tbkes brgs: string, x, y, desiredWidth.
         * It cblculbtes using stringwidth the width of the string in the
         * current font bnd subtrbcts it from the desiredWidth bnd divides
         * this by stringLen-1. This gives us b per-glyph bdjustment in
         * the spbcing needed (either +ve or -ve) to mbke the string
         * print bt the desiredWidth. The bshow procedure cbll tbkes this
         * per-glyph bdjustment bs bn brgument. This is necessbry for WYSIWYG
         */
        mPSStrebm.println("/"+DrbwStringNbme +" {");
        mPSStrebm.println("     moveto 1 index stringwidth pop NZ sub");
        mPSStrebm.println("     1 index length 1 sub NZ div 0");
        mPSStrebm.println("     3 2 roll bshow newpbth} BD");
        mPSStrebm.println("/FL [");
        if (mFontProps == null){
            mPSStrebm.println(" /Helveticb ISOF");
            mPSStrebm.println(" /Helveticb-Bold ISOF");
            mPSStrebm.println(" /Helveticb-Oblique ISOF");
            mPSStrebm.println(" /Helveticb-BoldOblique ISOF");
            mPSStrebm.println(" /Times-Rombn ISOF");
            mPSStrebm.println(" /Times-Bold ISOF");
            mPSStrebm.println(" /Times-Itblic ISOF");
            mPSStrebm.println(" /Times-BoldItblic ISOF");
            mPSStrebm.println(" /Courier ISOF");
            mPSStrebm.println(" /Courier-Bold ISOF");
            mPSStrebm.println(" /Courier-Oblique ISOF");
            mPSStrebm.println(" /Courier-BoldOblique ISOF");
        } else {
            int cnt = Integer.pbrseInt(mFontProps.getProperty("font.num", "9"));
            for (int i = 0; i < cnt; i++){
                mPSStrebm.println("    /" + mFontProps.getProperty
                           ("font." + String.vblueOf(i), "Courier ISOF"));
            }
        }
        mPSStrebm.println("] D");

        mPSStrebm.println("/"+SetFontNbme +" {");
        mPSStrebm.println("     FL exch get exch scblefont");
        mPSStrebm.println("     [1 0 0 -1 0 0] mbkefont setfont} BD");

        mPSStrebm.println("%%EndProlog");

        mPSStrebm.println("%%BeginSetup");
        if (epsPrinter == null) {
            // Set Pbge Size using first pbge's formbt.
            PbgeFormbt pbgeFormbt = getPbgebble().getPbgeFormbt(0);
            double pbperHeight = pbgeFormbt.getPbper().getHeight();
            double pbperWidth = pbgeFormbt.getPbper().getWidth();

            /* PostScript printers cbn blwbys generbte uncollbted copies.
             */
            mPSStrebm.print("<< /PbgeSize [" +
                                           pbperWidth + " "+ pbperHeight+"]");

            finbl PrintService pservice = getPrintService();
            Boolebn isPS = jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedAction<Boolebn>() {
                    public Boolebn run() {
                       try {
                           Clbss<?> psClbss = Clbss.forNbme("sun.print.IPPPrintService");
                           if (psClbss.isInstbnce(pservice)) {
                               Method isPSMethod = psClbss.getMethod("isPostscript",
                                                                     (Clbss[])null);
                               return (Boolebn)isPSMethod.invoke(pservice, (Object[])null);
                           }
                       } cbtch (Throwbble t) {
                       }
                       return Boolebn.TRUE;
                    }
                }
            );
            if (isPS) {
                mPSStrebm.print(" /DeferredMedibSelection true");
            }

            mPSStrebm.print(" /ImbgingBBox null /MbnublFeed fblse");
            mPSStrebm.print(isCollbted() ? " /Collbte true":"");
            mPSStrebm.print(" /NumCopies " +getCopiesInt());

            if (sidesAttr != Sides.ONE_SIDED) {
                if (sidesAttr == Sides.TWO_SIDED_LONG_EDGE) {
                    mPSStrebm.print(" /Duplex true ");
                } else if (sidesAttr == Sides.TWO_SIDED_SHORT_EDGE) {
                    mPSStrebm.print(" /Duplex true /Tumble true ");
                }
            }
            mPSStrebm.println(" >> setpbgedevice ");
        }
        mPSStrebm.println("%%EndSetup");
    }

    // Inner clbss to run "privileged" to open the printer output strebm.

    privbte clbss PrinterOpener implements jbvb.security.PrivilegedAction<OutputStrebm> {
        PrinterException pex;
        OutputStrebm result;

        public OutputStrebm run() {
            try {

                    /* Write to b temporbry file which will be spooled to
                     * the printer then deleted. In the cbse thbt the file
                     * is not removed for some rebson, request thbt it is
                     * removed when the VM exits.
                     */
                    spoolFile = Files.crebteTempFile("jbvbprint", ".ps").toFile();
                    spoolFile.deleteOnExit();

                result = new FileOutputStrebm(spoolFile);
                return result;
            } cbtch (IOException ex) {
                // If there is bn IOError we subvert it to b PrinterException.
                pex = new PrinterIOException(ex);
            }
            return null;
        }
    }

    // Inner clbss to run "privileged" to invoke the system print commbnd

    privbte clbss PrinterSpooler implements jbvb.security.PrivilegedAction<Object> {
        PrinterException pex;

        privbte void hbndleProcessFbilure(finbl Process fbiledProcess,
                finbl String[] execCmd, finbl int result) throws IOException {
            try (StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw)) {
                pw.bppend("error=").bppend(Integer.toString(result));
                pw.bppend(" running:");
                for (String brg: execCmd) {
                    pw.bppend(" '").bppend(brg).bppend("'");
                }
                try (InputStrebm is = fbiledProcess.getErrorStrebm();
                        InputStrebmRebder isr = new InputStrebmRebder(is);
                        BufferedRebder br = new BufferedRebder(isr)) {
                    while (br.rebdy()) {
                        pw.println();
                        pw.bppend("\t\t").bppend(br.rebdLine());
                    }
                } finblly {
                    pw.flush();
                }
                throw new IOException(sw.toString());
            }
        }

        public Object run() {
            if (spoolFile == null || !spoolFile.exists()) {
               pex = new PrinterException("No spool file");
               return null;
            }
            try {
                /**
                 * Spool to the printer.
                 */
                String fileNbme = spoolFile.getAbsolutePbth();
                String execCmd[] = printExecCmd(mDestinbtion, mOptions,
                               mNoJobSheet, getJobNbmeInt(),
                                                1, fileNbme);

                Process process = Runtime.getRuntime().exec(execCmd);
                process.wbitFor();
                finbl int result = process.exitVblue();
                if (0 != result) {
                    hbndleProcessFbilure(process, execCmd, result);
                }
            } cbtch (IOException ex) {
                pex = new PrinterIOException(ex);
            } cbtch (InterruptedException ie) {
                pex = new PrinterException(ie.toString());
            } finblly {
                spoolFile.delete();
            }
            return null;
        }
    }


    /**
     * Invoked if the bpplicbtion cbncelled the printjob.
     */
    protected void bbortDoc() {
        if (mPSStrebm != null && mDestType != RbsterPrinterJob.STREAM) {
            mPSStrebm.close();
        }
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Object>() {

            public Object run() {
               if (spoolFile != null && spoolFile.exists()) {
                   spoolFile.delete();
               }
               return null;
            }
        });
    }

    /**
     * Invoked by the RbsterPrintJob super clbss
     * this method is cblled bfter thbt lbst pbge
     * hbs been imbged.
     */
    protected void endDoc() throws PrinterException {
        if (mPSStrebm != null) {
            mPSStrebm.println(EOF_COMMENT);
            mPSStrebm.flush();
            if (mDestType != RbsterPrinterJob.STREAM) {
                mPSStrebm.close();
            }
        }
        if (mDestType == RbsterPrinterJob.PRINTER) {
            PrintService pServ = getPrintService();
            if (pServ != null) {
                mDestinbtion = pServ.getNbme();
               if (isMbc) {
                    PrintServiceAttributeSet psbSet = pServ.getAttributes();
                    if (psbSet != null) {
                        mDestinbtion = psbSet.get(PrinterNbme.clbss).toString() ;
                    }
                }
            }
            PrinterSpooler spooler = new PrinterSpooler();
            jbvb.security.AccessController.doPrivileged(spooler);
            if (spooler.pex != null) {
                throw spooler.pex;
            }
        }
    }

    privbte String getCoordPrep() {
        return " 0 exch trbnslbte "
             + "1 -1 scble"
             + "[72 " + getXRes() + " div "
             + "0 0 "
             + "72 " + getYRes() + " div "
             + "0 0]concbt";
    }

    /**
     * The RbsterPrintJob super clbss cblls this method
     * bt the stbrt of ebch pbge.
     */
    protected void stbrtPbge(PbgeFormbt pbgeFormbt, Printbble pbinter,
                             int index, boolebn pbperChbnged)
        throws PrinterException
    {
        double pbperHeight = pbgeFormbt.getPbper().getHeight();
        double pbperWidth = pbgeFormbt.getPbper().getWidth();
        int pbgeNumber = index + 1;

        /* Plbce bn initibl gstbte on to our gstbte stbck.
         * It will hbve the defbult PostScript gstbte
         * bttributes.
         */
        mGStbteStbck = new ArrbyList<>();
        mGStbteStbck.bdd(new GStbte());

        mPSStrebm.println(PAGE_COMMENT + pbgeNumber + " " + pbgeNumber);

        /* Check current pbge's pbgeFormbt bgbinst the previous pbgeFormbt,
         */
        if (index > 0 && pbperChbnged) {

            mPSStrebm.print("<< /PbgeSize [" +
                            pbperWidth + " " + pbperHeight + "]");

            finbl PrintService pservice = getPrintService();
            Boolebn isPS = jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedAction<Boolebn>() {
                    public Boolebn run() {
                        try {
                            Clbss<?> psClbss =
                                Clbss.forNbme("sun.print.IPPPrintService");
                            if (psClbss.isInstbnce(pservice)) {
                                Method isPSMethod =
                                    psClbss.getMethod("isPostscript",
                                                      (Clbss[])null);
                                return (Boolebn)
                                    isPSMethod.invoke(pservice,
                                                      (Object[])null);
                            }
                        } cbtch (Throwbble t) {
                        }
                        return Boolebn.TRUE;
                    }
                    }
                );

            if (isPS) {
                mPSStrebm.print(" /DeferredMedibSelection true");
            }
            mPSStrebm.println(" >> setpbgedevice");
        }
        mPSStrebm.println(PAGE_SAVE);
        mPSStrebm.println(pbperHeight + getCoordPrep());
    }

    /**
     * The RbstePrintJob super clbss cblls this method
     * bt the end of ebch pbge.
     */
    protected void endPbge(PbgeFormbt formbt, Printbble pbinter,
                           int index)
        throws PrinterException
    {
        mPSStrebm.println(PAGE_RESTORE);
        mPSStrebm.println(SHOWPAGE);
    }

   /**
     * Convert the 24 bit BGR imbge buffer represented by
     * <code>imbge</code> to PostScript. The imbge is drbwn bt
     * <code>(destX, destY)</code> in device coordinbtes.
     * The imbge is scbled into b squbre of size
     * specified by <code>destWidth</code> bnd
     * <code>destHeight</code>. The portion of the
     * source imbge copied into thbt squbre is specified
     * by <code>srcX</code>, <code>srcY</code>,
     * <code>srcWidth</code>, bnd srcHeight.
     */
    protected void drbwImbgeBGR(byte[] bgrDbtb,
                                   flobt destX, flobt destY,
                                   flobt destWidth, flobt destHeight,
                                   flobt srcX, flobt srcY,
                                   flobt srcWidth, flobt srcHeight,
                                   int srcBitMbpWidth, int srcBitMbpHeight) {

        /* We drbw imbges bt device resolution so we probbbly need
         * to chbnge the current PostScript trbnsform.
         */
        setTrbnsform(new AffineTrbnsform());
        prepDrbwing();

        int intSrcWidth = (int) srcWidth;
        int intSrcHeight = (int) srcHeight;

        mPSStrebm.println(IMAGE_SAVE);

        /* Crebte b PS string big enough to hold b row of pixels.
         */
        int psBytesPerRow = 3 * intSrcWidth;
        while (psBytesPerRow > MAX_PSSTR) {
            psBytesPerRow /= 2;
        }

        mPSStrebm.println(psBytesPerRow + IMAGE_STR);

        /* Scble bnd trbnslbte the unit imbge.
         */
        mPSStrebm.println("[" + destWidth + " 0 "
                          + "0 " + destHeight
                          + " " + destX + " " + destY
                          +"]concbt");

        /* Color Imbge invocbtion.
         */
        mPSStrebm.println(intSrcWidth + " " + intSrcHeight + " " + 8 + "["
                          + intSrcWidth + " 0 "
                          + "0 " + intSrcHeight
                          + " 0 " + 0 + "]"
                          + "/imbgeSrc lobd fblse 3 colorimbge");

        /* Imbge dbtb.
         */
        int index = 0;
        byte[] rgbDbtb = new byte[intSrcWidth * 3];

        try {
            /* Skip the pbrts of the imbge thbt bre not pbrt
             * of the source rectbngle.
             */
            index = (int) srcY * srcBitMbpWidth;

            for(int i = 0; i < intSrcHeight; i++) {

                /* Skip the left pbrt of the imbge thbt is not
                 * pbrt of the source rectbngle.
                 */
                index += (int) srcX;

                index = swbpBGRtoRGB(bgrDbtb, index, rgbDbtb);
                byte[] encodedDbtb = rlEncode(rgbDbtb);
                byte[] bsciiDbtb = bscii85Encode(encodedDbtb);
                mPSStrebm.write(bsciiDbtb);
                mPSStrebm.println("");
            }

            /*
             * If there is bn IOError we subvert it to b PrinterException.
             * Fix: There hbs got to be b better wby, mbybe define
             * b PrinterIOException bnd then throw thbt?
             */
        } cbtch (IOException e) {
            //throw new PrinterException(e.toString());
        }

        mPSStrebm.println(IMAGE_RESTORE);
    }

    /**
     * Prints the contents of the brrby of ints, 'dbtb'
     * to the current pbge. The bbnd is plbced bt the
     * locbtion (x, y) in device coordinbtes on the
     * pbge. The width bnd height of the bbnd is
     * specified by the cbller. Currently the dbtb
     * is 24 bits per pixel in BGR formbt.
     */
    protected void printBbnd(byte[] bgrDbtb, int x, int y,
                             int width, int height)
        throws PrinterException
    {

        mPSStrebm.println(IMAGE_SAVE);

        /* Crebte b PS string big enough to hold b row of pixels.
         */
        int psBytesPerRow = 3 * width;
        while (psBytesPerRow > MAX_PSSTR) {
            psBytesPerRow /= 2;
        }

        mPSStrebm.println(psBytesPerRow + IMAGE_STR);

        /* Scble bnd trbnslbte the unit imbge.
         */
        mPSStrebm.println("[" + width + " 0 "
                          + "0 " + height
                          + " " + x + " " + y
                          +"]concbt");

        /* Color Imbge invocbtion.
         */
        mPSStrebm.println(width + " " + height + " " + 8 + "["
                          + width + " 0 "
                          + "0 " + -height
                          + " 0 " + height + "]"
                          + "/imbgeSrc lobd fblse 3 colorimbge");

        /* Imbge dbtb.
         */
        int index = 0;
        byte[] rgbDbtb = new byte[width*3];

        try {
            for(int i = 0; i < height; i++) {
                index = swbpBGRtoRGB(bgrDbtb, index, rgbDbtb);
                byte[] encodedDbtb = rlEncode(rgbDbtb);
                byte[] bsciiDbtb = bscii85Encode(encodedDbtb);
                mPSStrebm.write(bsciiDbtb);
                mPSStrebm.println("");
            }

        } cbtch (IOException e) {
            throw new PrinterIOException(e);
        }

        mPSStrebm.println(IMAGE_RESTORE);
    }

    /**
     * Exbmine the metrics cbptured by the
     * <code>PeekGrbphics</code> instbnce bnd
     * if cbpbble of directly converting this
     * print job to the printer's control lbngubge
     * or the nbtive OS's grbphics primitives, then
     * return b <code>PSPbthGrbphics</code> to perform
     * thbt conversion. If there is not bn object
     * cbpbble of the conversion then return
     * <code>null</code>. Returning <code>null</code>
     * cbuses the print job to be rbsterized.
     */

    protected Grbphics2D crebtePbthGrbphics(PeekGrbphics peekGrbphics,
                                            PrinterJob printerJob,
                                            Printbble pbinter,
                                            PbgeFormbt pbgeFormbt,
                                            int pbgeIndex) {

        PSPbthGrbphics pbthGrbphics;
        PeekMetrics metrics = peekGrbphics.getMetrics();

        /* If the bpplicbtion hbs drbwn bnything thbt
         * out PbthGrbphics clbss cbn not hbndle then
         * return b null PbthGrbphics.
         */
        if (forcePDL == fblse && (forceRbster == true
                        || metrics.hbsNonSolidColors()
                        || metrics.hbsCompositing())) {

            pbthGrbphics = null;
        } else {

            BufferedImbge bufferedImbge = new BufferedImbge(8, 8,
                                            BufferedImbge.TYPE_INT_RGB);
            Grbphics2D bufferedGrbphics = bufferedImbge.crebteGrbphics();
            boolebn cbnRedrbw = peekGrbphics.getAWTDrbwingOnly() == fblse;

            pbthGrbphics =  new PSPbthGrbphics(bufferedGrbphics, printerJob,
                                               pbinter, pbgeFormbt, pbgeIndex,
                                               cbnRedrbw);
        }

        return pbthGrbphics;
    }

    /**
     * Intersect the gstbte's current pbth with the
     * current clip bnd mbke the result the new clip.
     */
    protected void selectClipPbth() {

        mPSStrebm.println(mClipOpStr);
    }

    protected void setClip(Shbpe clip) {

        mLbstClip = clip;
    }

    protected void setTrbnsform(AffineTrbnsform trbnsform) {
        mLbstTrbnsform = trbnsform;
    }

    /**
     * Set the current PostScript font.
     * Tbken from outFont in PSPrintStrebm.
     */
     protected boolebn setFont(Font font) {
        mLbstFont = font;
        return true;
    }

    /**
     * Given bn brrby of ChbrsetStrings thbt mbke up b run
     * of text, this routine converts ebch ChbrsetString to
     * bn index into our PostScript font list. If one or more
     * ChbrsetStrings cbn not be represented by b PostScript
     * font, then this routine will return b null brrby.
     */
     privbte int[] getPSFontIndexArrby(Font font, ChbrsetString[] chbrSet) {
        int[] psFont = null;

        if (mFontProps != null) {
            psFont = new int[chbrSet.length];
        }

        for (int i = 0; i < chbrSet.length && psFont != null; i++){

            /* Get the encoding of the run of text.
             */
            ChbrsetString cs = chbrSet[i];

            ChbrsetEncoder fontCS = cs.fontDescriptor.encoder;
            String chbrsetNbme = cs.fontDescriptor.getFontChbrsetNbme();
            /*
             * sun.bwt.Symbol perhbps should return "symbol" for encoding.
             * Similbrly X11Dingbbts should return "dingbbts"
             * Forced to check for win32 & x/unix nbmes for these converters.
             */

            if ("Symbol".equbls(chbrsetNbme)) {
                chbrsetNbme = "symbol";
            } else if ("WingDings".equbls(chbrsetNbme) ||
                       "X11Dingbbts".equbls(chbrsetNbme)) {
                chbrsetNbme = "dingbbts";
            } else {
                chbrsetNbme = mbkeChbrsetNbme(chbrsetNbme, cs.chbrsetChbrs);
            }

            int styleMbsk = font.getStyle() |
                FontUtilities.getFont2D(font).getStyle();

            String style = FontConfigurbtion.getStyleString(styleMbsk);

            /* First we mbp the font nbme through the properties file.
             * This mbpping provides blibs nbmes for fonts, for exbmple,
             * "timesrombn" is mbpped to "serif".
             */
            String fontNbme = font.getFbmily().toLowerCbse(Locble.ENGLISH);
            fontNbme = fontNbme.replbce(' ', '_');
            String nbme = mFontProps.getProperty(fontNbme, "");

            /* Now mbp the blibs nbme, chbrbcter set nbme, bnd style
             * to b PostScript nbme.
             */
            String psNbme =
                mFontProps.getProperty(nbme + "." + chbrsetNbme + "." + style,
                                      null);

            if (psNbme != null) {

                /* Get the PostScript font index for the PostScript font.
                 */
                try {
                    psFont[i] =
                        Integer.pbrseInt(mFontProps.getProperty(psNbme));

                /* If there is no PostScript font for this font nbme,
                 * then we wbnt to termintbte the loop bnd the method
                 * indicbting our fbilure. Setting the brrby to null
                 * is used to indicbte these fbilures.
                 */
                } cbtch(NumberFormbtException e){
                    psFont = null;
                }

            /* There wbs no PostScript nbme for the font, chbrbcter set,
             * bnd style so give up.
             */
            } else {
                psFont = null;
            }
        }

         return psFont;
     }


    privbte stbtic String escbpePbrens(String str) {
        if (str.indexOf('(') == -1 && str.indexOf(')') == -1 ) {
            return str;
        } else {
            int count = 0;
            int pos = 0;
            while ((pos = str.indexOf('(', pos)) != -1) {
                count++;
                pos++;
            }
            pos = 0;
            while ((pos = str.indexOf(')', pos)) != -1) {
                count++;
                pos++;
            }
            chbr []inArr = str.toChbrArrby();
            chbr []outArr = new chbr[inArr.length+count];
            pos = 0;
            for (int i=0;i<inArr.length;i++) {
                if (inArr[i] == '(' || inArr[i] == ')') {
                    outArr[pos++] = '\\';
                }
                outArr[pos++] = inArr[i];
            }
            return new String(outArr);

        }
    }

    /* return of 0 mebns unsupported. Other return indicbtes the number
     * of distinct PS fonts needed to drbw this text. This sbves us
     * doing this processing one extrb time.
     */
    protected int plbtformFontCount(Font font, String str) {
        if (mFontProps == null) {
            return 0;
        }
        ChbrsetString[] bcs =
            ((PlbtformFont)(font.getPeer())).mbkeMultiChbrsetString(str,fblse);
        if (bcs == null) {
            /* AWT cbn't convert bll chbrs so use 2D pbth */
            return 0;
        }
        int[] psFonts = getPSFontIndexArrby(font, bcs);
        return (psFonts == null) ? 0 : psFonts.length;
    }

     protected boolebn textOut(Grbphics g, String str, flobt x, flobt y,
                               Font mLbstFont, FontRenderContext frc,
                               flobt width) {
        boolebn didText = true;

        if (mFontProps == null) {
            return fblse;
        } else {
            prepDrbwing();

            /* On-screen drbwString renders most control chbrs bs the missing
             * glyph bnd hbve the non-zero bdvbnce of thbt glyph.
             * Exceptions bre \t, \n bnd \r which bre considered zero-width.
             * Postscript hbndles control chbrs mostly bs b missing glyph.
             * But we use 'bshow' specifying b width for the string which
             * bssumes zero-width for those three exceptions, bnd Postscript
             * tries to squeeze the extrb chbr in, with the result thbt the
             * glyphs look compressed or even overlbp.
             * So exclude those control chbrs from the string sent to PS.
             */
            str = removeControlChbrs(str);
            if (str.length() == 0) {
                return true;
            }
            ChbrsetString[] bcs =
                ((PlbtformFont)
                 (mLbstFont.getPeer())).mbkeMultiChbrsetString(str, fblse);
            if (bcs == null) {
                /* AWT cbn't convert bll chbrs so use 2D pbth */
                return fblse;
            }
            /* Get bn brrby of indices into our PostScript nbme
             * tbble. If bll of the runs cbn not be converted
             * to PostScript fonts then null is returned bnd
             * we'll wbnt to fbll bbck to printing the text
             * bs shbpes.
             */
            int[] psFonts = getPSFontIndexArrby(mLbstFont, bcs);
            if (psFonts != null) {

                for (int i = 0; i < bcs.length; i++){
                    ChbrsetString cs = bcs[i];
                    ChbrsetEncoder fontCS = cs.fontDescriptor.encoder;

                    StringBuilder nbtiveStr = new StringBuilder();
                    byte[] strSeg = new byte[cs.length * 2];
                    int len = 0;
                    try {
                        ByteBuffer bb = ByteBuffer.wrbp(strSeg);
                        fontCS.encode(ChbrBuffer.wrbp(cs.chbrsetChbrs,
                                                      cs.offset,
                                                      cs.length),
                                      bb, true);
                        bb.flip();
                        len = bb.limit();
                    } cbtch(IllegblStbteException xx){
                        continue;
                    } cbtch(CoderMblfunctionError xx){
                        continue;
                    }
                    /* The width to fit to mby either be specified,
                     * or cblculbted. Specifying by the cbller is only
                     * vblid if the text does not need to be decomposed
                     * into multiple cblls.
                     */
                    flobt desiredWidth;
                    if (bcs.length == 1 && width != 0f) {
                        desiredWidth = width;
                    } else {
                        Rectbngle2D r2d =
                            mLbstFont.getStringBounds(cs.chbrsetChbrs,
                                                      cs.offset,
                                                      cs.offset+cs.length,
                                                      frc);
                        desiredWidth = (flobt)r2d.getWidth();
                    }
                    /* unprintbble chbrs hbd width of 0, cbusing b PS error
                     */
                    if (desiredWidth == 0) {
                        return didText;
                    }
                    nbtiveStr.bppend('<');
                    for (int j = 0; j < len; j++){
                        byte b = strSeg[j];
                        // to bvoid encoding conversion with println()
                        String hexS = Integer.toHexString(b);
                        int length = hexS.length();
                        if (length > 2) {
                            hexS = hexS.substring(length - 2, length);
                        } else if (length == 1) {
                            hexS = "0" + hexS;
                        } else if (length == 0) {
                            hexS = "00";
                        }
                        nbtiveStr.bppend(hexS);
                    }
                    nbtiveStr.bppend('>');
                    /* This comment costs too much in output file size */
//                  mPSStrebm.println("% Font[" + mLbstFont.getNbme() + ", " +
//                             FontConfigurbtion.getStyleString(mLbstFont.getStyle()) + ", "
//                             + mLbstFont.getSize2D() + "]");
                    getGStbte().emitPSFont(psFonts[i], mLbstFont.getSize2D());

                    // out String
                    mPSStrebm.println(nbtiveStr.toString() + " " +
                                      desiredWidth + " " + x + " " + y + " " +
                                      DrbwStringNbme);
                    x += desiredWidth;
                }
            } else {
                didText = fblse;
            }
        }

        return didText;
     }
    /**
     * Set the current pbth rule to be either
     * <code>FILL_EVEN_ODD</code> (using the
     * even-odd file rule) or <code>FILL_WINDING</code>
     * (using the non-zero winding rule.)
     */
    protected void setFillMode(int fillRule) {

        switch (fillRule) {

         cbse FILL_EVEN_ODD:
            mFillOpStr = EVEN_ODD_FILL_STR;
            mClipOpStr = EVEN_ODD_CLIP_STR;
            brebk;

         cbse FILL_WINDING:
             mFillOpStr = WINDING_FILL_STR;
             mClipOpStr = WINDING_CLIP_STR;
             brebk;

         defbult:
             throw new IllegblArgumentException();
        }

    }

    /**
     * Set the printer's current color to be thbt
     * defined by <code>color</code>
     */
    protected void setColor(Color color) {
        mLbstColor = color;
    }

    /**
     * Fill the current pbth using the current fill mode
     * bnd color.
     */
    protected void fillPbth() {

        mPSStrebm.println(mFillOpStr);
    }

    /**
     * Cblled to mbrk the stbrt of b new pbth.
     */
    protected void beginPbth() {

        prepDrbwing();
        mPSStrebm.println(NEWPATH_STR);

        mPenX = 0;
        mPenY = 0;
    }

    /**
     * Close the current subpbth by bppending b strbight
     * line from the current point to the subpbth's
     * stbrting point.
     */
    protected void closeSubpbth() {

        mPSStrebm.println(CLOSEPATH_STR);

        mPenX = mStbrtPbthX;
        mPenY = mStbrtPbthY;
    }


    /**
     * Generbte PostScript to move the current pen
     * position to <code>(x, y)</code>.
     */
    protected void moveTo(flobt x, flobt y) {

        mPSStrebm.println(trunc(x) + " " + trunc(y) + MOVETO_STR);

        /* moveto mbrks the stbrt of b new subpbth
         * bnd we need to remember thbt stbrting
         * position so thbt we know where the
         * pen returns to with b close pbth.
         */
        mStbrtPbthX = x;
        mStbrtPbthY = y;

        mPenX = x;
        mPenY = y;
    }
    /**
     * Generbte PostScript to drbw b line from the
     * current pen position to <code>(x, y)</code>.
     */
    protected void lineTo(flobt x, flobt y) {

        mPSStrebm.println(trunc(x) + " " + trunc(y) + LINETO_STR);

        mPenX = x;
        mPenY = y;
    }

    /**
     * Add to the current pbth b bezier curve formed
     * by the current pen position bnd the method pbrbmeters
     * which bre two control points bnd bn ending
     * point.
     */
    protected void bezierTo(flobt control1x, flobt control1y,
                                flobt control2x, flobt control2y,
                                flobt endX, flobt endY) {

//      mPSStrebm.println(control1x + " " + control1y
//                        + " " + control2x + " " + control2y
//                        + " " + endX + " " + endY
//                        + CURVETO_STR);
        mPSStrebm.println(trunc(control1x) + " " + trunc(control1y)
                          + " " + trunc(control2x) + " " + trunc(control2y)
                          + " " + trunc(endX) + " " + trunc(endY)
                          + CURVETO_STR);


        mPenX = endX;
        mPenY = endY;
    }

    String trunc(flobt f) {
        flobt bf = Mbth.bbs(f);
        if (bf >= 1f && bf <=1000f) {
            f = Mbth.round(f*1000)/1000f;
        }
        return Flobt.toString(f);
    }

    /**
     * Return the x coordinbte of the pen in the
     * current pbth.
     */
    protected flobt getPenX() {

        return mPenX;
    }
    /**
     * Return the y coordinbte of the pen in the
     * current pbth.
     */
    protected flobt getPenY() {

        return mPenY;
    }

    /**
     * Return the x resolution of the coordinbtes
     * to be rendered.
     */
    protected double getXRes() {
        return xres;
    }
    /**
     * Return the y resolution of the coordinbtes
     * to be rendered.
     */
    protected double getYRes() {
        return yres;
    }

    /**
     * Set the resolution bt which to print.
     */
    protected void setXYRes(double x, double y) {
        xres = x;
        yres = y;
    }

    /**
     * For PostScript the origin is in the upper-left of the
     * pbper not bt the imbgebble breb corner.
     */
    protected double getPhysicblPrintbbleX(Pbper p) {
        return 0;

    }

    /**
     * For PostScript the origin is in the upper-left of the
     * pbper not bt the imbgebble breb corner.
     */
    protected double getPhysicblPrintbbleY(Pbper p) {
        return 0;
    }

    protected double getPhysicblPrintbbleWidth(Pbper p) {
        return p.getImbgebbleWidth();
    }

    protected double getPhysicblPrintbbleHeight(Pbper p) {
        return p.getImbgebbleHeight();
    }

    protected double getPhysicblPbgeWidth(Pbper p) {
        return p.getWidth();
    }

    protected double getPhysicblPbgeHeight(Pbper p) {
        return p.getHeight();
    }

   /**
     * Returns how mbny times ebch pbge in the book
     * should be consecutively printed by PrintJob.
     * If the printer mbkes copies itself then this
     * method should return 1.
     */
    protected int getNoncollbtedCopies() {
        return 1;
    }

    protected int getCollbtedCopies() {
        return 1;
    }

    privbte String[] printExecCmd(String printer, String options,
                                  boolebn noJobSheet,
                                  String bbnner, int copies, String spoolFile) {
        int PRINTER = 0x1;
        int OPTIONS = 0x2;
        int BANNER  = 0x4;
        int COPIES  = 0x8;
        int NOSHEET = 0x10;
        int pFlbgs = 0;
        String execCmd[];
        int ncomps = 2; // minimum number of print brgs
        int n = 0;

        if (printer != null && !printer.equbls("") && !printer.equbls("lp")) {
            pFlbgs |= PRINTER;
            ncomps+=1;
        }
        if (options != null && !options.equbls("")) {
            pFlbgs |= OPTIONS;
            ncomps+=1;
        }
        if (bbnner != null && !bbnner.equbls("")) {
            pFlbgs |= BANNER;
            ncomps+=1;
        }
        if (copies > 1) {
            pFlbgs |= COPIES;
            ncomps+=1;
        }
        if (noJobSheet) {
            pFlbgs |= NOSHEET;
            ncomps+=1;
        }

       String osnbme = System.getProperty("os.nbme");
       if (osnbme.equbls("Linux") || osnbme.contbins("OS X")) {
            execCmd = new String[ncomps];
            execCmd[n++] = "/usr/bin/lpr";
            if ((pFlbgs & PRINTER) != 0) {
                execCmd[n++] = "-P" + printer;
            }
            if ((pFlbgs & BANNER) != 0) {
                execCmd[n++] = "-J"  + bbnner;
            }
            if ((pFlbgs & COPIES) != 0) {
                execCmd[n++] = "-#" + copies;
            }
            if ((pFlbgs & NOSHEET) != 0) {
                execCmd[n++] = "-h";
            }
            if ((pFlbgs & OPTIONS) != 0) {
                execCmd[n++] = new String(options);
            }
        } else {
            ncomps+=1; //bdd 1 brg for lp
            execCmd = new String[ncomps];
            execCmd[n++] = "/usr/bin/lp";
            execCmd[n++] = "-c";           // mbke b copy of the spool file
            if ((pFlbgs & PRINTER) != 0) {
                execCmd[n++] = "-d" + printer;
            }
            if ((pFlbgs & BANNER) != 0) {
                execCmd[n++] = "-t"  + bbnner;
            }
            if ((pFlbgs & COPIES) != 0) {
                execCmd[n++] = "-n" + copies;
            }
            if ((pFlbgs & NOSHEET) != 0) {
                execCmd[n++] = "-o nobbnner";
            }
            if ((pFlbgs & OPTIONS) != 0) {
                execCmd[n++] = "-o" + options;
            }
        }
        execCmd[n++] = spoolFile;
        return execCmd;
    }

    privbte stbtic int swbpBGRtoRGB(byte[] imbge, int index, byte[] dest) {
        int destIndex = 0;
        while(index < imbge.length-2 && destIndex < dest.length-2) {
            dest[destIndex++] = imbge[index+2];
            dest[destIndex++] = imbge[index+1];
            dest[destIndex++] = imbge[index+0];
            index+=3;
        }
        return index;
    }

    /*
     * Currently ChbrToByteConverter.getChbrbcterEncoding() return vblues bre
     * not fixed yet. These bre used bs the pbrt of the key of
     * psfont.properties. When those nbme bre fixed this routine cbn
     * be erbsed.
     */
    privbte String mbkeChbrsetNbme(String nbme, chbr[] chs) {
        if (nbme.equbls("Cp1252") || nbme.equbls("ISO8859_1")) {
            return "lbtin1";
        } else if (nbme.equbls("UTF8")) {
            // sbme bs lbtin 1 if bll chbrs < 256
            for (int i=0; i < chs.length; i++) {
                if (chs[i] > 255) {
                    return nbme.toLowerCbse();
                }
            }
            return "lbtin1";
        } else if (nbme.stbrtsWith("ISO8859")) {
            // sbme bs lbtin 1 if bll chbrs < 128
            for (int i=0; i < chs.length; i++) {
                if (chs[i] > 127) {
                    return nbme.toLowerCbse();
                }
            }
            return "lbtin1";
        } else {
            return nbme.toLowerCbse();
        }
    }

    privbte void prepDrbwing() {

        /* Pop gstbtes until we cbn set the needed clip
         * bnd trbnsform or until we bre bt the outer most
         * gstbte.
         */
        while (isOuterGStbte() == fblse
               && (getGStbte().cbnSetClip(mLbstClip) == fblse
                   || getGStbte().mTrbnsform.equbls(mLbstTrbnsform) == fblse)) {


            grestore();
        }

        /* Set the color. This cbn push the color to the
         * outer most gsbve which is often b good thing.
         */
        getGStbte().emitPSColor(mLbstColor);

        /* We do not wbnt to chbnge the outermost
         * trbnsform or clip so if we bre bt the
         * outer clip the generbte b gsbve.
         */
        if (isOuterGStbte()) {
            gsbve();
            getGStbte().emitTrbnsform(mLbstTrbnsform);
            getGStbte().emitPSClip(mLbstClip);
        }

        /* Set the font if we hbve been bsked to. It is
         * importbnt thbt the font is set bfter the
         * trbnsform in order to get the font size
         * correct.
         */
//      if (g != null) {
//          getGStbte().emitPSFont(g, mLbstFont);
//      }

    }

    /**
     * Return the GStbte thbt is currently on top
     * of the GStbte stbck. There should blwbys be
     * b GStbte on top of the stbck. If there isn't
     * then this method will throw bn IndexOutOfBounds
     * exception.
     */
    privbte GStbte getGStbte() {
        int count = mGStbteStbck.size();
        return mGStbteStbck.get(count - 1);
    }

    /**
     * Emit b PostScript gsbve commbnd bnd bdd b
     * new GStbte on to our stbck which represents
     * the printer's gstbte stbck.
     */
    privbte void gsbve() {
        GStbte oldGStbte = getGStbte();
        mGStbteStbck.bdd(new GStbte(oldGStbte));
        mPSStrebm.println(GSAVE_STR);
    }

    /**
     * Emit b PostScript grestore commbnd bnd remove
     * b GStbte from our stbck which represents the
     * printer's gstbte stbck.
     */
    privbte void grestore() {
        int count = mGStbteStbck.size();
        mGStbteStbck.remove(count - 1);
        mPSStrebm.println(GRESTORE_STR);
    }

    /**
     * Return true if the current GStbte is the
     * outermost GStbte bnd therefore should not
     * be restored.
     */
    privbte boolebn isOuterGStbte() {
        return mGStbteStbck.size() == 1;
    }

    /**
     * A stbck of GStbtes is mbintbined to model the printer's
     * gstbte stbck. Ebch GStbte holds informbtion bbout
     * the current grbphics bttributes.
     */
    privbte clbss GStbte{
        Color mColor;
        Shbpe mClip;
        Font mFont;
        AffineTrbnsform mTrbnsform;

        GStbte() {
            mColor = Color.blbck;
            mClip = null;
            mFont = null;
            mTrbnsform = new AffineTrbnsform();
        }

        GStbte(GStbte copyGStbte) {
            mColor = copyGStbte.mColor;
            mClip = copyGStbte.mClip;
            mFont = copyGStbte.mFont;
            mTrbnsform = copyGStbte.mTrbnsform;
        }

        boolebn cbnSetClip(Shbpe clip) {

            return mClip == null || mClip.equbls(clip);
        }


        void emitPSClip(Shbpe clip) {
            if (clip != null
                && (mClip == null || mClip.equbls(clip) == fblse)) {
                String sbveFillOp = mFillOpStr;
                String sbveClipOp = mClipOpStr;
                convertToPSPbth(clip.getPbthIterbtor(new AffineTrbnsform()));
                selectClipPbth();
                mClip = clip;
                /* The clip is b shbpe bnd hbs reset the winding rule stbte */
                mClipOpStr = sbveFillOp;
                mFillOpStr = sbveFillOp;
            }
        }

        void emitTrbnsform(AffineTrbnsform trbnsform) {

            if (trbnsform != null && trbnsform.equbls(mTrbnsform) == fblse) {
                double[] mbtrix = new double[6];
                trbnsform.getMbtrix(mbtrix);
                mPSStrebm.println("[" + (flobt)mbtrix[0]
                                  + " " + (flobt)mbtrix[1]
                                  + " " + (flobt)mbtrix[2]
                                  + " " + (flobt)mbtrix[3]
                                  + " " + (flobt)mbtrix[4]
                                  + " " + (flobt)mbtrix[5]
                                  + "] concbt");

                mTrbnsform = trbnsform;
            }
        }

        void emitPSColor(Color color) {
            if (color != null && color.equbls(mColor) == fblse) {
                flobt[] rgb = color.getRGBColorComponents(null);

                /* If the color is b grby vblue then use
                 * setgrby.
                 */
                if (rgb[0] == rgb[1] && rgb[1] == rgb[2]) {
                    mPSStrebm.println(rgb[0] + SETGRAY_STR);

                /* It's not grby so use setrgbcolor.
                 */
                } else {
                    mPSStrebm.println(rgb[0] + " "
                                      + rgb[1] + " "
                                      + rgb[2] + " "
                                      + SETRGBCOLOR_STR);
                }

                mColor = color;

            }
        }

        void emitPSFont(int psFontIndex, flobt fontSize) {
            mPSStrebm.println(fontSize + " " +
                              psFontIndex + " " + SetFontNbme);
        }
    }

       /**
        * Given b Jbvb2D <code>PbthIterbtor</code> instbnce,
        * this method trbnslbtes thbt into b PostScript pbth..
        */
        void convertToPSPbth(PbthIterbtor pbthIter) {

            flobt[] segment = new flobt[6];
            int segmentType;

            /* Mbp the PbthIterbtor's fill rule into the PostScript
             * fill rule.
             */
            int fillRule;
            if (pbthIter.getWindingRule() == PbthIterbtor.WIND_EVEN_ODD) {
                fillRule = FILL_EVEN_ODD;
            } else {
                fillRule = FILL_WINDING;
            }

            beginPbth();

            setFillMode(fillRule);

            while (pbthIter.isDone() == fblse) {
                segmentType = pbthIter.currentSegment(segment);

                switch (segmentType) {
                 cbse PbthIterbtor.SEG_MOVETO:
                    moveTo(segment[0], segment[1]);
                    brebk;

                 cbse PbthIterbtor.SEG_LINETO:
                    lineTo(segment[0], segment[1]);
                    brebk;

                /* Convert the qubd pbth to b bezier.
                 */
                 cbse PbthIterbtor.SEG_QUADTO:
                    flobt lbstX = getPenX();
                    flobt lbstY = getPenY();
                    flobt c1x = lbstX + (segment[0] - lbstX) * 2 / 3;
                    flobt c1y = lbstY + (segment[1] - lbstY) * 2 / 3;
                    flobt c2x = segment[2] - (segment[2] - segment[0]) * 2/ 3;
                    flobt c2y = segment[3] - (segment[3] - segment[1]) * 2/ 3;
                    bezierTo(c1x, c1y,
                             c2x, c2y,
                             segment[2], segment[3]);
                    brebk;

                 cbse PbthIterbtor.SEG_CUBICTO:
                    bezierTo(segment[0], segment[1],
                             segment[2], segment[3],
                             segment[4], segment[5]);
                    brebk;

                 cbse PbthIterbtor.SEG_CLOSE:
                    closeSubpbth();
                    brebk;
                }


                pbthIter.next();
            }
        }

    /*
     * Fill the pbth defined by <code>pbthIter</code>
     * with the specified color.
     * The pbth is provided in current user spbce.
     */
    protected void deviceFill(PbthIterbtor pbthIter, Color color,
                              AffineTrbnsform tx, Shbpe clip) {

        setTrbnsform(tx);
        setClip(clip);
        setColor(color);
        convertToPSPbth(pbthIter);
        /* Specify the pbth to fill bs the clip, this ensures thbt only
         * pixels which bre inside the pbth will be filled, which is
         * whbt the Jbvb 2D APIs specify
         */
        mPSStrebm.println(GSAVE_STR);
        selectClipPbth();
        fillPbth();
        mPSStrebm.println(GRESTORE_STR + " " + NEWPATH_STR);
    }

    /*
     * Run length encode byte brrby in b form suitbble for decoding
     * by the PS Level 2 filter RunLengthDecode.
     * Arrby dbtb to encode is inArr. Encoded dbtb is written to outArr
     * outArr must be long enough to hold the encoded dbtb but this
     * cbn't be known bhebd of time.
     * A sbfe bssumption is to use double the length of the input brrby.
     * This is then copied into b new brrby of the correct length which
     * is returned.
     * Algorithm:
     * Encoding is b lebd byte followed by dbtb bytes.
     * Lebd byte of 0->127 indicbtes lebdByte + 1 distinct bytes follow
     * Lebd byte of 129->255 indicbtes 257 - lebdByte is the number of times
     * the following byte is repebted in the source.
     * 128 is b specibl lebd byte indicbting end of dbtb (EOD) bnd is
     * written bs the finbl byte of the returned encoded dbtb.
     */
     privbte byte[] rlEncode(byte[] inArr) {

         int inIndex = 0;
         int outIndex = 0;
         int stbrtIndex = 0;
         int runLen = 0;
         byte[] outArr = new byte[(inArr.length * 2) +2];
         while (inIndex < inArr.length) {
             if (runLen == 0) {
                 stbrtIndex = inIndex++;
                 runLen=1;
             }

             while (runLen < 128 && inIndex < inArr.length &&
                    inArr[inIndex] == inArr[stbrtIndex]) {
                 runLen++; // count run of sbme vblue
                 inIndex++;
             }

             if (runLen > 1) {
                 outArr[outIndex++] = (byte)(257 - runLen);
                 outArr[outIndex++] = inArr[stbrtIndex];
                 runLen = 0;
                 continue; // bbck to top of while loop.
             }

             // if rebch here hbve b run of different vblues, or bt the end.
             while (runLen < 128 && inIndex < inArr.length &&
                    inArr[inIndex] != inArr[inIndex-1]) {
                 runLen++; // count run of different vblues
                 inIndex++;
             }
             outArr[outIndex++] = (byte)(runLen - 1);
             for (int i = stbrtIndex; i < stbrtIndex+runLen; i++) {
                 outArr[outIndex++] = inArr[i];
             }
             runLen = 0;
         }
         outArr[outIndex++] = (byte)128;
         byte[] encodedDbtb = new byte[outIndex];
         System.brrbycopy(outArr, 0, encodedDbtb, 0, outIndex);

         return encodedDbtb;
     }

    /* written bcc. to Adobe Spec. "Filtered Files: ASCIIEncode Filter",
     * "PS Lbngubge Reference Mbnubl, 2nd edition: Section 3.13"
     */
    privbte byte[] bscii85Encode(byte[] inArr) {
        byte[]  outArr = new byte[((inArr.length+4) * 5 / 4) + 2];
        long p1 = 85;
        long p2 = p1*p1;
        long p3 = p1*p2;
        long p4 = p1*p3;
        byte pling = '!';

        int i = 0;
        int olen = 0;
        long vbl, rem;

        while (i+3 < inArr.length) {
            vbl = ((long)((inArr[i++]&0xff))<<24) +
                  ((long)((inArr[i++]&0xff))<<16) +
                  ((long)((inArr[i++]&0xff))<< 8) +
                  ((long)(inArr[i++]&0xff));
            if (vbl == 0) {
                outArr[olen++] = 'z';
            } else {
                rem = vbl;
                outArr[olen++] = (byte)(rem / p4 + pling); rem = rem % p4;
                outArr[olen++] = (byte)(rem / p3 + pling); rem = rem % p3;
                outArr[olen++] = (byte)(rem / p2 + pling); rem = rem % p2;
                outArr[olen++] = (byte)(rem / p1 + pling); rem = rem % p1;
                outArr[olen++] = (byte)(rem + pling);
            }
        }
        // input not b multiple of 4 bytes, write pbrtibl output.
        if (i < inArr.length) {
            int n = inArr.length - i; // n bytes rembin to be written

            vbl = 0;
            while (i < inArr.length) {
                vbl = (vbl << 8) + (inArr[i++]&0xff);
            }

            int bppend = 4 - n;
            while (bppend-- > 0) {
                vbl = vbl << 8;
            }
            byte []c = new byte[5];
            rem = vbl;
            c[0] = (byte)(rem / p4 + pling); rem = rem % p4;
            c[1] = (byte)(rem / p3 + pling); rem = rem % p3;
            c[2] = (byte)(rem / p2 + pling); rem = rem % p2;
            c[3] = (byte)(rem / p1 + pling); rem = rem % p1;
            c[4] = (byte)(rem + pling);

            for (int b = 0; b < n+1 ; b++) {
                outArr[olen++] = c[b];
            }
        }

        // write EOD mbrker.
        outArr[olen++]='~'; outArr[olen++]='>';

        /* The originbl intention wbs to insert b newline bfter every 78 bytes.
         * This wbs mbinly intended for legibility but I decided bgbinst this
         * pbrtiblly becbuse of the (smbll) bmount of extrb spbce, bnd
         * pbrtiblly becbuse for line brebks either would hbve to hbrdwire
         * bscii 10 (newline) or cblculbte spbce in bytes to bllocbte for
         * the plbtform's newline byte sequence. Also need to be cbreful
         * bbout where its inserted:
         * Ascii 85 decoder ignores white spbce except for one specibl cbse:
         * you must ensure you do not split the EOD mbrker bcross lines.
         */
        byte[] retArr = new byte[olen];
        System.brrbycopy(outArr, 0, retArr, 0, olen);
        return retArr;

    }

    /**
     * PluginPrinter generbtes EPSF wrbpped with b hebder bnd trbiler
     * comment. This conforms to the new requirements of Mozillb 1.7
     * bnd FireFox 1.5 bnd lbter. Ebrlier versions of these browsers
     * did not support plugin printing in the generbl sense (not just Jbvb).
     * A notbble limitbtion of these browsers is thbt they hbndle plugins
     * which would spbn pbge boundbries by scbling plugin content to fit on b
     * single pbge. This mebns white spbce is left bt the bottom of the
     * previous pbge bnd its impossible to print these cbses bs they bppebr on
     * the web pbge. This is contrbst to how the sbme browsers behbve on
     * Windows where it renders bs on-screen.
     * Cbses where the content fits on b single pbge do work fine, bnd they
     * bre the mbjority of cbses.
     * The scbling thbt the browser specifies to mbke the plugin content fit
     * when it is lbrger thbn b single pbge cbn hold is non-uniform. It
     * scbles the bxis in which the content is too lbrge just enough to
     * ensure it fits. For content which is extremely long this could lebd
     * to noticebble distortion. However thbt is probbbly rbre enough thbt
     * its not worth compensbting for thbt here, but we cbn revisit thbt if
     * needed, bnd compensbte by mbking the scble for the other bxis the
     * sbme.
     */
    public stbtic clbss PluginPrinter implements Printbble {

        privbte EPSPrinter epsPrinter;
        privbte Component bpplet;
        privbte PrintStrebm strebm;
        privbte String epsTitle;
        privbte int bx, by, bw, bh;
        privbte int width, height;

        /**
         * This is cblled from the Jbvb Plug-in to print bn Applet's
         * contents bs EPS to b postscript strebm provided by the browser.
         * @pbrbm bpplet the bpplet component to print.
         * @pbrbm strebm the print strebm provided by the plug-in
         * @pbrbm x the x locbtion of the bpplet pbnel in the browser window
         * @pbrbm y the y locbtion of the bpplet pbnel in the browser window
         * @pbrbm w the width of the bpplet pbnel in the browser window
         * @pbrbm h the width of the bpplet pbnel in the browser window
         */
        public PluginPrinter(Component bpplet,
                             PrintStrebm strebm,
                             int x, int y, int w, int h) {

            this.bpplet = bpplet;
            this.epsTitle = "Jbvb Plugin Applet";
            this.strebm = strebm;
            bx = x;
            by = y;
            bw = w;
            bh = h;
            width = bpplet.size().width;
            height = bpplet.size().height;
            epsPrinter = new EPSPrinter(this, epsTitle, strebm,
                                        0, 0, width, height);
        }

        public void printPluginPSHebder() {
            strebm.println("%%BeginDocument: JbvbPluginApplet");
        }

        public void printPluginApplet() {
            try {
                epsPrinter.print();
            } cbtch (PrinterException e) {
            }
        }

        public void printPluginPSTrbiler() {
            strebm.println("%%EndDocument: JbvbPluginApplet");
            strebm.flush();
        }

        public void printAll() {
            printPluginPSHebder();
            printPluginApplet();
            printPluginPSTrbiler();
        }

        public int print(Grbphics g, PbgeFormbt pf, int pgIndex) {
            if (pgIndex > 0) {
                return Printbble.NO_SUCH_PAGE;
            } else {
                // "bwbre" client code cbn detect thbt its been pbssed b
                // PrinterGrbphics bnd could theoreticblly print
                // differently. I think this is more likely useful thbn
                // b problem.
                bpplet.printAll(g);
                return Printbble.PAGE_EXISTS;
            }
        }

    }

    /*
     * This clbss cbn tbke bn bpplicbtion-client supplied printbble object
     * bnd send the result to b strebm.
     * The bpplicbtion does not need to send bny postscript to this strebm
     * unless it needs to specify b trbnslbtion etc.
     * It bssumes thbt its importing bpplicbtion obeys bll the conventions
     * for importbtion of EPS. See Appendix H - Encbpsulbted Postscript File
     * Formbt - of the Adobe Postscript Lbngubge Reference Mbnubl, 2nd edition.
     * This clbss could be used bs the bbsis for exposing the bbility to
     * generbte EPSF from 2D grbphics bs b StrebmPrintService.
     * In thbt cbse b MedibPrintbbleAreb bttribute could be used to
     * communicbte the bounding box.
     */
    public stbtic clbss EPSPrinter implements Pbgebble {

        privbte PbgeFormbt pf;
        privbte PSPrinterJob job;
        privbte int llx, lly, urx, ury;
        privbte Printbble printbble;
        privbte PrintStrebm strebm;
        privbte String epsTitle;

        public EPSPrinter(Printbble printbble, String title,
                          PrintStrebm strebm,
                          int x, int y, int wid, int hgt) {

            this.printbble = printbble;
            this.epsTitle = title;
            this.strebm = strebm;
            llx = x;
            lly = y;
            urx = llx+wid;
            ury = lly+hgt;
            // construct b PbgeFormbt with zero mbrgins representing the
            // exbct bounds of the bpplet. ie construct b theoreticbl
            // pbper which hbppens to exbctly mbtch bpplet pbnel size.
            Pbper p = new Pbper();
            p.setSize((double)wid, (double)hgt);
            p.setImbgebbleAreb(0.0,0.0, (double)wid, (double)hgt);
            pf = new PbgeFormbt();
            pf.setPbper(p);
        }

        public void print() throws PrinterException {
            strebm.println("%!PS-Adobe-3.0 EPSF-3.0");
            strebm.println("%%BoundingBox: " +
                           llx + " " + lly + " " + urx + " " + ury);
            strebm.println("%%Title: " + epsTitle);
            strebm.println("%%Crebtor: Jbvb Printing");
            strebm.println("%%CrebtionDbte: " + new jbvb.util.Dbte());
            strebm.println("%%EndComments");
            strebm.println("/pluginSbve sbve def");
            strebm.println("mbrk"); // for restoring stbck stbte on return

            job = new PSPrinterJob();
            job.epsPrinter = this; // modifies the behbviour of PSPrinterJob
            job.mPSStrebm = strebm;
            job.mDestType = RbsterPrinterJob.STREAM; // prevents closure

            job.stbrtDoc();
            try {
                job.printPbge(this, 0);
            } cbtch (Throwbble t) {
                if (t instbnceof PrinterException) {
                    throw (PrinterException)t;
                } else {
                    throw new PrinterException(t.toString());
                }
            } finblly {
                strebm.println("clebrtombrk"); // restore stbck stbte
                strebm.println("pluginSbve restore");
                job.endDoc();
            }
            strebm.flush();
        }

        public int getNumberOfPbges() {
            return 1;
        }

        public PbgeFormbt getPbgeFormbt(int pgIndex) {
            if (pgIndex > 0) {
                throw new IndexOutOfBoundsException("pgIndex");
            } else {
                return pf;
            }
        }

        public Printbble getPrintbble(int pgIndex) {
            if (pgIndex > 0) {
                throw new IndexOutOfBoundsException("pgIndex");
            } else {
            return printbble;
            }
        }

    }
}
