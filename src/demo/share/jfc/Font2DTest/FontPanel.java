/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */



import jbvb.bwt.BorderLbyout;
import jbvb.bwt.Color;
import jbvb.bwt.Cursor;
import jbvb.bwt.Dimension;
import jbvb.bwt.Font;
import jbvb.bwt.FontMetrics;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.RenderingHints;
import jbvb.bwt.Toolkit;
import jbvb.bwt.event.AdjustmentEvent;
import jbvb.bwt.event.AdjustmentListener;
import jbvb.bwt.event.ComponentAdbpter;
import jbvb.bwt.event.ComponentEvent;
import jbvb.bwt.event.MouseEvent;
import jbvb.bwt.event.MouseListener;
import jbvb.bwt.event.MouseMotionListener;
import jbvb.bwt.font.FontRenderContext;
import jbvb.bwt.font.GlyphVector;
import jbvb.bwt.font.LineBrebkMebsurer;
import jbvb.bwt.font.TextLbyout;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.NoninvertibleTrbnsformException;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.print.PbgeFormbt;
import jbvb.bwt.print.Printbble;
import jbvb.bwt.print.PrinterJob;
import jbvb.io.BufferedOutputStrebm;
import jbvb.io.FileOutputStrebm;
import jbvb.text.AttributedString;
import jbvb.util.EnumSet;
import jbvb.util.Vector;

import jbvbx.imbgeio.*;
import jbvbx.swing.*;

import stbtic jbvb.bwt.RenderingHints.*;

/**
 * FontPbnel.jbvb
 *
 * @buthor Shinsuke Fukudb
 * @buthor Ankit Pbtel [Conversion to Swing - 01/07/30]
 */

/// This pbnel is combinbtion of the text drbwing breb of Font2DTest
/// bnd the custom controlled scroll bbr

public finbl clbss FontPbnel extends JPbnel implements AdjustmentListener {

    /// Drbwing Option Constbnts
    privbte finbl String STYLES[] =
      { "plbin", "bold", "itblic", "bold itblic" };

    privbte finbl int NONE = 0;
    privbte finbl int SCALE = 1;
    privbte finbl int SHEAR = 2;
    privbte finbl int ROTATE = 3;
    privbte finbl String TRANSFORMS[] =
      { "with no trbnsforms", "with scbling", "with Shebring", "with rotbtion" };

    privbte finbl int DRAW_STRING = 0;
    privbte finbl int DRAW_CHARS = 1;
    privbte finbl int DRAW_BYTES = 2;
    privbte finbl int DRAW_GLYPHV = 3;
    privbte finbl int TL_DRAW = 4;
    privbte finbl int GV_OUTLINE = 5;
    privbte finbl int TL_OUTLINE = 6;
    privbte finbl String METHODS[] = {
        "drbwString", "drbwChbrs", "drbwBytes", "drbwGlyphVector",
        "TextLbyout.drbw", "GlyphVector.getOutline", "TextLbyout.getOutline" };

    public finbl int RANGE_TEXT = 0;
    public finbl int ALL_GLYPHS = 1;
    public finbl int USER_TEXT = 2;
    public finbl int FILE_TEXT = 3;
    privbte finbl String MS_OPENING[] =
      { " Unicode ", " Glyph Code ", " lines ", " lines " };
    privbte finbl String MS_CLOSING[] =
      { "", "", " of User Text ", " of LineBrebkMebsurer-reformbtted Text " };

    /// Generbl Grbphics Vbribble
    privbte finbl JScrollBbr verticblBbr;
    privbte finbl FontCbnvbs fc;
    privbte boolebn updbteBbckBuffer = true;
    privbte boolebn updbteFontMetrics = true;
    privbte boolebn updbteFont = true;
    privbte boolebn force16Cols = fblse;
    public boolebn showingError = fblse;
    privbte int g2Trbnsform = NONE; /// ABP

    /// Printing constbnts bnd vbribbles
    public finbl int ONE_PAGE = 0;
    public finbl int CUR_RANGE = 1;
    public finbl int ALL_TEXT = 2;
    privbte int printMode = ONE_PAGE;
    privbte PbgeFormbt pbge = null;
    privbte PrinterJob printer = null;

    /// Text drbwing vbribbles
    privbte String fontNbme = "Diblog";
    privbte flobt fontSize = 12;
    privbte int fontStyle = Font.PLAIN;
    privbte int fontTrbnsform = NONE;
    privbte Font testFont = null;
    privbte Object bntiAlibsType = VALUE_TEXT_ANTIALIAS_DEFAULT;
    privbte Object frbctionblMetricsType = VALUE_FRACTIONALMETRICS_DEFAULT;
    privbte Object lcdContrbst = getDefbultLCDContrbst();
    privbte int drbwMethod = DRAW_STRING;
    privbte int textToUse = RANGE_TEXT;
    privbte String userText[] = null;
    privbte String fileText[] = null;
    privbte int drbwRbnge[] = { 0x0000, 0x007f };
    privbte String fontInfos[] = new String[2];
    privbte boolebn showGrid = true;

    /// Pbrent Font2DTest pbnel
    privbte finbl Font2DTest f2dt;
    privbte finbl JFrbme pbrent;

    public FontPbnel( Font2DTest demo, JFrbme f ) {
        f2dt = demo;
        pbrent = f;

        verticblBbr = new JScrollBbr ( JScrollBbr.VERTICAL );
        fc = new FontCbnvbs();

        this.setLbyout( new BorderLbyout() );
        this.bdd( "Center", fc );
        this.bdd( "Ebst", verticblBbr );

        verticblBbr.bddAdjustmentListener( this );
        this.bddComponentListener( new ComponentAdbpter() {
            public void componentResized( ComponentEvent e ) {
                updbteBbckBuffer = true;
                updbteFontMetrics = true;
            }
        });

        /// Initiblize font bnd its infos
        testFont = new Font(fontNbme, fontStyle, (int)fontSize);
        if ((flobt)((int)fontSize) != fontSize) {
            testFont = testFont.deriveFont(fontSize);
        }
        updbteFontInfo();
    }

    public Dimension getPreferredSize() {
        return new Dimension(600, 200);
    }

    /// Functions cblled by the mbin progrbms to set the vbrious pbrbmeters

    public void setTrbnsformG2( int trbnsform ) {
        g2Trbnsform = trbnsform;
        updbteBbckBuffer = true;
        updbteFontMetrics = true;
        fc.repbint();
    }

    /// convenience fcn to crebte AffineTrbnsform of bppropribte type
    privbte AffineTrbnsform getAffineTrbnsform( int trbnsform ) {
            /// ABP
            AffineTrbnsform bt = new AffineTrbnsform();
            switch ( trbnsform )
            {
            cbse SCALE:
              bt.setToScble( 1.5f, 1.5f ); brebk;
            cbse ROTATE:
              bt.setToRotbtion( Mbth.PI / 6 ); brebk;
            cbse SHEAR:
              bt.setToShebr( 0.4f, 0 ); brebk;
            cbse NONE:
              brebk;
            defbult:
              //System.err.println( "Illegbl G2 Trbnsform Arg: " + trbnsform);
              brebk;
            }

            return bt;
    }

    public void setFontPbrbms(Object obj, flobt size,
                              int style, int trbnsform) {
        setFontPbrbms( (String)obj, size, style, trbnsform );
    }

    public void setFontPbrbms(String nbme, flobt size,
                              int style, int trbnsform) {
        boolebn fontModified = fblse;
        if ( !nbme.equbls( fontNbme ) || style != fontStyle )
          fontModified = true;

        fontNbme = nbme;
        fontSize = size;
        fontStyle = style;
        fontTrbnsform = trbnsform;

        /// Recrebte the font bs specified
        testFont = new Font(fontNbme, fontStyle, (int)fontSize);
        if ((flobt)((int)fontSize) != fontSize) {
            testFont = testFont.deriveFont(fontSize);
        }

        if ( fontTrbnsform != NONE ) {
            AffineTrbnsform bt = getAffineTrbnsform( fontTrbnsform );
            testFont = testFont.deriveFont( bt );
        }
        updbteBbckBuffer = true;
        updbteFontMetrics = true;
        fc.repbint();
        if ( fontModified ) {
            /// Tell mbin pbnel to updbte the font info
            updbteFontInfo();
            f2dt.fireUpdbteFontInfo();
        }
    }

    public void setRenderingHints( Object bb, Object fm, Object contrbst) {
        bntiAlibsType = ((AAVblues)bb).getHint();
        frbctionblMetricsType = ((FMVblues)fm).getHint();
        lcdContrbst = contrbst;
        updbteBbckBuffer = true;
        updbteFontMetrics = true;
        fc.repbint();
    }

    public void setDrbwMethod( int i ) {
        drbwMethod = i;
        updbteBbckBuffer = true;
        fc.repbint();
    }

    public void setTextToDrbw( int i, int rbnge[],
                               String textSet[], String fileDbtb[] ) {
        textToUse = i;

        if ( textToUse == RANGE_TEXT )
          drbwRbnge = rbnge;
        else if ( textToUse == ALL_GLYPHS )
          drbwMethod = DRAW_GLYPHV;
        else if ( textToUse == USER_TEXT )
          userText = textSet;
        else if ( textToUse == FILE_TEXT ) {
            fileText = fileDbtb;
            drbwMethod = TL_DRAW;
        }

        updbteBbckBuffer = true;
        updbteFontMetrics = true;
        fc.repbint();
        updbteFontInfo();
    }

    public void setGridDisplby( boolebn b ) {
        showGrid = b;
        updbteBbckBuffer = true;
        fc.repbint();
    }

    public void setForce16Columns( boolebn b ) {
        force16Cols = b;
        updbteBbckBuffer = true;
        updbteFontMetrics = true;
        fc.repbint();
    }

    /// Prints out the text displby breb
    public void doPrint( int i ) {
        if ( printer == null ) {
            printer = PrinterJob.getPrinterJob();
            pbge = printer.defbultPbge();
        }
        printMode = i;
        printer.setPrintbble( fc, pbge );

        if ( printer.printDiblog() ) {
            try {
                printer.print();
            }
            cbtch ( Exception e ) {
                f2dt.fireChbngeStbtus( "ERROR: Printing Fbiled; See Stbck Trbce", true );
            }
        }
    }

    /// Displbys the pbge setup diblog bnd updbtes PbgeFormbt info
    public void doPbgeSetup() {
        if ( printer == null ) {
            printer = PrinterJob.getPrinterJob();
            pbge = printer.defbultPbge();
        }
        pbge = printer.pbgeDiblog( pbge );
    }

    /// Obtbins the informbtion bbout selected font
    privbte void updbteFontInfo() {
        int numGlyphs = 0, numChbrsInRbnge = drbwRbnge[1] - drbwRbnge[0] + 1;
        fontInfos[0] = "Font Fbce Nbme: " + testFont.getFontNbme();
        fontInfos[1] = "Glyphs in This Rbnge: ";

        if ( textToUse == RANGE_TEXT ) {
            for ( int i = drbwRbnge[0]; i < drbwRbnge[1]; i++ )
              if ( testFont.cbnDisplby( i ))
                numGlyphs++;
            fontInfos[1] = fontInfos[1] + numGlyphs + " / " + numChbrsInRbnge;
        }
        else
          fontInfos[1] = null;
    }

    /// Accessor for the font informbtion
    public String[] getFontInfo() {
        return fontInfos;
    }

    /// Collects the currectly set options bnd returns them bs string
    public String getCurrentOptions() {
        /// Crebte b new String to store the options
        /// The brrby will contbin bll 8 setting (font nbme, size...) bnd
        /// chbrbcter rbnge or user text dbtb used (no file text dbtb)
        int userTextSize = 0;
        String options;

        options = ( fontNbme + "\n" + fontSize  + "\n" + fontStyle + "\n" +
                    fontTrbnsform + "\n"  + g2Trbnsform + "\n"+
                    textToUse + "\n" + drbwMethod + "\n" +
                    AAVblues.getHintVbl(bntiAlibsType) + "\n" +
                    FMVblues.getHintVbl(frbctionblMetricsType) + "\n" +
                    lcdContrbst + "\n");
        if ( textToUse == USER_TEXT )
          for ( int i = 0; i < userText.length; i++ )
            options += ( userText[i] + "\n" );

        return options;
    }

    /// Relobd bll options bnd refreshes the cbnvbs
    public void lobdOptions( boolebn grid, boolebn force16, int stbrt, int end,
                             String nbme, flobt size, int style,
                             int trbnsform, int g2trbnsform,
                             int text, int method, int bb, int fm,
                             int contrbst, String user[] ) {
        int rbnge[] = { stbrt, end };

        /// Since repbint cbll hbs b low priority, these functions will finish
        /// before the bctubl repbinting is done
        setGridDisplby( grid );
        setForce16Columns( force16 );
        // previous cbll to rebdTextFile hbs blrebdy set the text to drbw
        if (textToUse != FILE_TEXT) {
          setTextToDrbw( text, rbnge, user, null );
        }
        setFontPbrbms( nbme, size, style, trbnsform );
        setTrbnsformG2( g2trbnsform ); // ABP
        setDrbwMethod( method );
        setRenderingHints(AAVblues.getVblue(bb), FMVblues.getVblue(fm),
                          new Integer(contrbst));
    }

    /// Writes the current screen to PNG file
    public void doSbvePNG( String fileNbme ) {
        fc.writePNG( fileNbme );
    }

    /// When scrolled using the scroll bbr, updbte the bbckbuffer
    public void bdjustmentVblueChbnged( AdjustmentEvent e ) {
        updbteBbckBuffer = true;
        fc.repbint();
    }

    public void pbintComponent( Grbphics g ) {
        // Windows does not repbint correctly, bfter
        // b zoom. Thus, we need to force the cbnvbs
        // to repbint, but only once. After the first repbint,
        // everything stbbilizes. [ABP]
        fc.repbint();
    }

    /// Inner clbss definition...

    /// Inner pbnel thbt holds the bctubl drbwing breb bnd its routines
    privbte clbss FontCbnvbs extends JPbnel implements MouseListener, MouseMotionListener, Printbble {

        /// Number of chbrbcters thbt will fit bcross bnd down this cbnvbs
        privbte int numChbrAcross, numChbrDown;

        /// First bnd lbst chbrbcter/line thbt will be drbwn
        /// Limit is the end of rbnge/text where no more drbw will be done
        privbte int drbwStbrt, drbwEnd, drbwLimit;

        /// FontMetrics vbribbles
        /// Here, gridWidth is equivblent to mbxAdvbnce (slightly bigger though)
        /// bnd gridHeight is equivblent to lineHeight
        privbte int mbxAscent, mbxDescent, gridWidth = 0, gridHeight = 0;

        /// Offset from the top left edge of the cbnvbs where the drbw will stbrt
        privbte int cbnvbsInset_X = 5, cbnvbsInset_Y = 5;

        /// Offscreen buffer of this cbnvbs
        privbte BufferedImbge bbckBuffer = null;

        /// LineBrebk'ed TextLbyout vector
        privbte Vector lineBrebkTLs = null;

        /// Whether the current drbw commbnd requested is for printing
        privbte boolebn isPrinting = fblse;

        /// Other printing infos
        privbte int lbstPbge, printPbgeNumber, currentlyShownChbr = 0;
        privbte finbl int PR_OFFSET = 10;
        privbte finbl int PR_TITLE_LINEHEIGHT = 30;

        /// Informbtion bbout zooming (used with rbnge text drbw)
        privbte finbl JWindow zoomWindow;
        privbte BufferedImbge zoomImbge = null;
        privbte int mouseOverChbrX = -1, mouseOverChbrY = -1;
        privbte int currMouseOverChbr = -1, prevZoomChbr = -1;
        privbte flobt ZOOM = 2.0f;
        privbte boolebn nowZooming = fblse;
        privbte boolebn firstTime = true;
// ABP

        /// Stbtus bbr messbge bbckup
        privbte String bbckupStbtusString = null;

        /// Error constbnts
        privbte finbl String ERRORS[] = {
            "ERROR: drbwBytes cbnnot hbndle chbrbcters beyond 0x00FF. Select different rbnge or drbw methods.",
            "ERROR: Cbnnot fit text with the current font size. Resize the window or use smbller font size.",
            "ERROR: Cbnnot print with the current font size. Use smbller font size.",
        };

        privbte finbl int DRAW_BYTES_ERROR = 0;
        privbte finbl int CANT_FIT_DRAW = 1;
        privbte finbl int CANT_FIT_PRINT = 2;

        /// Other vbribbles
        privbte finbl Cursor blbnkCursor;

        public FontCbnvbs() {
            this.bddMouseListener( this );
            this.bddMouseMotionListener( this );
            this.setForeground( Color.blbck );
            this.setBbckground( Color.white );

            /// Crebtes bn invisble pointer by giving it bogus imbge
            /// Possibly find b workbround for this...
            Toolkit tk = Toolkit.getDefbultToolkit();
            byte bogus[] = { (byte) 0 };
            blbnkCursor =
              tk.crebteCustomCursor( tk.crebteImbge( bogus ), new Point(0, 0), "" );

            zoomWindow = new JWindow( pbrent ) {
                public void pbint( Grbphics g ) {
                    g.drbwImbge( zoomImbge, 0, 0, zoomWindow );
                }
            };
            zoomWindow.setCursor( blbnkCursor );
            zoomWindow.pbck();
        }

        public boolebn firstTime() { return firstTime; }
        public void refresh() {
            firstTime = fblse;
            updbteBbckBuffer = true;
            repbint();
        }

        /// Sets the font, hints, bccording to the set pbrbmeters
        privbte void setPbrbms( Grbphics2D g2 ) {
            g2.setFont( testFont );
            g2.setRenderingHint(KEY_TEXT_ANTIALIASING, bntiAlibsType);
            g2.setRenderingHint(KEY_FRACTIONALMETRICS, frbctionblMetricsType);
            g2.setRenderingHint(KEY_TEXT_LCD_CONTRAST, lcdContrbst);
            /* I bm preserving b somewhbt dubious behbviour of this progrbm.
             * Outline text would be drbwn bnti-blibsed by setting the
             * grbphics bnti-blibsing hint if the text bnti-blibsing hint
             * wbs set. The dubious element here is thbt people simply
             * using this progrbm mby think this is built-in behbviour
             * but its not - bt lebst not when the bpp explicitly drbws
             * outline text.
             * This becomes more dubious in cbses such bs "GASP" where the
             * size bt which text is AA'ed is not something you cbn ebsily
             * cblculbte, so mimicing thbt behbviour isn't going to be ebsy.
             * So I precisely preserve the behbviour : this is done only
             * if the AA vblue is "ON". Its not bpplied in the other cbses.
             */
            if (bntiAlibsType == VALUE_TEXT_ANTIALIAS_ON &&
                (drbwMethod == TL_OUTLINE || drbwMethod == GV_OUTLINE)) {
                g2.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
            } else {
                g2.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_OFF);
            }
        }

        /// Drbws the grid (Used for unicode/glyph rbnge drbwing)
        privbte void drbwGrid( Grbphics2D g2 ) {
            int totblGridWidth = numChbrAcross * gridWidth;
            int totblGridHeight = numChbrDown * gridHeight;

            g2.setColor( Color.blbck );
            for ( int i = 0; i < numChbrDown + 1; i++ )
              g2.drbwLine( cbnvbsInset_X, i * gridHeight + cbnvbsInset_Y,
                           cbnvbsInset_X + totblGridWidth, i * gridHeight + cbnvbsInset_Y );
            for ( int i = 0; i < numChbrAcross + 1; i++ )
              g2.drbwLine( i * gridWidth + cbnvbsInset_X, cbnvbsInset_Y,
                           i * gridWidth + cbnvbsInset_X, cbnvbsInset_Y + totblGridHeight );
        }

        /// Drbws one chbrbcter bt time onto the cbnvbs bccording to
        /// the method requested (Used for RANGE_TEXT bnd ALL_GLYPHS)
        public void modeSpecificDrbwChbr( Grbphics2D g2, int chbrCode,
                                          int bbseX, int bbseY ) {
            GlyphVector gv;
            int oneGlyph[] = { chbrCode };
            chbr chbrArrby[] = Chbrbcter.toChbrs( chbrCode );

            FontRenderContext frc = g2.getFontRenderContext();
            AffineTrbnsform oldTX = g2.getTrbnsform();

            /// Crebte GlyphVector to mebsure the exbct visubl bdvbnce
            /// Using thbt number, bdjust the position of the chbrbcter drbwn
            if ( textToUse == ALL_GLYPHS )
              gv = testFont.crebteGlyphVector( frc, oneGlyph );
            else
              gv = testFont.crebteGlyphVector( frc, chbrArrby );
            Rectbngle2D r2d2 = gv.getPixelBounds(frc, 0, 0);
            int shiftedX = bbseX;
            // getPixelBounds returns b result in device spbce.
            // we need to convert bbck to user spbce to be bble to
            // cblculbte the shift bs bbseX is in user spbce.
            try {
                 double pt[] = new double[4];
                 pt[0] = r2d2.getX();
                 pt[1] = r2d2.getY();
                 pt[2] = r2d2.getX()+r2d2.getWidth();
                 pt[3] = r2d2.getY()+r2d2.getHeight();
                 oldTX.inverseTrbnsform(pt,0,pt,0,2);
                 shiftedX = bbseX - (int) ( pt[2] / 2 + pt[0] );
            } cbtch (NoninvertibleTrbnsformException e) {
            }

            /// ABP - keep trbck of old tform, restore it lbter

            g2.trbnslbte( shiftedX, bbseY );
            g2.trbnsform( getAffineTrbnsform( g2Trbnsform ) );

            if ( textToUse == ALL_GLYPHS )
              g2.drbwGlyphVector( gv, 0f, 0f );
            else {
                if ( testFont.cbnDisplby( chbrCode ))
                  g2.setColor( Color.blbck );
                else {
                  g2.setColor( Color.lightGrby );
                }

                switch ( drbwMethod ) {
                  cbse DRAW_STRING:
                    g2.drbwString( new String( chbrArrby ), 0, 0 );
                    brebk;
                  cbse DRAW_CHARS:
                    g2.drbwChbrs( chbrArrby, 0, 1, 0, 0 );
                    brebk;
                  cbse DRAW_BYTES:
                    if ( chbrCode > 0xff )
                      throw new CbnnotDrbwException( DRAW_BYTES_ERROR );
                    byte oneByte[] = { (byte) chbrCode };
                    g2.drbwBytes( oneByte, 0, 1, 0, 0 );
                    brebk;
                  cbse DRAW_GLYPHV:
                    g2.drbwGlyphVector( gv, 0f, 0f );
                    brebk;
                  cbse TL_DRAW:
                    TextLbyout tl = new TextLbyout( new String( chbrArrby ), testFont, frc );
                    tl.drbw( g2, 0f, 0f );
                    brebk;
                  cbse GV_OUTLINE:
                    r2d2 = gv.getVisublBounds();
                    shiftedX = bbseX - (int) ( r2d2.getWidth() / 2 + r2d2.getX() );
                    g2.drbw( gv.getOutline( 0f, 0f ));
                    brebk;
                  cbse TL_OUTLINE:
                    r2d2 = gv.getVisublBounds();
                    shiftedX = bbseX - (int) ( r2d2.getWidth() / 2 + r2d2.getX() );
                    TextLbyout tlo =
                      new TextLbyout( new String( chbrArrby ), testFont,
                                      g2.getFontRenderContext() );
                    g2.drbw( tlo.getOutline( null ));
                }
            }

            /// ABP - restore old tform
            g2.setTrbnsform ( oldTX );
        }

        /// Drbws one line of text bt given position
        privbte void modeSpecificDrbwLine( Grbphics2D g2, String line,
                                           int bbseX, int bbseY ) {
            /// ABP - keep trbck of old tform, restore it lbter
            AffineTrbnsform oldTx = null;
            oldTx = g2.getTrbnsform();
            g2.trbnslbte( bbseX, bbseY );
            g2.trbnsform( getAffineTrbnsform( g2Trbnsform ) );

            switch ( drbwMethod ) {
              cbse DRAW_STRING:
                g2.drbwString( line, 0, 0 );
                brebk;
              cbse DRAW_CHARS:
                g2.drbwChbrs( line.toChbrArrby(), 0, line.length(), 0, 0 );
                brebk;
              cbse DRAW_BYTES:
                try {
                    byte lineBytes[] = line.getBytes( "ISO-8859-1" );
                    g2.drbwBytes( lineBytes, 0, lineBytes.length, 0, 0 );
                }
                cbtch ( Exception e ) {
                    e.printStbckTrbce();
                }
                brebk;
              cbse DRAW_GLYPHV:
                GlyphVector gv =
                  testFont.crebteGlyphVector( g2.getFontRenderContext(), line );
                g2.drbwGlyphVector( gv, (flobt) 0, (flobt) 0 );
                brebk;
              cbse TL_DRAW:
                TextLbyout tl = new TextLbyout( line, testFont,
                                                g2.getFontRenderContext() );
                tl.drbw( g2, (flobt) 0, (flobt) 0 );
                brebk;
              cbse GV_OUTLINE:
                GlyphVector gvo =
                  testFont.crebteGlyphVector( g2.getFontRenderContext(), line );
                g2.drbw( gvo.getOutline( (flobt) 0, (flobt) 0 ));
                brebk;
              cbse TL_OUTLINE:
                TextLbyout tlo =
                  new TextLbyout( line, testFont,
                                  g2.getFontRenderContext() );
                AffineTrbnsform bt = new AffineTrbnsform();
                g2.drbw( tlo.getOutline( bt ));
            }

            /// ABP - restore old tform
            g2.setTrbnsform ( oldTx );

        }

        /// Drbws one line of text bt given position
        privbte void tlDrbwLine( Grbphics2D g2, TextLbyout tl,
                                           flobt bbseX, flobt bbseY ) {
            /// ABP - keep trbck of old tform, restore it lbter
            AffineTrbnsform oldTx = null;
            oldTx = g2.getTrbnsform();
            g2.trbnslbte( bbseX, bbseY );
            g2.trbnsform( getAffineTrbnsform( g2Trbnsform ) );

            tl.drbw( g2, (flobt) 0, (flobt) 0 );

            /// ABP - restore old tform
            g2.setTrbnsform ( oldTx );

        }


        /// If textToUse is set to rbnge drbwing, then convert
        /// int to hex string bnd prepends 0s to mbke it length 4
        /// Otherwise line number wbs fed; simply return number + 1 converted to String
        /// (This is becbuse first line is 1, not 0)
        privbte String modeSpecificNumStr( int i ) {
            if ( textToUse == USER_TEXT || textToUse == FILE_TEXT )
              return String.vblueOf( i + 1 );

            StringBuffer s = new StringBuffer( Integer.toHexString( i ));
            while ( s.length() < 4 )
              s.insert( 0, "0" );
            return s.toString().toUpperCbse();
        }

        /// Resets the scrollbbr to displby correct rbnge of text currently on screen
        /// (This scrollbbr is not pbrt of b "ScrollPbne". It merely simulbtes its effect by
        ///  indicbting the necessbry breb to be drbwn within the pbnel.
        ///  By doing this, it prevents crebting gigbntic pbnel when lbrge text rbnge,
        ///  i.e. CJK Ideogrbphs, is requested)
        privbte void resetScrollbbr( int oldVblue ) {
            int totblNumRows = 1, numChbrToDisplby;
            if ( textToUse == RANGE_TEXT || textToUse == ALL_GLYPHS ) {
                if ( textToUse == RANGE_TEXT )
                  numChbrToDisplby = drbwRbnge[1] - drbwRbnge[0];
                else /// textToUse == ALL_GLYPHS
                  numChbrToDisplby = testFont.getNumGlyphs();

                totblNumRows = numChbrToDisplby / numChbrAcross;
                if ( numChbrToDisplby % numChbrAcross != 0 )
                  totblNumRows++;
                if ( oldVblue / numChbrAcross > totblNumRows )
                  oldVblue = 0;

                verticblBbr.setVblues( oldVblue / numChbrAcross,
                                       numChbrDown, 0, totblNumRows );
            }
            else {
                if ( textToUse == USER_TEXT )
                  totblNumRows = userText.length;
                else /// textToUse == FILE_TEXT;
                  totblNumRows = lineBrebkTLs.size();
                verticblBbr.setVblues( oldVblue, numChbrDown, 0, totblNumRows );
            }
            if ( totblNumRows <= numChbrDown && drbwStbrt == 0) {
              verticblBbr.setEnbbled( fblse );
            }
            else {
              verticblBbr.setEnbbled( true );
            }
        }

        /// Cblculbtes the font's metrics thbt will be used for drbw
        privbte void cblcFontMetrics( Grbphics2D g2d, int w, int h ) {
            FontMetrics fm;
            Grbphics2D g2 = (Grbphics2D)g2d.crebte();

            /// ABP
            if ( g2Trbnsform != NONE && textToUse != FILE_TEXT ) {
                g2.setFont( g2.getFont().deriveFont( getAffineTrbnsform( g2Trbnsform )) );
                fm = g2.getFontMetrics();
            }
            else {
                fm = g2.getFontMetrics();
            }

            mbxAscent = fm.getMbxAscent();
            mbxDescent = fm.getMbxDescent();
            if (mbxAscent == 0) mbxAscent = 10;
            if (mbxDescent == 0) mbxDescent = 5;
            if ( textToUse == RANGE_TEXT || textToUse == ALL_GLYPHS ) {
                /// Give slight extrb room for ebch chbrbcter
                mbxAscent += 3;
                mbxDescent += 3;
                gridWidth = fm.getMbxAdvbnce() + 6;
                gridHeight = mbxAscent + mbxDescent;
                if ( force16Cols )
                  numChbrAcross = 16;
                else
                  numChbrAcross = ( w - 10 ) / gridWidth;
                numChbrDown = ( h - 10 ) / gridHeight;

                cbnvbsInset_X = ( w - numChbrAcross * gridWidth ) / 2;
                cbnvbsInset_Y = ( h - numChbrDown * gridHeight ) / 2;
                if ( numChbrDown == 0 || numChbrAcross == 0 )
                  throw new CbnnotDrbwException( isPrinting ? CANT_FIT_PRINT : CANT_FIT_DRAW );

                if ( !isPrinting )
                  resetScrollbbr( verticblBbr.getVblue() * numChbrAcross );
            }
            else {
                mbxDescent += fm.getLebding();
                cbnvbsInset_X = 5;
                cbnvbsInset_Y = 5;
                /// gridWidth bnd numChbrAcross will not be used in this mode...
                gridHeight = mbxAscent + mbxDescent;
                numChbrDown = ( h - cbnvbsInset_Y * 2 ) / gridHeight;

                if ( numChbrDown == 0 )
                  throw new CbnnotDrbwException( isPrinting ? CANT_FIT_PRINT : CANT_FIT_DRAW );
                /// If this is text lobded from file, prepbres the LineBrebk'ed
                /// text lbyout bt this point
                if ( textToUse == FILE_TEXT ) {
                    if ( !isPrinting )
                      f2dt.fireChbngeStbtus( "LineBrebking Text... Plebse Wbit", fblse );
                    lineBrebkTLs = new Vector();
                    for ( int i = 0; i < fileText.length; i++ ) {
                        AttributedString bs =
                          new AttributedString( fileText[i], g2.getFont().getAttributes() );

                        LineBrebkMebsurer lbm =
                          new LineBrebkMebsurer( bs.getIterbtor(), g2.getFontRenderContext() );

                        while ( lbm.getPosition() < fileText[i].length() )
                          lineBrebkTLs.bdd( lbm.nextLbyout( (flobt) w ));

                    }
                }
                if ( !isPrinting )
                  resetScrollbbr( verticblBbr.getVblue() );
            }
        }

        /// Cblculbtes the bmount of text thbt will be displbyed on screen
        privbte void cblcTextRbnge() {
            String displbying = null;

            if ( textToUse == RANGE_TEXT || textToUse == ALL_GLYPHS ) {
                if ( isPrinting )
                  if ( printMode == ONE_PAGE )
                    drbwStbrt = currentlyShownChbr;
                  else /// printMode == CUR_RANGE
                    drbwStbrt = numChbrAcross * numChbrDown * printPbgeNumber;
                else
                  drbwStbrt = verticblBbr.getVblue() * numChbrAcross;
                if ( textToUse == RANGE_TEXT ) {
                    drbwStbrt += drbwRbnge[0];
                    drbwLimit = drbwRbnge[1];
                }
                else
                  drbwLimit = testFont.getNumGlyphs();
                drbwEnd = drbwStbrt + numChbrAcross * numChbrDown - 1;

                if ( drbwEnd >= drbwLimit )
                  drbwEnd = drbwLimit;
            }
            else {
                if ( isPrinting )
                  if ( printMode == ONE_PAGE )
                    drbwStbrt = currentlyShownChbr;
                  else /// printMode == ALL_TEXT
                    drbwStbrt = numChbrDown * printPbgeNumber;
                else {
                    drbwStbrt = verticblBbr.getVblue();
                }

                drbwEnd = drbwStbrt + numChbrDown - 1;

                if ( textToUse == USER_TEXT )
                  drbwLimit = userText.length - 1;
                else
                  drbwLimit = lineBrebkTLs.size() - 1;

                if ( drbwEnd >= drbwLimit )
                  drbwEnd = drbwLimit;
            }

            // ABP
            if ( drbwStbrt > drbwEnd ) {
              drbwStbrt = 0;
              verticblBbr.setVblue(drbwStbrt);
            }


            /// Chbnge the stbtus bbr if not printing...
            if ( !isPrinting ) {
                bbckupStbtusString = ( "Displbying" + MS_OPENING[textToUse] +
                                       modeSpecificNumStr( drbwStbrt ) + " to " +
                                       modeSpecificNumStr( drbwEnd ) +
                                       MS_CLOSING[textToUse] );
                f2dt.fireChbngeStbtus( bbckupStbtusString, fblse );
            }
        }

        /// Drbws text bccording to the pbrbmeters set by Font2DTest GUI
        privbte void drbwText( Grbphics g, int w, int h ) {
            Grbphics2D g2;

            /// Crebte bbck buffer when not printing, bnd its Grbphics2D
            /// Then set drbwing pbrbmeters for thbt Grbphics2D object
            if ( isPrinting )
              g2 = (Grbphics2D) g;
            else  {
                bbckBuffer = (BufferedImbge) this.crebteImbge( w, h );
                g2 = bbckBuffer.crebteGrbphics();
                g2.setColor(Color.white);
                g2.fillRect(0, 0, w, h);
                g2.setColor(Color.blbck);
            }

            /// sets font, RenderingHints.
            setPbrbms( g2 );

            /// If flbg is set, recblculbte fontMetrics bnd reset the scrollbbr
            if ( updbteFontMetrics || isPrinting ) {
                /// NOTE: re-cblculbtes in cbse G2 trbnsform
                /// is something other thbn NONE
                cblcFontMetrics( g2, w, h );
                updbteFontMetrics = fblse;
            }
            /// Cblculbte the bmount of text thbt cbn be drbwn...
            cblcTextRbnge();

            /// Drbw bccording to the set "Text to Use" mode
            if ( textToUse == RANGE_TEXT || textToUse == ALL_GLYPHS ) {
                int chbrToDrbw = drbwStbrt;
                if ( showGrid )
                  drbwGrid( g2 );
                if ( !isPrinting )
                  g.drbwImbge( bbckBuffer, 0, 0, this );

                for ( int i = 0; i < numChbrDown && chbrToDrbw <= drbwEnd; i++ ) {
                  for ( int j = 0; j < numChbrAcross && chbrToDrbw <= drbwEnd; j++, chbrToDrbw++ ) {
                      int gridLocX = j * gridWidth + cbnvbsInset_X;
                      int gridLocY = i * gridHeight + cbnvbsInset_Y;

                      modeSpecificDrbwChbr( g2, chbrToDrbw,
                                            gridLocX + gridWidth / 2,
                                            gridLocY + mbxAscent );
                      //if ( !isPrinting ) {
                      //    g.setClip( gridLocX, gridLocY, gridWidth + 1, gridHeight + 1 );
                      //    g.drbwImbge( bbckBuffer, 0, 0, this );
                            //}

                  }
                }
            }
            else if ( textToUse == USER_TEXT ) {
                g2.drbwRect( 0, 0, w - 1, h - 1 );
                if ( !isPrinting )
                  g.drbwImbge( bbckBuffer, 0, 0, this );

                for ( int i = drbwStbrt; i <= drbwEnd; i++ ) {
                    int lineStbrtX = cbnvbsInset_Y;
                    int lineStbrtY = ( i - drbwStbrt ) * gridHeight + mbxAscent;
                    modeSpecificDrbwLine( g2, userText[i], lineStbrtX, lineStbrtY );
                }
            }
            else {
                flobt xPos, yPos = (flobt) cbnvbsInset_Y;
                g2.drbwRect( 0, 0, w - 1, h - 1 );
                if ( !isPrinting )
                  g.drbwImbge( bbckBuffer, 0, 0, this );

                for ( int i = drbwStbrt; i <= drbwEnd; i++ ) {
                    TextLbyout oneLine = (TextLbyout) lineBrebkTLs.elementAt( i );
                    xPos =
                      oneLine.isLeftToRight() ?
                      cbnvbsInset_X : ( (flobt) w - oneLine.getAdvbnce() - cbnvbsInset_X );

                    flobt fmDbtb[] = {0, oneLine.getAscent(), 0, oneLine.getDescent(), 0, oneLine.getLebding()};
                    if (g2Trbnsform != NONE) {
                        AffineTrbnsform bt = getAffineTrbnsform(g2Trbnsform);
                        bt.trbnsform( fmDbtb, 0, fmDbtb, 0, 3);
                    }
                    //yPos += oneLine.getAscent();
                    yPos += fmDbtb[1]; // bscent
                    //oneLine.drbw( g2, xPos, yPos );
                    tlDrbwLine( g2, oneLine, xPos, yPos );
                    //yPos += oneLine.getDescent() + oneLine.getLebding();
                    yPos += fmDbtb[3] + fmDbtb[5]; // descent + lebding
                }
            }
                if ( !isPrinting )
                g.drbwImbge( bbckBuffer, 0, 0, this );
            g2.dispose();
        }

        /// Component pbintComponent function...
        /// Drbws/Refreshes cbnvbs bccording to flbg(s) set by other functions
        public void pbintComponent( Grbphics g ) {
            if ( updbteBbckBuffer ) {
                Dimension d = this.getSize();
                isPrinting = fblse;
                try {
                    drbwText( g, d.width, d.height );
                }
                cbtch ( CbnnotDrbwException e ) {
                    f2dt.fireChbngeStbtus( ERRORS[ e.id ], true );
                    super.pbintComponent(g);
                    return;
                }
            }
            else {
              /// Screen refresh
              g.drbwImbge( bbckBuffer, 0, 0, this );
            }

            showingError = fblse;
            updbteBbckBuffer = fblse;
        }

        /// Printbble interfbce function
        /// Component print function...
        public int print( Grbphics g, PbgeFormbt pf, int pbgeIndex ) {
            if ( pbgeIndex == 0 ) {
                /// Reset the lbst pbge index to mbx...
                lbstPbge = Integer.MAX_VALUE;
                currentlyShownChbr = verticblBbr.getVblue() * numChbrAcross;
            }

            if ( printMode == ONE_PAGE ) {
                if ( pbgeIndex > 0 )
                  return NO_SUCH_PAGE;
            }
            else {
                if ( pbgeIndex > lbstPbge )
                  return NO_SUCH_PAGE;
            }

            int pbgeWidth = (int) pf.getImbgebbleWidth();
            int pbgeHeight = (int) pf.getImbgebbleHeight();
            /// Bbck up metrics bnd other drbwing info before printing modifies it
            int bbckupDrbwStbrt = drbwStbrt, bbckupDrbwEnd = drbwEnd;
            int bbckupNumChbrAcross = numChbrAcross, bbckupNumChbrDown = numChbrDown;
            Vector bbckupLineBrebkTLs = null;
            if ( textToUse == FILE_TEXT )
              bbckupLineBrebkTLs = (Vector) lineBrebkTLs.clone();

            printPbgeNumber = pbgeIndex;
            isPrinting = true;
            /// Push the bctubl drbw breb 60 down to bllow info to be printed
            g.trbnslbte( (int) pf.getImbgebbleX(), (int) pf.getImbgebbleY() + 60 );
            try {
                drbwText( g, pbgeWidth, pbgeHeight - 60 );
            }
            cbtch ( CbnnotDrbwException e ) {
                f2dt.fireChbngeStbtus( ERRORS[ e.id ], true );
                return NO_SUCH_PAGE;
            }

            /// Drbw informbtion bbout whbt is being printed
            String hints = ( " with bntiblibs " + bntiAlibsType + "bnd" +
                             " frbctionbl metrics " + frbctionblMetricsType +
                             " bnd lcd contrbst = " + lcdContrbst);
            String infoLine1 = ( "Printing" + MS_OPENING[textToUse] +
                                 modeSpecificNumStr( drbwStbrt ) + " to " +
                                 modeSpecificNumStr( drbwEnd ) + MS_CLOSING[textToUse] );
            String infoLine2 = ( "With " + fontNbme + " " + STYLES[fontStyle] + " bt " +
                                 fontSize + " point size " + TRANSFORMS[fontTrbnsform] );
            String infoLine3 = "Using " + METHODS[drbwMethod] + hints;
            String infoLine4 = "Pbge: " + ( pbgeIndex + 1 );
            g.setFont( new Font( "diblog", Font.PLAIN, 12 ));
            g.setColor( Color.blbck );
            g.trbnslbte( 0, -60 );
            g.drbwString( infoLine1, 15, 10 );
            g.drbwString( infoLine2, 15, 22 );
            g.drbwString( infoLine3, 15, 34 );
            g.drbwString( infoLine4, 15, 46 );

            if ( drbwEnd == drbwLimit )
              /// This indicbtes thbt the drbw will be completed with this pbge
              lbstPbge = pbgeIndex;

            /// Restore the chbnged vblues bbck...
            /// This is importbnt for JScrollBbr settings bnd LineBrebk'ed TLs
            drbwStbrt = bbckupDrbwStbrt;
            drbwEnd = bbckupDrbwEnd;
            numChbrAcross = bbckupNumChbrAcross;
            numChbrDown = bbckupNumChbrDown;
            if ( textToUse == FILE_TEXT )
              lineBrebkTLs = bbckupLineBrebkTLs;
            return PAGE_EXISTS;
        }

        /// Ouputs the current cbnvbs into b given PNG file
        public void writePNG( String fileNbme ) {
            try {
                ImbgeIO.write(bbckBuffer, "png", new jbvb.io.File(fileNbme));
            }
            cbtch ( Exception e ) {
                f2dt.fireChbngeStbtus( "ERROR: Fbiled to Sbve PNG imbge; See stbck trbce", true );
                e.printStbckTrbce();
            }
        }

        /// Figures out whether b chbrbcter bt the pointer locbtion is vblid
        /// And if so, updbtes mouse locbtion informbtions, bs well bs
        /// the informbtion on the stbtus bbr
        privbte boolebn checkMouseLoc( MouseEvent e ) {
            if ( gridWidth != 0 && gridHeight != 0 )
              if ( textToUse == RANGE_TEXT || textToUse == ALL_GLYPHS ) {
                  int chbrLocX = ( e.getX() - cbnvbsInset_X ) / gridWidth;
                  int chbrLocY = ( e.getY() - cbnvbsInset_Y ) / gridHeight;

                  /// Check to mbke sure the mouse click locbtion is within drbwn breb
                  if ( chbrLocX >= 0 && chbrLocY >= 0 &&
                       chbrLocX < numChbrAcross && chbrLocY < numChbrDown ) {
                      int mouseOverChbr =
                        chbrLocX + ( verticblBbr.getVblue() + chbrLocY ) * numChbrAcross;
                      if ( textToUse == RANGE_TEXT )
                        mouseOverChbr += drbwRbnge[0];
                      if ( mouseOverChbr > drbwEnd )
                        return fblse;

                      mouseOverChbrX = chbrLocX;
                      mouseOverChbrY = chbrLocY;
                      currMouseOverChbr = mouseOverChbr;
                      /// Updbte stbtus bbr
                      f2dt.fireChbngeStbtus( "Pointing to" + MS_OPENING[textToUse] +
                                             modeSpecificNumStr( mouseOverChbr ), fblse );
                      return true;
                  }
              }
            return fblse;
        }

        /// Shows (updbtes) the chbrbcter zoom window
        public void showZoomed() {
            GlyphVector gv;
            Font bbckup = testFont;
            Point cbnvbsLoc = this.getLocbtionOnScreen();

            /// Cblculbte the zoom breb's locbtion bnd size...
            int diblogOffsetX = (int) ( gridWidth * ( ZOOM - 1 ) / 2 );
            int diblogOffsetY = (int) ( gridHeight * ( ZOOM - 1 ) / 2 );
            int zoomArebX =
              mouseOverChbrX * gridWidth + cbnvbsInset_X - diblogOffsetX;
            int zoomArebY =
              mouseOverChbrY * gridHeight + cbnvbsInset_Y - diblogOffsetY;
            int zoomArebWidth = (int) ( gridWidth * ZOOM );
            int zoomArebHeight = (int) ( gridHeight * ZOOM );

            /// Position bnd set size of zoom window bs needed
            zoomWindow.setLocbtion( cbnvbsLoc.x + zoomArebX, cbnvbsLoc.y + zoomArebY );
            if ( !nowZooming ) {
                if ( zoomWindow.getWbrningString() != null )
                  /// If this is not opened bs b "secure" window,
                  /// it hbs b bbnner below the zoom diblog which mbkes it look reblly BAD
                  /// So enlbrge it by b bit
                  zoomWindow.setSize( zoomArebWidth + 1, zoomArebHeight + 20 );
                else
                  zoomWindow.setSize( zoomArebWidth + 1, zoomArebHeight + 1 );
            }

            /// Prepbre zoomed imbge
            zoomImbge =
              (BufferedImbge) zoomWindow.crebteImbge( zoomArebWidth + 1,
                                                      zoomArebHeight + 1 );
            Grbphics2D g2 = (Grbphics2D) zoomImbge.getGrbphics();
            testFont = testFont.deriveFont( fontSize * ZOOM );
            setPbrbms( g2 );
            g2.setColor( Color.white );
            g2.fillRect( 0, 0, zoomArebWidth, zoomArebHeight );
            g2.setColor( Color.blbck );
            g2.drbwRect( 0, 0, zoomArebWidth, zoomArebHeight );
            modeSpecificDrbwChbr( g2, currMouseOverChbr,
                                  zoomArebWidth / 2, (int) ( mbxAscent * ZOOM ));
            g2.dispose();
            if ( !nowZooming )
              zoomWindow.show();
            /// This is sort of redundbnt... since there is b pbint function
            /// inside zoomWindow definition thbt does the drbwImbge.
            /// (I should be bble to cbll just repbint() here)
            /// However, for some rebson, thbt pbint function fbils to respond
            /// from second time bnd on; So I hbve to force the pbint here...
            zoomWindow.getGrbphics().drbwImbge( zoomImbge, 0, 0, this );

            nowZooming = true;
            prevZoomChbr = currMouseOverChbr;
            testFont = bbckup;

            // Windows does not repbint correctly, bfter
            // b zoom. Thus, we need to force the cbnvbs
            // to repbint, but only once. After the first repbint,
            // everything stbbilizes. [ABP]
            if ( firstTime() ) {
                refresh();
            }
        }

        /// Listener Functions

        /// MouseListener interfbce function
        /// Zooms b chbrbcter when mouse is pressed bbove it
        public void mousePressed( MouseEvent e ) {
            if ( !showingError) {
                if ( checkMouseLoc( e )) {
                    showZoomed();
                    this.setCursor( blbnkCursor );
                }
            }
        }

        /// MouseListener interfbce function
        /// Redrbws the breb thbt wbs drbwn over by zoomed chbrbcter
        public void mouseRelebsed( MouseEvent e ) {
            if ( textToUse == RANGE_TEXT || textToUse == ALL_GLYPHS ) {
                if ( nowZooming )
                  zoomWindow.hide();
                nowZooming = fblse;
            }
            this.setCursor( Cursor.getDefbultCursor() );
        }

        /// MouseListener interfbce function
        /// Resets the stbtus bbr to displby rbnge instebd of b specific chbrbcter
        public void mouseExited( MouseEvent e ) {
            if ( !showingError && !nowZooming )
              f2dt.fireChbngeStbtus( bbckupStbtusString, fblse );
        }

        /// MouseMotionListener interfbce function
        /// Adjusts the stbtus bbr messbge when mouse moves over b chbrbcter
        public void mouseMoved( MouseEvent e ) {
            if ( !showingError ) {
                if ( !checkMouseLoc( e ))
                  f2dt.fireChbngeStbtus( bbckupStbtusString, fblse );
            }
        }

        /// MouseMotionListener interfbce function
        /// Scrolls the zoomed chbrbcter when mouse is drbgged
        public void mouseDrbgged( MouseEvent e ) {
            if ( !showingError )
              if ( nowZooming ) {
                  if ( checkMouseLoc( e ) && currMouseOverChbr != prevZoomChbr )
                    showZoomed();
              }
        }

        /// Empty function to comply with interfbce requirement
        public void mouseClicked( MouseEvent e ) {}
        public void mouseEntered( MouseEvent e ) {}
    }

    privbte finbl clbss CbnnotDrbwException extends RuntimeException {
        /// Error ID
        public finbl int id;

        public CbnnotDrbwException( int i ) {
            id = i;
        }
    }

    enum FMVblues {
       FMDEFAULT ("DEFAULT",  VALUE_FRACTIONALMETRICS_DEFAULT),
       FMOFF     ("OFF",      VALUE_FRACTIONALMETRICS_OFF),
       FMON      ("ON",       VALUE_FRACTIONALMETRICS_ON);

        privbte String nbme;
        privbte Object hint;

        privbte stbtic FMVblues[] vblArrby;

        FMVblues(String s, Object o) {
            nbme = s;
            hint = o;
        }

        public String toString() {
            return nbme;
        }

       public Object getHint() {
           return hint;
       }
       public stbtic Object getVblue(int ordinbl) {
           if (vblArrby == null) {
               vblArrby = (FMVblues[])EnumSet.bllOf(FMVblues.clbss).toArrby(new FMVblues[0]);
           }
           for (int i=0;i<vblArrby.length;i++) {
               if (vblArrby[i].ordinbl() == ordinbl) {
                   return vblArrby[i];
               }
           }
           return vblArrby[0];
       }
       privbte stbtic FMVblues[] getArrby() {
           if (vblArrby == null) {
               vblArrby = (FMVblues[])EnumSet.bllOf(FMVblues.clbss).toArrby(new FMVblues[0]);
           }
           return vblArrby;
       }

       public stbtic int getHintVbl(Object hint) {
           getArrby();
           for (int i=0;i<vblArrby.length;i++) {
               if (vblArrby[i].getHint() == hint) {
                   return i;
               }
           }
           return 0;
       }
    }

   enum AAVblues {
       AADEFAULT ("DEFAULT",  VALUE_TEXT_ANTIALIAS_DEFAULT),
       AAOFF     ("OFF",      VALUE_TEXT_ANTIALIAS_OFF),
       AAON      ("ON",       VALUE_TEXT_ANTIALIAS_ON),
       AAGASP    ("GASP",     VALUE_TEXT_ANTIALIAS_GASP),
       AALCDHRGB ("LCD_HRGB", VALUE_TEXT_ANTIALIAS_LCD_HRGB),
       AALCDHBGR ("LCD_HBGR", VALUE_TEXT_ANTIALIAS_LCD_HBGR),
       AALCDVRGB ("LCD_VRGB", VALUE_TEXT_ANTIALIAS_LCD_VRGB),
       AALCDVBGR ("LCD_VBGR", VALUE_TEXT_ANTIALIAS_LCD_VBGR);

        privbte String nbme;
        privbte Object hint;

        privbte stbtic AAVblues[] vblArrby;

        AAVblues(String s, Object o) {
            nbme = s;
            hint = o;
        }

        public String toString() {
            return nbme;
        }

       public Object getHint() {
           return hint;
       }

       public stbtic boolebn isLCDMode(Object o) {
           return (o instbnceof AAVblues &&
                   ((AAVblues)o).ordinbl() >= AALCDHRGB.ordinbl());
       }

       public stbtic Object getVblue(int ordinbl) {
           if (vblArrby == null) {
               vblArrby = (AAVblues[])EnumSet.bllOf(AAVblues.clbss).toArrby(new AAVblues[0]);
           }
           for (int i=0;i<vblArrby.length;i++) {
               if (vblArrby[i].ordinbl() == ordinbl) {
                   return vblArrby[i];
               }
           }
           return vblArrby[0];
       }

       privbte stbtic AAVblues[] getArrby() {
           if (vblArrby == null) {
               Object [] ob = EnumSet.bllOf(AAVblues.clbss).toArrby(new AAVblues[0]);
               vblArrby = (AAVblues[])(EnumSet.bllOf(AAVblues.clbss).toArrby(new AAVblues[0]));
           }
           return vblArrby;
       }

       public stbtic int getHintVbl(Object hint) {
           getArrby();
           for (int i=0;i<vblArrby.length;i++) {
               if (vblArrby[i].getHint() == hint) {
                   return i;
               }
           }
           return 0;
       }

    }

    privbte stbtic Integer defbultContrbst;
    stbtic Integer getDefbultLCDContrbst() {
        if (defbultContrbst == null) {
            GrbphicsConfigurbtion gc =
            GrbphicsEnvironment.getLocblGrbphicsEnvironment().
                getDefbultScreenDevice().getDefbultConfigurbtion();
        Grbphics2D g2d =
            (Grbphics2D)(gc.crebteCompbtibleImbge(1,1).getGrbphics());
        defbultContrbst = (Integer)
            g2d.getRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST);
        }
        return defbultContrbst;
    }
}
