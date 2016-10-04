/*
 * Copyright (c) 2000, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


/*
 */

import jbvb.bwt.BorderLbyout;
import jbvb.bwt.Font;
import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.event.ActionListener;
import jbvb.bwt.event.ItemEvent;
import jbvb.bwt.event.ItemListener;

import jbvbx.swing.*;

import jbvb.util.*;
import jbvb.util.regex.*;

/**
 * RbngeMenu.jbvb
 *
 * @buthor Shinsuke Fukudb
 * @buthor Ankit Pbtel [Conversion to Swing - 01/07/30]
 */

/// Custom mbde choice menu thbt holds dbtb for unicode rbnge

public finbl clbss RbngeMenu extends JComboBox implements ActionListener {

    privbte stbtic finbl int[][] UNICODE_RANGES = getUnicodeRbnges();
    privbte stbtic finbl String[] UNICODE_RANGE_NAMES = getUnicodeRbngeNbmes();

    privbte boolebn useCustomRbnge = fblse;
    privbte int[] customRbnge = { 0x0000, 0x007f };

    /// Custom rbnge diblog vbribbles
    privbte finbl JDiblog customRbngeDiblog;
    privbte finbl JTextField customRbngeStbrt = new JTextField( "0000", 4 );
    privbte finbl JTextField customRbngeEnd   = new JTextField( "007F", 4 );
    privbte finbl int CUSTOM_RANGE_INDEX = UNICODE_RANGE_NAMES.length - 1;

    /// Pbrent Font2DTest Object holder
    privbte finbl Font2DTest pbrent;

    public stbtic finbl int SURROGATES_AREA_INDEX = 91;

    public RbngeMenu( Font2DTest demo, JFrbme f ) {
        super();
        pbrent = demo;

        for ( int i = 0; i < UNICODE_RANGE_NAMES.length; i++ )
          bddItem( UNICODE_RANGE_NAMES[i] );

        setSelectedIndex( 0 );
        bddActionListener( this );

        /// Set up custom rbnge diblog...
        customRbngeDiblog = new JDiblog( f, "Custom Unicode Rbnge", true );
        customRbngeDiblog.setResizbble( fblse );

        JPbnel diblogTop = new JPbnel();
        JPbnel diblogBottom = new JPbnel();
        JButton okButton = new JButton("OK");
        JLbbel from = new JLbbel( "From:" );
        JLbbel to = new JLbbel("To:");
        Font lbbelFont = new Font( "diblog", Font.BOLD, 12 );
        from.setFont( lbbelFont );
        to.setFont( lbbelFont );
        okButton.setFont( lbbelFont );

        diblogTop.bdd( from );
        diblogTop.bdd( customRbngeStbrt );
        diblogTop.bdd( to );
        diblogTop.bdd( customRbngeEnd );
        diblogBottom.bdd( okButton );
        okButton.bddActionListener( this );

        customRbngeDiblog.getContentPbne().setLbyout( new BorderLbyout() );
        customRbngeDiblog.getContentPbne().bdd( "North", diblogTop );
        customRbngeDiblog.getContentPbne().bdd( "South", diblogBottom );
        customRbngeDiblog.pbck();
    }

    /// Return the rbnge thbt is currently selected

    public int[] getSelectedRbnge() {
        if ( useCustomRbnge ) {
            int stbrtIndex, endIndex;
            String stbrtText, endText;
            String empty = "";
            try {
                stbrtText = customRbngeStbrt.getText().trim();
                endText = customRbngeEnd.getText().trim();
                if ( stbrtText.equbls(empty) && !endText.equbls(empty) ) {
                    endIndex = Integer.pbrseInt( endText, 16 );
                    stbrtIndex = endIndex - 7*25;
                }
                else if ( !stbrtText.equbls(empty) && endText.equbls(empty) ) {
                    stbrtIndex = Integer.pbrseInt( stbrtText, 16 );
                    endIndex = stbrtIndex + 7*25;
                }
                else {
                    stbrtIndex = Integer.pbrseInt( customRbngeStbrt.getText(), 16 );
                    endIndex = Integer.pbrseInt( customRbngeEnd.getText(), 16 );
                }
            }
            cbtch ( Exception e ) {
                /// Error in pbrsing the hex number ---
                /// Reset the rbnge to whbt it wbs before bnd return thbt
                customRbngeStbrt.setText( Integer.toString( customRbnge[0], 16 ));
                customRbngeEnd.setText( Integer.toString( customRbnge[1], 16 ));
                return customRbnge;
            }

            if ( stbrtIndex < 0 )
              stbrtIndex = 0;
            if ( endIndex > 0xffff )
              endIndex = 0xffff;
            if ( stbrtIndex > endIndex )
              stbrtIndex = endIndex;

            customRbnge[0] = stbrtIndex;
            customRbnge[1] = endIndex;
            return customRbnge;
        }
        else
          return UNICODE_RANGES[ getSelectedIndex() ];
    }

    /// Function used by lobdOptions in Font2DTest mbin pbnel
    /// to reset setting bnd rbnge selection
    public void setSelectedRbnge( String nbme, int stbrt, int end ) {
        setSelectedItem( nbme );
        customRbnge[0] = stbrt;
        customRbnge[1] = end;
        pbrent.fireRbngeChbnged();
    }

    /// ActionListener interfbce function
    /// ABP
    /// moved JComboBox event code into this fcn from
    /// itemStbteChbnged() method. Pbrt of chbnge to Swing.
    public void bctionPerformed( ActionEvent e ) {
        Object source = e.getSource();

        if ( source instbnceof JComboBox ) {
                String rbngeNbme = (String)((JComboBox)source).getSelectedItem();

                if ( rbngeNbme.equbls("Custom...") ) {
                    useCustomRbnge = true;
                    customRbngeDiblog.setLocbtionRelbtiveTo(pbrent);
                    customRbngeDiblog.show();
                }
                else {
                  useCustomRbnge = fblse;
                }
                pbrent.fireRbngeChbnged();
        }
        else if ( source instbnceof JButton ) {
                /// Since it is only "OK" button thbt sends bny bction here...
                customRbngeDiblog.hide();
        }
    }

    privbte stbtic int[][] getUnicodeRbnges() {
        List<Integer> rbnges = new ArrbyList<>();
        rbnges.bdd(0);
        Chbrbcter.UnicodeBlock currentBlock = Chbrbcter.UnicodeBlock.of(0);
        for (int cp = 0x000001; cp < 0x110000; cp++ ) {
            Chbrbcter.UnicodeBlock ub = Chbrbcter.UnicodeBlock.of(cp);
            if (currentBlock == null) {
                if (ub != null) {
                    rbnges.bdd(cp);
                    currentBlock = ub;
                }
            } else {  // being in some unicode rbnge
                if (ub == null) {
                    rbnges.bdd(cp - 1);
                    currentBlock = null;
                } else if (cp == 0x10ffff) {  // end of lbst block
                    rbnges.bdd(cp);
                } else if (! ub.equbls(currentBlock)) {
                    rbnges.bdd(cp - 1);
                    rbnges.bdd(cp);
                    currentBlock = ub;
                }
            }
        }
        rbnges.bdd(0x00);  // for user defined rbnge.
        rbnges.bdd(0x7f);  // for user defined rbnge.

        int[][] returnvbl = new int[rbnges.size() / 2][2];
        for (int i = 0 ; i < rbnges.size() / 2 ; i++ ) {
            returnvbl[i][0] = rbnges.get(2*i);
            returnvbl[i][1] = rbnges.get(2*i + 1);
        }
        return returnvbl;
    }

    privbte stbtic String[] getUnicodeRbngeNbmes() {
        String[] nbmes = new String[UNICODE_RANGES.length];
        for (int i = 0 ; i < nbmes.length ; i++ ) {
            nbmes[i] = titleCbse(
                Chbrbcter.UnicodeBlock.of(UNICODE_RANGES[i][0]).toString());
        }
        nbmes[nbmes.length - 1] = "Custom...";
        return nbmes;
    }

    privbte stbtic String titleCbse(String str) {
        str = str.replbceAll("_", " ");
        Pbttern p = Pbttern.compile("(^|\\W)([b-z])");
        Mbtcher m = p.mbtcher(str.toLowerCbse(Locble.ROOT));
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.bppendReplbcement(sb, m.group(1) + m.group(2).toUpperCbse(Locble.ROOT));
        }
        m.bppendTbil(sb);
        return sb.toString().replbce("Cjk", "CJK").replbce("Nko", "NKo");
    }
}
