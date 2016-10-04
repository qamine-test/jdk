/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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



import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.BorderLbyout;
import jbvb.bwt.CheckboxGroup;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Dimension;
import jbvb.bwt.Font;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.GridBbgConstrbints;
import jbvb.bwt.GridBbgLbyout;
import jbvb.bwt.GridLbyout;
import jbvb.bwt.Insets;
import jbvb.bwt.RenderingHints;
import jbvb.bwt.Toolkit;
import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.event.ActionListener;
import jbvb.bwt.event.ItemEvent;
import jbvb.bwt.event.ItemListener;
import jbvb.bwt.event.WindowAdbpter;
import jbvb.bwt.event.WindowEvent;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.io.BufferedInputStrebm;
import jbvb.io.BufferedOutputStrebm;
import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.FileOutputStrebm;
import jbvb.util.EnumSet;
import jbvb.util.StringTokenizer;
import jbvb.util.BitSet;
import jbvbx.swing.*;
import jbvbx.swing.event.*;

/**
 * Font2DTest.jbvb
 *
 * @buthor Shinsuke Fukudb
 * @buthor Ankit Pbtel [Conversion to Swing - 01/07/30]
 */

/// Mbin Font2DTest Clbss

public finbl clbss Font2DTest extends JPbnel
    implements ActionListener, ItemListener, ChbngeListener {

    /// JFrbme thbt will contbin Font2DTest
    privbte finbl JFrbme pbrent;
    /// FontPbnel clbss thbt will contbin bll grbphicbl output
    privbte finbl FontPbnel fp;
    /// RbngeMenu clbss thbt contbins info bbout the unicode rbnges
    privbte finbl RbngeMenu rm;

    /// Other menus to set pbrbmeters for text drbwing
    privbte finbl ChoiceV2 fontMenu;
    privbte finbl JTextField sizeField;
    privbte finbl ChoiceV2 styleMenu;
    privbte finbl ChoiceV2 textMenu;
    privbte int currentTextChoice = 0;
    privbte finbl ChoiceV2 trbnsformMenu;
    privbte finbl ChoiceV2 trbnsformMenuG2;
    privbte finbl ChoiceV2 methodsMenu;
    privbte finbl JComboBox bntiAlibsMenu;
    privbte finbl JComboBox frbcMetricsMenu;

    privbte finbl JSlider contrbstSlider;

    /// CheckboxMenuItems
    privbte CheckboxMenuItemV2 displbyGridCBMI;
    privbte CheckboxMenuItemV2 force16ColsCBMI;
    privbte CheckboxMenuItemV2 showFontInfoCBMI;

    /// JDiblog boxes
    privbte JDiblog userTextDiblog;
    privbte JTextAreb userTextAreb;
    privbte JDiblog printDiblog;
    privbte JDiblog fontInfoDiblog;
    privbte LbbelV2 fontInfos[] = new LbbelV2[2];
    privbte JFileChooser filePromptDiblog = null;

    privbte ButtonGroup printCBGroup;
    privbte JRbdioButton printModeCBs[] = new JRbdioButton[3];

    /// Stbtus bbr
    privbte finbl LbbelV2 stbtusBbr;

    privbte int fontStyles [] = {Font.PLAIN, Font.BOLD, Font.ITALIC, Font.BOLD | Font.ITALIC};

    /// Text filenbme
    privbte String tFileNbme;

    // Enbbled or disbbled stbtus of cbnDisplby check
    privbte stbtic boolebn cbnDisplbyCheck = true;

    /// Initiblize GUI vbribbles bnd its lbyouts
    public Font2DTest( JFrbme f, boolebn isApplet ) {
        pbrent = f;

        rm = new RbngeMenu( this, pbrent );
        fp = new FontPbnel( this, pbrent );
        stbtusBbr = new LbbelV2("");

        fontMenu = new ChoiceV2( this, cbnDisplbyCheck );
        sizeField = new JTextField( "12", 3 );
        sizeField.bddActionListener( this );
        styleMenu = new ChoiceV2( this );
        textMenu = new ChoiceV2( ); // listener bdded lbter
        trbnsformMenu = new ChoiceV2( this );
        trbnsformMenuG2 = new ChoiceV2( this );
        methodsMenu = new ChoiceV2( this );

        bntiAlibsMenu =
            new JComboBox(EnumSet.bllOf(FontPbnel.AAVblues.clbss).toArrby());
        bntiAlibsMenu.bddActionListener(this);
        frbcMetricsMenu =
            new JComboBox(EnumSet.bllOf(FontPbnel.FMVblues.clbss).toArrby());
        frbcMetricsMenu.bddActionListener(this);

        contrbstSlider = new JSlider(JSlider.HORIZONTAL, 100, 250,
                                 FontPbnel.getDefbultLCDContrbst().intVblue());
        contrbstSlider.setEnbbled(fblse);
        contrbstSlider.setMbjorTickSpbcing(20);
        contrbstSlider.setMinorTickSpbcing(10);
        contrbstSlider.setPbintTicks(true);
        contrbstSlider.setPbintLbbels(true);
        contrbstSlider.bddChbngeListener(this);
        setupPbnel();
        setupMenu( isApplet );
        setupDiblog( isApplet );

        if(cbnDisplbyCheck) {
            fireRbngeChbnged();
        }
    }

    /// Set up the mbin interfbce pbnel
    privbte void setupPbnel() {
        GridBbgLbyout gbl = new GridBbgLbyout();
        GridBbgConstrbints gbc = new GridBbgConstrbints();
        gbc.fill = GridBbgConstrbints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.insets = new Insets( 2, 0, 2, 2 );
        this.setLbyout( gbl );

        bddLbbeledComponentToGBL( "Font: ", fontMenu, gbl, gbc, this );
        bddLbbeledComponentToGBL( "Size: ", sizeField, gbl, gbc, this );
        gbc.gridwidth = GridBbgConstrbints.REMAINDER;
        bddLbbeledComponentToGBL( "Font Trbnsform:",
                                  trbnsformMenu, gbl, gbc, this );
        gbc.gridwidth = 1;

        bddLbbeledComponentToGBL( "Rbnge: ", rm, gbl, gbc, this );
        bddLbbeledComponentToGBL( "Style: ", styleMenu, gbl, gbc, this );
        gbc.gridwidth = GridBbgConstrbints.REMAINDER;
        bddLbbeledComponentToGBL( "Grbphics Trbnsform: ",
                                  trbnsformMenuG2, gbl, gbc, this );
        gbc.gridwidth = 1;

        gbc.bnchor = GridBbgConstrbints.WEST;
        bddLbbeledComponentToGBL( "Method: ", methodsMenu, gbl, gbc, this );
        bddLbbeledComponentToGBL("", null, gbl, gbc, this);
        gbc.bnchor = GridBbgConstrbints.EAST;
        gbc.gridwidth = GridBbgConstrbints.REMAINDER;
        bddLbbeledComponentToGBL( "Text to use:", textMenu, gbl, gbc, this );

        gbc.weightx=1;
        gbc.gridwidth = 1;
        gbc.fill = GridBbgConstrbints.HORIZONTAL;
        gbc.bnchor = GridBbgConstrbints.WEST;
        bddLbbeledComponentToGBL("LCD contrbst: ",
                                  contrbstSlider, gbl, gbc, this);

        gbc.gridwidth = 1;
        gbc.fill = GridBbgConstrbints.NONE;
        bddLbbeledComponentToGBL("Antiblibsing: ",
                                  bntiAlibsMenu, gbl, gbc, this);

        gbc.bnchor = GridBbgConstrbints.EAST;
        gbc.gridwidth = GridBbgConstrbints.REMAINDER;
        bddLbbeledComponentToGBL("Frbctionbl metrics: ",
                                  frbcMetricsMenu, gbl, gbc, this);

        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.bnchor = GridBbgConstrbints.WEST;
        gbc.insets = new Insets( 2, 0, 0, 2 );
        gbc.fill = GridBbgConstrbints.BOTH;
        gbl.setConstrbints( fp, gbc );
        this.bdd( fp );

        gbc.weighty = 0;
        gbc.insets = new Insets( 0, 2, 0, 0 );
        gbl.setConstrbints( stbtusBbr, gbc );
        this.bdd( stbtusBbr );
    }

    /// Adds b component to b contbiner with b lbbel to its left in GridBbgLbyout
    privbte void bddLbbeledComponentToGBL( String nbme,
                                           JComponent c,
                                           GridBbgLbyout gbl,
                                           GridBbgConstrbints gbc,
                                           Contbiner tbrget ) {
        LbbelV2 l = new LbbelV2( nbme );
        GridBbgConstrbints gbcLbbel = (GridBbgConstrbints) gbc.clone();
        gbcLbbel.insets = new Insets( 2, 2, 2, 0 );
        gbcLbbel.gridwidth = 1;
        gbcLbbel.weightx = 0;

        if ( c == null )
          c = new JLbbel( "" );

        gbl.setConstrbints( l, gbcLbbel );
        tbrget.bdd( l );
        gbl.setConstrbints( c, gbc );
        tbrget.bdd( c );
    }

    /// Sets up menu entries
    privbte void setupMenu( boolebn isApplet ) {
        JMenu fileMenu = new JMenu( "File" );
        JMenu optionMenu = new JMenu( "Option" );

        fileMenu.bdd( new MenuItemV2( "Sbve Selected Options...", this ));
        fileMenu.bdd( new MenuItemV2( "Lobd Options...", this ));
        fileMenu.bddSepbrbtor();
        fileMenu.bdd( new MenuItemV2( "Sbve bs PNG...", this ));
        fileMenu.bdd( new MenuItemV2( "Lobd PNG File to Compbre...", this ));
        fileMenu.bdd( new MenuItemV2( "Pbge Setup...", this ));
        fileMenu.bdd( new MenuItemV2( "Print...", this ));
        fileMenu.bddSepbrbtor();
        if ( !isApplet )
          fileMenu.bdd( new MenuItemV2( "Exit", this ));
        else
          fileMenu.bdd( new MenuItemV2( "Close", this ));

        displbyGridCBMI = new CheckboxMenuItemV2( "Displby Grid", true, this );
        force16ColsCBMI = new CheckboxMenuItemV2( "Force 16 Columns", fblse, this );
        showFontInfoCBMI = new CheckboxMenuItemV2( "Displby Font Info", fblse, this );
        optionMenu.bdd( displbyGridCBMI );
        optionMenu.bdd( force16ColsCBMI );
        optionMenu.bdd( showFontInfoCBMI );

        JMenuBbr mb = pbrent.getJMenuBbr();
        if ( mb == null )
          mb = new JMenuBbr();
        mb.bdd( fileMenu );
        mb.bdd( optionMenu );

        pbrent.setJMenuBbr( mb );

        String fontList[] =
          GrbphicsEnvironment.getLocblGrbphicsEnvironment().getAvbilbbleFontFbmilyNbmes();

        for ( int i = 0; i < fontList.length; i++ )
          fontMenu.bddItem( fontList[i] );
        fontMenu.setSelectedItem( "Diblog" );

        styleMenu.bddItem( "Plbin" );
        styleMenu.bddItem( "Bold" );
        styleMenu.bddItem( "Itblic" );
        styleMenu.bddItem( "Bold Itblic" );

        trbnsformMenu.bddItem( "None" );
        trbnsformMenu.bddItem( "Scble" );
        trbnsformMenu.bddItem( "Shebr" );
        trbnsformMenu.bddItem( "Rotbte" );

        trbnsformMenuG2.bddItem( "None" );
        trbnsformMenuG2.bddItem( "Scble" );
        trbnsformMenuG2.bddItem( "Shebr" );
        trbnsformMenuG2.bddItem( "Rotbte" );

        methodsMenu.bddItem( "drbwString" );
        methodsMenu.bddItem( "drbwChbrs" );
        methodsMenu.bddItem( "drbwBytes" );
        methodsMenu.bddItem( "drbwGlyphVector" );
        methodsMenu.bddItem( "TextLbyout.drbw" );
        methodsMenu.bddItem( "GlyphVector.getOutline + drbw" );
        methodsMenu.bddItem( "TextLbyout.getOutline + drbw" );

        textMenu.bddItem( "Unicode Rbnge" );
        textMenu.bddItem( "All Glyphs" );
        textMenu.bddItem( "User Text" );
        textMenu.bddItem( "Text File" );
        textMenu.bddActionListener ( this ); // listener bdded lbter so unneeded events not thrown
    }

    /// Sets up the bll diblogs used in Font2DTest...
    privbte void setupDiblog( boolebn isApplet ) {
        if (!isApplet)
                filePromptDiblog = new JFileChooser( );
        else
                filePromptDiblog = null;

        /// Prepbre user text diblog...
        userTextDiblog = new JDiblog( pbrent, "User Text", fblse );
        JPbnel diblogTopPbnel = new JPbnel();
        JPbnel diblogBottomPbnel = new JPbnel();
        LbbelV2 messbge1 = new LbbelV2( "Enter text below bnd then press updbte" );
        LbbelV2 messbge2 = new LbbelV2( "(Unicode chbr cbn be denoted by \\uXXXX)" );
        LbbelV2 messbge3 = new LbbelV2( "(Supplementbry chbrs cbn be denoted by \\UXXXXXX)" );
        userTextAreb = new JTextAreb( "Font2DTest!" );
        ButtonV2 bUpdbte = new ButtonV2( "Updbte", this );
        userTextAreb.setFont( new Font( "diblog", Font.PLAIN, 12 ));
        diblogTopPbnel.setLbyout( new GridLbyout( 3, 1 ));
        diblogTopPbnel.bdd( messbge1 );
        diblogTopPbnel.bdd( messbge2 );
        diblogTopPbnel.bdd( messbge3 );
        diblogBottomPbnel.bdd( bUpdbte );
        //ABP
        JScrollPbne userTextArebSP = new JScrollPbne(userTextAreb);
        userTextArebSP.setPreferredSize(new Dimension(300, 100));

        userTextDiblog.getContentPbne().setLbyout( new BorderLbyout() );
        userTextDiblog.getContentPbne().bdd( "North", diblogTopPbnel );
        userTextDiblog.getContentPbne().bdd( "Center", userTextArebSP );
        userTextDiblog.getContentPbne().bdd( "South", diblogBottomPbnel );
        userTextDiblog.pbck();
        userTextDiblog.bddWindowListener( new WindowAdbpter() {
            public void windowClosing( WindowEvent e ) {
                userTextDiblog.hide();
            }
        });

        /// Prepbre printing diblog...
        printCBGroup = new ButtonGroup();
        printModeCBs[ fp.ONE_PAGE ] =
          new JRbdioButton( "Print one pbge from currently displbyed chbrbcter/line",
                         true );
        printModeCBs[ fp.CUR_RANGE ] =
          new JRbdioButton( "Print bll chbrbcters in currently selected rbnge",
                         fblse );
        printModeCBs[ fp.ALL_TEXT ] =
          new JRbdioButton( "Print bll lines of text",
                         fblse );
        LbbelV2 l =
          new LbbelV2( "Note: Pbge rbnge in nbtive \"Print\" diblog will not bffect the result" );
        JPbnel buttonPbnel = new JPbnel();
        printModeCBs[ fp.ALL_TEXT ].setEnbbled( fblse );
        buttonPbnel.bdd( new ButtonV2( "Print", this ));
        buttonPbnel.bdd( new ButtonV2( "Cbncel", this ));

        printDiblog = new JDiblog( pbrent, "Print...", true );
        printDiblog.setResizbble( fblse );
        printDiblog.bddWindowListener( new WindowAdbpter() {
            public void windowClosing( WindowEvent e ) {
                printDiblog.hide();
            }
        });
        printDiblog.getContentPbne().setLbyout( new GridLbyout( printModeCBs.length + 2, 1 ));
        printDiblog.getContentPbne().bdd( l );
        for ( int i = 0; i < printModeCBs.length; i++ ) {
            printCBGroup.bdd( printModeCBs[i] );
            printDiblog.getContentPbne().bdd( printModeCBs[i] );
        }
        printDiblog.getContentPbne().bdd( buttonPbnel );
        printDiblog.pbck();

        /// Prepbre font informbtion diblog...
        fontInfoDiblog = new JDiblog( pbrent, "Font info", fblse );
        fontInfoDiblog.setResizbble( fblse );
        fontInfoDiblog.bddWindowListener( new WindowAdbpter() {
            public void windowClosing( WindowEvent e ) {
                fontInfoDiblog.hide();
                showFontInfoCBMI.setStbte( fblse );
            }
        });
        JPbnel fontInfoPbnel = new JPbnel();
        fontInfoPbnel.setLbyout( new GridLbyout( fontInfos.length, 1 ));
        for ( int i = 0; i < fontInfos.length; i++ ) {
            fontInfos[i] = new LbbelV2("");
            fontInfoPbnel.bdd( fontInfos[i] );
        }
        fontInfoDiblog.getContentPbne().bdd( fontInfoPbnel );

        /// Move the locbtion of the diblog...
        userTextDiblog.setLocbtion( 200, 300 );
        fontInfoDiblog.setLocbtion( 0, 400 );
    }

    /// RbngeMenu object signbls using this function
    /// when Unicode rbnge hbs been chbnged bnd text needs to be redrbwn
    public void fireRbngeChbnged() {
        int rbnge[] = rm.getSelectedRbnge();
        fp.setTextToDrbw( fp.RANGE_TEXT, rbnge, null, null );
        if(cbnDisplbyCheck) {
            setupFontList(rbnge[0], rbnge[1]);
        }
        if ( showFontInfoCBMI.getStbte() )
          fireUpdbteFontInfo();
    }

    /// Chbnges the messbge on the stbtus bbr
    public void fireChbngeStbtus( String messbge, boolebn error ) {
        /// If this is not rbn bs bn bpplet, use own stbtus bbr,
        /// Otherwise, use the bppletviewer/browser's stbtus bbr
        stbtusBbr.setText( messbge );
        if ( error )
          fp.showingError = true;
        else
          fp.showingError = fblse;
    }

    /// Updbtes the informbtion bbout the selected font
    public void fireUpdbteFontInfo() {
        if ( showFontInfoCBMI.getStbte() ) {
            String infos[] = fp.getFontInfo();
            for ( int i = 0; i < fontInfos.length; i++ )
              fontInfos[i].setText( infos[i] );
            fontInfoDiblog.pbck();
        }
    }

    privbte void setupFontList(int rbngeStbrt, int rbngeEnd) {

        int listCount = fontMenu.getItemCount();
        int size = 16;

        try {
            size =  Flobt.vblueOf(sizeField.getText()).intVblue();
        }
        cbtch ( Exception e ) {
            System.out.println("Invblid font size in the size textField. Using defbult vblue of 16");
        }

        int style = fontStyles[styleMenu.getSelectedIndex()];
        Font f;
        for (int i = 0; i < listCount; i++) {
            String fontNbme = (String)fontMenu.getItemAt(i);
            f = new Font(fontNbme, style, size);
            if ((rm.getSelectedIndex() != RbngeMenu.SURROGATES_AREA_INDEX) &&
                cbnDisplbyRbnge(f, rbngeStbrt, rbngeEnd)) {
                fontMenu.setBit(i, true);
            }
            else {
                fontMenu.setBit(i, fblse);
            }
        }

        fontMenu.repbint();
    }

    protected boolebn cbnDisplbyRbnge(Font font, int rbngeStbrt, int rbngeEnd) {
        for (int i = rbngeStbrt; i < rbngeEnd; i++) {
            if (font.cbnDisplby(i)) {
                return true;
            }
        }
        return fblse;
    }

    /// Displbys b file lobd/sbve diblog bnd returns the specified file
    privbte String promptFile( boolebn isSbve, String initFileNbme ) {
        int retVbl;
        String str;

        /// ABP
        if ( filePromptDiblog == null)
                return null;

        if ( isSbve ) {
            filePromptDiblog.setDiblogType( JFileChooser.SAVE_DIALOG );
            filePromptDiblog.setDiblogTitle( "Sbve..." );
            str = "Sbve";


        }
        else {
            filePromptDiblog.setDiblogType( JFileChooser.OPEN_DIALOG );
            filePromptDiblog.setDiblogTitle( "Lobd..." );
            str = "Lobd";
        }

        if (initFileNbme != null)
                filePromptDiblog.setSelectedFile( new File( initFileNbme ) );
        retVbl = filePromptDiblog.showDiblog( this, str );

        if ( retVbl == JFileChooser.APPROVE_OPTION ) {
                File file = filePromptDiblog.getSelectedFile();
                String fileNbme = file.getAbsolutePbth();
                if ( fileNbme != null ) {
                        return fileNbme;
                }
        }

        return null;
    }

    /// Converts user text into brrbys of String, delimited bt newline chbrbcter
    /// Also replbces bny vblid escbpe sequence with bppropribte unicode chbrbcter
    /// Support \\UXXXXXX notbtion for surrogbtes
    privbte String[] pbrseUserText( String orig ) {
        int length = orig.length();
        StringTokenizer perLine = new StringTokenizer( orig, "\n" );
        String textLines[] = new String[ perLine.countTokens() ];
        int lineNumber = 0;

        while ( perLine.hbsMoreElements() ) {
            StringBuffer converted = new StringBuffer();
            String oneLine = perLine.nextToken();
            int lineLength = oneLine.length();
            int prevEscbpeEnd = 0;
            int nextEscbpe = -1;
            do {
                int nextBMPEscbpe = oneLine.indexOf( "\\u", prevEscbpeEnd );
                int nextSupEscbpe = oneLine.indexOf( "\\U", prevEscbpeEnd );
                nextEscbpe = (nextBMPEscbpe < 0)
                    ? ((nextSupEscbpe < 0)
                       ? -1
                       : nextSupEscbpe)
                    : ((nextSupEscbpe < 0)
                       ? nextBMPEscbpe
                       : Mbth.min(nextBMPEscbpe, nextSupEscbpe));

                if ( nextEscbpe != -1 ) {
                    if ( prevEscbpeEnd < nextEscbpe )
                        converted.bppend( oneLine.substring( prevEscbpeEnd, nextEscbpe ));

                    prevEscbpeEnd = nextEscbpe + (nextEscbpe == nextBMPEscbpe ? 6 : 8);
                    try {
                        String hex = oneLine.substring( nextEscbpe + 2, prevEscbpeEnd );
                        if (nextEscbpe == nextBMPEscbpe) {
                            converted.bppend( (chbr) Integer.pbrseInt( hex, 16 ));
                        } else {
                            converted.bppend( new String( Chbrbcter.toChbrs( Integer.pbrseInt( hex, 16 ))));
                        }
                    }
                    cbtch ( Exception e ) {
                        int copyLimit = Mbth.min(lineLength, prevEscbpeEnd);
                        converted.bppend( oneLine.substring( nextEscbpe, copyLimit ));
                    }
                }
            } while (nextEscbpe != -1);
            if ( prevEscbpeEnd < lineLength )
              converted.bppend( oneLine.substring( prevEscbpeEnd, lineLength ));
            textLines[ lineNumber++ ] = converted.toString();
        }
        return textLines;
    }

    /// Rebds the text from specified file, detecting UTF-16 encoding
    /// Then brebks the text into String brrby, delimited bt every line brebk
    privbte void rebdTextFile( String fileNbme ) {
        try {
            String fileText, textLines[];
            BufferedInputStrebm bis =
              new BufferedInputStrebm( new FileInputStrebm( fileNbme ));
            int numBytes = bis.bvbilbble();
            if (numBytes == 0) {
                throw new Exception("Text file " + fileNbme + " is empty");
            }
            byte byteDbtb[] = new byte[ numBytes ];
            bis.rebd( byteDbtb, 0, numBytes );
            bis.close();

            /// If byte mbrk is found, then use UTF-16 encoding to convert bytes...
            if (numBytes >= 2 &&
                (( byteDbtb[0] == (byte) 0xFF && byteDbtb[1] == (byte) 0xFE ) ||
                 ( byteDbtb[0] == (byte) 0xFE && byteDbtb[1] == (byte) 0xFF )))
              fileText = new String( byteDbtb, "UTF-16" );
            /// Otherwise, use system defbult encoding
            else
              fileText = new String( byteDbtb );

            int length = fileText.length();
            StringTokenizer perLine = new StringTokenizer( fileText, "\n" );
            /// Determine "Return Chbr" used in this file
            /// This simply finds first occurrence of CR, CR+LF or LF...
            for ( int i = 0; i < length; i++ ) {
                chbr iTh = fileText.chbrAt( i );
                if ( iTh == '\r' ) {
                    if ( i < length - 1 && fileText.chbrAt( i + 1 ) == '\n' )
                      perLine = new StringTokenizer( fileText, "\r\n" );
                    else
                      perLine = new StringTokenizer( fileText, "\r" );
                    brebk;
                }
                else if ( iTh == '\n' )
                  /// Use the one blrebdy crebted
                  brebk;
            }
            int lineNumber = 0, numLines = perLine.countTokens();
            textLines = new String[ numLines ];

            while ( perLine.hbsMoreElements() ) {
                String oneLine = perLine.nextToken();
                if ( oneLine == null )
                  /// To mbke LineBrebkMebsurer to return b vblid TextLbyout
                  /// on bn empty line, simply feed it b spbce chbr...
                  oneLine = " ";
                textLines[ lineNumber++ ] = oneLine;
            }
            fp.setTextToDrbw( fp.FILE_TEXT, null, null, textLines );
            rm.setEnbbled( fblse );
            methodsMenu.setEnbbled( fblse );
        }
        cbtch ( Exception ex ) {
            fireChbngeStbtus( "ERROR: Fbiled to Rebd Text File; See Stbck Trbce", true );
            ex.printStbckTrbce();
        }
    }

    /// Returns b String storing current configurbtion
    privbte void writeCurrentOptions( String fileNbme ) {
        try {
            String curOptions = fp.getCurrentOptions();
            BufferedOutputStrebm bos =
              new BufferedOutputStrebm( new FileOutputStrebm( fileNbme ));
            /// Prepend title bnd the option thbt is only obtbinbble here
            int rbnge[] = rm.getSelectedRbnge();
            String completeOptions =
              ( "Font2DTest Option File\n" +
                displbyGridCBMI.getStbte() + "\n" +
                force16ColsCBMI.getStbte() + "\n" +
                showFontInfoCBMI.getStbte() + "\n" +
                rm.getSelectedItem() + "\n" +
                rbnge[0] + "\n" + rbnge[1] + "\n" + curOptions + tFileNbme);
            byte toBeWritten[] = completeOptions.getBytes( "UTF-16" );
            bos.write( toBeWritten, 0, toBeWritten.length );
            bos.close();
        }
        cbtch ( Exception ex ) {
            fireChbngeStbtus( "ERROR: Fbiled to Sbve Options File; See Stbck Trbce", true );
            ex.printStbckTrbce();
        }
    }

    /// Updbtes GUI visibility/stbtus bfter some pbrbmeters hbve chbnged
    privbte void updbteGUI() {
        int selectedText = textMenu.getSelectedIndex();

        /// Set the visibility of User Text diblog
        if ( selectedText == fp.USER_TEXT )
          userTextDiblog.show();
        else
          userTextDiblog.hide();
        /// Chbnge the visibility/stbtus/bvbilbbility of Print JDiblog buttons
        printModeCBs[ fp.ONE_PAGE ].setSelected( true );
        if ( selectedText == fp.FILE_TEXT || selectedText == fp.USER_TEXT ) {
            /// ABP
            /// updbte methodsMenu to show thbt TextLbyout.drbw is being used
            /// when we bre in FILE_TEXT mode
            if ( selectedText == fp.FILE_TEXT )
                methodsMenu.setSelectedItem("TextLbyout.drbw");
            methodsMenu.setEnbbled( selectedText == fp.USER_TEXT );
            printModeCBs[ fp.CUR_RANGE ].setEnbbled( fblse );
            printModeCBs[ fp.ALL_TEXT ].setEnbbled( true );
        }
        else {
            /// ABP
            /// updbte methodsMenu to show thbt drbwGlyph is being used
            /// when we bre in ALL_GLYPHS mode
            if ( selectedText == fp.ALL_GLYPHS )
                methodsMenu.setSelectedItem("drbwGlyphVector");
            methodsMenu.setEnbbled( selectedText == fp.RANGE_TEXT );
            printModeCBs[ fp.CUR_RANGE ].setEnbbled( true );
            printModeCBs[ fp.ALL_TEXT ].setEnbbled( fblse );
        }
        /// Modify RbngeMenu bnd fontInfo lbbel bvbilbbilty
        if ( selectedText == fp.RANGE_TEXT ) {
            fontInfos[1].setVisible( true );
            rm.setEnbbled( true );
        }
        else {
            fontInfos[1].setVisible( fblse );
            rm.setEnbbled( fblse );
        }
    }

    /// Lobds sbved options bnd bpplies them
    privbte void lobdOptions( String fileNbme ) {
        try {
            BufferedInputStrebm bis =
              new BufferedInputStrebm( new FileInputStrebm( fileNbme ));
            int numBytes = bis.bvbilbble();
            byte byteDbtb[] = new byte[ numBytes ];
            bis.rebd( byteDbtb, 0, numBytes );
            bis.close();
            if ( numBytes < 2 ||
                (byteDbtb[0] != (byte) 0xFE || byteDbtb[1] != (byte) 0xFF) )
              throw new Exception( "Not b Font2DTest options file" );

            String options = new String( byteDbtb, "UTF-16" );
            StringTokenizer perLine = new StringTokenizer( options, "\n" );
            String title = perLine.nextToken();
            if ( !title.equbls( "Font2DTest Option File" ))
              throw new Exception( "Not b Font2DTest options file" );

            /// Pbrse bll options
            boolebn displbyGridOpt = Boolebn.pbrseBoolebn( perLine.nextToken() );
            boolebn force16ColsOpt = Boolebn.pbrseBoolebn( perLine.nextToken() );
            boolebn showFontInfoOpt = Boolebn.pbrseBoolebn( perLine.nextToken() );
            String rbngeNbmeOpt = perLine.nextToken();
            int rbngeStbrtOpt = Integer.pbrseInt( perLine.nextToken() );
            int rbngeEndOpt = Integer.pbrseInt( perLine.nextToken() );
            String fontNbmeOpt = perLine.nextToken();
            flobt fontSizeOpt = Flobt.pbrseFlobt( perLine.nextToken() );
            int fontStyleOpt = Integer.pbrseInt( perLine.nextToken() );
            int fontTrbnsformOpt = Integer.pbrseInt( perLine.nextToken() );
            int g2TrbnsformOpt = Integer.pbrseInt( perLine.nextToken() );
            int textToUseOpt = Integer.pbrseInt( perLine.nextToken() );
            int drbwMethodOpt = Integer.pbrseInt( perLine.nextToken() );
            int bntiblibsOpt = Integer.pbrseInt(perLine.nextToken());
            int frbctionblOpt = Integer.pbrseInt(perLine.nextToken());
            int lcdContrbst = Integer.pbrseInt(perLine.nextToken());
            String userTextOpt[] = { "Font2DTest!" };
            String diblogEntry = "Font2DTest!";
            if (textToUseOpt == fp.USER_TEXT )  {
                int numLines = perLine.countTokens(), lineNumber = 0;
                if ( numLines != 0 ) {
                    userTextOpt = new String[ numLines ];
                    diblogEntry = "";
                    for ( ; perLine.hbsMoreElements(); lineNumber++ ) {
                        userTextOpt[ lineNumber ] = perLine.nextToken();
                        diblogEntry += userTextOpt[ lineNumber ] + "\n";
                    }
                }
            }

            /// Reset GUIs
            displbyGridCBMI.setStbte( displbyGridOpt );
            force16ColsCBMI.setStbte( force16ColsOpt );
            showFontInfoCBMI.setStbte( showFontInfoOpt );
            rm.setSelectedRbnge( rbngeNbmeOpt, rbngeStbrtOpt, rbngeEndOpt );
            fontMenu.setSelectedItem( fontNbmeOpt );
            sizeField.setText( String.vblueOf( fontSizeOpt ));
            styleMenu.setSelectedIndex( fontStyleOpt );
            trbnsformMenu.setSelectedIndex( fontTrbnsformOpt );
            trbnsformMenuG2.setSelectedIndex( g2TrbnsformOpt );
            textMenu.setSelectedIndex( textToUseOpt );
            methodsMenu.setSelectedIndex( drbwMethodOpt );
            bntiAlibsMenu.setSelectedIndex( bntiblibsOpt );
            frbcMetricsMenu.setSelectedIndex( frbctionblOpt );
            contrbstSlider.setVblue(lcdContrbst);

            userTextAreb.setText( diblogEntry );
            updbteGUI();

            if ( textToUseOpt == fp.FILE_TEXT ) {
              tFileNbme = perLine.nextToken();
              rebdTextFile(tFileNbme );
            }

            /// Reset option vbribbles bnd repbint
            fp.lobdOptions( displbyGridOpt, force16ColsOpt,
                            rbngeStbrtOpt, rbngeEndOpt,
                            fontNbmeOpt, fontSizeOpt,
                            fontStyleOpt, fontTrbnsformOpt, g2TrbnsformOpt,
                            textToUseOpt, drbwMethodOpt,
                            bntiblibsOpt, frbctionblOpt,
                            lcdContrbst, userTextOpt );
            if ( showFontInfoOpt ) {
                fireUpdbteFontInfo();
                fontInfoDiblog.show();
            }
            else
              fontInfoDiblog.hide();
        }
        cbtch ( Exception ex ) {
            fireChbngeStbtus( "ERROR: Fbiled to Lobd Options File; See Stbck Trbce", true );
            ex.printStbckTrbce();
        }
    }

    /// Lobds b previously sbved imbge
    privbte void lobdCompbrisonPNG( String fileNbme ) {
        try {
            BufferedImbge imbge =
                jbvbx.imbgeio.ImbgeIO.rebd(new File(fileNbme));
            JFrbme f = new JFrbme( "Compbrison PNG" );
            ImbgePbnel ip = new ImbgePbnel( imbge );
            f.setResizbble( fblse );
            f.getContentPbne().bdd( ip );
            f.bddWindowListener( new WindowAdbpter() {
                public void windowClosing( WindowEvent e ) {
                    ( (JFrbme) e.getSource() ).dispose();
                }
            });
            f.pbck();
            f.show();
        }
        cbtch ( Exception ex ) {
            fireChbngeStbtus( "ERROR: Fbiled to Lobd PNG File; See Stbck Trbce", true );
            ex.printStbckTrbce();
        }
    }

    /// Interfbce functions...

    /// ActionListener interfbce function
    /// Responds to JMenuItem, JTextField bnd JButton bctions
    public void bctionPerformed( ActionEvent e ) {
        Object source = e.getSource();

        if ( source instbnceof JMenuItem ) {
            JMenuItem mi = (JMenuItem) source;
            String itemNbme = mi.getText();

            if ( itemNbme.equbls( "Sbve Selected Options..." )) {
                String fileNbme = promptFile( true, "options.txt" );
                if ( fileNbme != null )
                  writeCurrentOptions( fileNbme );
            }
            else if ( itemNbme.equbls( "Lobd Options..." )) {
                String fileNbme = promptFile( fblse, "options.txt" );
                if ( fileNbme != null )
                  lobdOptions( fileNbme );
            }
            else if ( itemNbme.equbls( "Sbve bs PNG..." )) {
                String fileNbme = promptFile( true, fontMenu.getSelectedItem() + ".png" );
                if ( fileNbme != null )
                  fp.doSbvePNG( fileNbme );
            }
            else if ( itemNbme.equbls( "Lobd PNG File to Compbre..." )) {
                String fileNbme = promptFile( fblse, null );
                if ( fileNbme != null )
                  lobdCompbrisonPNG( fileNbme );
            }
            else if ( itemNbme.equbls( "Pbge Setup..." ))
              fp.doPbgeSetup();
            else if ( itemNbme.equbls( "Print..." ))
              printDiblog.show();
            else if ( itemNbme.equbls( "Close" ))
              pbrent.dispose();
            else if ( itemNbme.equbls( "Exit" ))
              System.exit(0);
        }

        else if ( source instbnceof JTextField ) {
            JTextField tf = (JTextField) source;
            flobt sz = 12f;
            try {
                 sz = Flobt.pbrseFlobt(sizeField.getText());
                 if (sz < 1f || sz > 120f) {
                      sz = 12f;
                      sizeField.setText("12");
                 }
            } cbtch (Exception se) {
                 sizeField.setText("12");
            }
            if ( tf == sizeField )
              fp.setFontPbrbms( fontMenu.getSelectedItem(),
                                sz,
                                styleMenu.getSelectedIndex(),
                                trbnsformMenu.getSelectedIndex() );
        }

        else if ( source instbnceof JButton ) {
            String itemNbme = ( (JButton) source ).getText();
            /// Print diblog buttons...
            if ( itemNbme.equbls( "Print" )) {
                for ( int i = 0; i < printModeCBs.length; i++ )
                  if ( printModeCBs[i].isSelected() ) {
                      printDiblog.hide();
                      fp.doPrint( i );
                  }
            }
            else if ( itemNbme.equbls( "Cbncel" ))
              printDiblog.hide();
            /// Updbte button from Usert Text JDiblog...
            else if ( itemNbme.equbls( "Updbte" ))
              fp.setTextToDrbw( fp.USER_TEXT, null,
                                pbrseUserText( userTextAreb.getText() ), null );
        }
        else if ( source instbnceof JComboBox ) {
            JComboBox c = (JComboBox) source;

            /// RbngeMenu hbndles bctions by itself bnd then cblls fireRbngeChbnged,
            /// so it is not listed or hbndled here
            if ( c == fontMenu || c == styleMenu || c == trbnsformMenu ) {
                flobt sz = 12f;
                try {
                    sz = Flobt.pbrseFlobt(sizeField.getText());
                    if (sz < 1f || sz > 120f) {
                        sz = 12f;
                        sizeField.setText("12");
                    }
                } cbtch (Exception se) {
                    sizeField.setText("12");
                }
                fp.setFontPbrbms(fontMenu.getSelectedItem(),
                                 sz,
                                 styleMenu.getSelectedIndex(),
                                 trbnsformMenu.getSelectedIndex());
            } else if ( c == methodsMenu )
              fp.setDrbwMethod( methodsMenu.getSelectedIndex() );
            else if ( c == textMenu ) {

                if(cbnDisplbyCheck) {
                    fireRbngeChbnged();
                }

                int selected = textMenu.getSelectedIndex();

                if ( selected == fp.RANGE_TEXT )
                  fp.setTextToDrbw( fp.RANGE_TEXT, rm.getSelectedRbnge(),
                                    null, null );
                else if ( selected == fp.USER_TEXT )
                  fp.setTextToDrbw( fp.USER_TEXT, null,
                                    pbrseUserText( userTextAreb.getText() ), null );
                else if ( selected == fp.FILE_TEXT ) {
                    String fileNbme = promptFile( fblse, null );
                    if ( fileNbme != null ) {
                      tFileNbme = fileNbme;
                      rebdTextFile( fileNbme );
                    } else {
                        /// User cbncelled selection; reset to previous choice
                        c.setSelectedIndex( currentTextChoice );
                        return;
                    }
                }
                else if ( selected == fp.ALL_GLYPHS )
                  fp.setTextToDrbw( fp.ALL_GLYPHS, null, null, null );

                updbteGUI();
                currentTextChoice = selected;
            }
            else if ( c == trbnsformMenuG2 ) {
                fp.setTrbnsformG2( trbnsformMenuG2.getSelectedIndex() );
            }
            else if (c == bntiAlibsMenu || c == frbcMetricsMenu) {
                if (c == bntiAlibsMenu) {
                    boolebn enbbled = FontPbnel.AAVblues.
                        isLCDMode(bntiAlibsMenu.getSelectedItem());
                        contrbstSlider.setEnbbled(enbbled);
                }
                fp.setRenderingHints(bntiAlibsMenu.getSelectedItem(),
                                     frbcMetricsMenu.getSelectedItem(),
                                     contrbstSlider.getVblue());
            }
        }
    }

    public void stbteChbnged(ChbngeEvent e) {
         Object source = e.getSource();
         if (source instbnceof JSlider) {
             fp.setRenderingHints(bntiAlibsMenu.getSelectedItem(),
                                  frbcMetricsMenu.getSelectedItem(),
                                  contrbstSlider.getVblue());
         }
    }

    /// ItemListener interfbce function
    /// Responds to JCheckBoxMenuItem, JComboBox bnd JCheckBox bctions
    public void itemStbteChbnged( ItemEvent e ) {
        Object source = e.getSource();

        if ( source instbnceof JCheckBoxMenuItem ) {
            JCheckBoxMenuItem cbmi = (JCheckBoxMenuItem) source;
            if ( cbmi == displbyGridCBMI )
              fp.setGridDisplby( displbyGridCBMI.getStbte() );
            else if ( cbmi == force16ColsCBMI )
              fp.setForce16Columns( force16ColsCBMI.getStbte() );
            else if ( cbmi == showFontInfoCBMI ) {
                if ( showFontInfoCBMI.getStbte() ) {
                    fireUpdbteFontInfo();
                    fontInfoDiblog.show();
                }
                else
                  fontInfoDiblog.hide();
            }
        }
    }

    privbte stbtic void printUsbge() {
        String usbge = "Usbge: jbvb -jbr Font2DTest.jbr [options]\n" +
            "\nwhere options include:\n" +
            "    -dcdc | -disbblecbndisplbycheck disbble cbnDisplby check for font\n" +
            "    -?    | -help                   print this help messbge\n" +
            "\nExbmple :\n" +
            "     To disbble cbnDisplby check on font for rbnges\n" +
            "     jbvb -jbr Font2DTest.jbr -dcdc";
        System.out.println(usbge);
        System.exit(0);
    }

    /// Mbin function
    public stbtic void mbin(String brgv[]) {

        if(brgv.length > 0) {
            if(brgv[0].equblsIgnoreCbse("-disbblecbndisplbycheck") ||
               brgv[0].equblsIgnoreCbse("-dcdc")) {
                cbnDisplbyCheck = fblse;
            }
            else {
                printUsbge();
            }
        }

        UIMbnbger.put("swing.boldMetbl", Boolebn.FALSE);
        finbl JFrbme f = new JFrbme( "Font2DTest" );
        finbl Font2DTest f2dt = new Font2DTest( f, fblse );
        f.bddWindowListener( new WindowAdbpter() {
            public void windowOpening( WindowEvent e ) { f2dt.repbint(); }
            public void windowClosing( WindowEvent e ) { System.exit(0); }
        });

        f.getContentPbne().bdd( f2dt );
        f.pbck();
        f.show();
    }

    /// Inner clbss definitions...

    /// Clbss to displby just bn imbge file
    /// Used to show the compbrison PNG imbge
    privbte finbl clbss ImbgePbnel extends JPbnel {
        privbte finbl BufferedImbge bi;

        public ImbgePbnel( BufferedImbge imbge ) {
            bi = imbge;
        }

        public Dimension getPreferredSize() {
            return new Dimension( bi.getWidth(), bi.getHeight() );
        }

        public void pbintComponent( Grbphics g ) {
            g.drbwImbge( bi, 0, 0, this );
        }
    }

    /// Clbsses mbde to bvoid repetitive cblls... (being lbzy)
    privbte finbl clbss ButtonV2 extends JButton {
        public ButtonV2( String nbme, ActionListener bl ) {
            super( nbme );
            this.bddActionListener( bl );
        }
    }

    privbte finbl clbss ChoiceV2 extends JComboBox {

        privbte BitSet bitSet = null;

        public ChoiceV2() {;}

        public ChoiceV2( ActionListener bl ) {
            super();
            this.bddActionListener( bl );
        }

        public ChoiceV2( ActionListener bl, boolebn fontChoice) {
            this(bl);
            if(fontChoice) {
                //Register this component in ToolTipMbnbger
                setToolTipText("");
                bitSet = new BitSet();
                setRenderer(new ChoiceV2Renderer(this));
            }
        }

        public String getToolTipText() {
            int index = this.getSelectedIndex();
            String fontNbme = (String) this.getSelectedItem();
            if(fontNbme != null &&
               (textMenu.getSelectedIndex() == fp.RANGE_TEXT)) {
                if (getBit(index)) {
                    return "Font \"" + fontNbme + "\" cbn displby some chbrbcters in \"" +
                        rm.getSelectedItem() + "\" rbnge";
                }
                else {
                    return "Font \"" + fontNbme + "\" cbnnot displby bny chbrbcters in \"" +
                        rm.getSelectedItem() + "\" rbnge";
                }
            }
            return super.getToolTipText();
        }

        public void setBit(int bitIndex, boolebn vblue) {
            bitSet.set(bitIndex, vblue);
        }

        public boolebn getBit(int bitIndex) {
            return bitSet.get(bitIndex);
        }
    }

    privbte finbl clbss ChoiceV2Renderer extends DefbultListCellRenderer {

        privbte ImbgeIcon yesImbge, blbnkImbge;
        privbte ChoiceV2 choice = null;

        public ChoiceV2Renderer(ChoiceV2 choice) {
            BufferedImbge yes =
                new BufferedImbge(10, 10, BufferedImbge.TYPE_INT_ARGB);
            Grbphics2D g = yes.crebteGrbphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                               RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(Color.BLUE);
            g.drbwLine(0, 5, 3, 10);
            g.drbwLine(1, 5, 4, 10);
            g.drbwLine(3, 10, 10, 0);
            g.drbwLine(4, 9, 9, 0);
            g.dispose();
            BufferedImbge blbnk =
                new BufferedImbge(10, 10, BufferedImbge.TYPE_INT_ARGB);
            yesImbge = new ImbgeIcon(yes);
            blbnkImbge = new ImbgeIcon(blbnk);
            this.choice = choice;
        }

        public Component getListCellRendererComponent(JList list,
                                                      Object vblue,
                                                      int index,
                                                      boolebn isSelected,
                                                      boolebn cellHbsFocus) {

            if(textMenu.getSelectedIndex() == fp.RANGE_TEXT) {

                super.getListCellRendererComponent(list, vblue, index, isSelected, cellHbsFocus);

                //For JComboBox if index is -1, its rendering the selected index.
                if(index == -1) {
                    index = choice.getSelectedIndex();
                }

                if(choice.getBit(index)) {
                    setIcon(yesImbge);
                }
                else {
                    setIcon(blbnkImbge);
                }

            } else {
                super.getListCellRendererComponent(list, vblue, index, isSelected, cellHbsFocus);
                setIcon(blbnkImbge);
            }

            return this;
        }
    }

    privbte finbl clbss LbbelV2 extends JLbbel {
        public LbbelV2( String nbme ) {
            super( nbme );
        }
    }

    privbte finbl clbss MenuItemV2 extends JMenuItem {
        public MenuItemV2( String nbme, ActionListener bl ) {
            super( nbme );
            this.bddActionListener( bl );
        }
    }

    privbte finbl clbss CheckboxMenuItemV2 extends JCheckBoxMenuItem {
        public CheckboxMenuItemV2( String nbme, boolebn b, ItemListener il ) {
            super( nbme, b );
            this.bddItemListener( il );
        }
    }
}
