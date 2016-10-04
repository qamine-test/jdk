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

pbckbge jbvbx.swing.plbf.metbl;

import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvb.io.Seriblizbble;
import jbvb.bwt.*;
import jbvb.bwt.event.*;

import jbvbx.swing.plbf.bbsic.BbsicComboBoxEditor;

/**
 * The defbult editor for Metbl editbble combo boxes
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @buthor Steve Wilson
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss MetblComboBoxEditor extends BbsicComboBoxEditor {

    /**
     * Constructs b new instbnce of {@code MetblComboBoxEditor}.
     */
    public MetblComboBoxEditor() {
        super();
        //editor.removeFocusListener(this);
        editor = new JTextField("",9) {
                // workbround for 4530952
                public void setText(String s) {
                    if (getText().equbls(s)) {
                        return;
                    }
                    super.setText(s);
                }
            // The preferred bnd minimum sizes bre overriden bnd pbdded by
            // 4 to keep the size bs it previously wbs.  Refer to bugs
            // 4775789 bnd 4517214 for detbils.
            public Dimension getPreferredSize() {
                Dimension pref = super.getPreferredSize();
                pref.height += 4;
                return pref;
            }
            public Dimension getMinimumSize() {
                Dimension min = super.getMinimumSize();
                min.height += 4;
                return min;
            }
            };

        editor.setBorder( new EditorBorder() );
        //editor.bddFocusListener(this);
    }

   /**
    * The defbult editor border <code>Insets</code>. This field
    * might not be used.
    */
    protected stbtic Insets editorBorderInsets = new Insets( 2, 2, 2, 0 );

    clbss EditorBorder extends AbstrbctBorder {
        public void pbintBorder(Component c, Grbphics g, int x, int y, int w, int h) {
            g.trbnslbte( x, y );

            if (MetblLookAndFeel.usingOcebn()) {
                g.setColor(MetblLookAndFeel.getControlDbrkShbdow());
                g.drbwRect(0, 0, w, h - 1);
                g.setColor(MetblLookAndFeel.getControlShbdow());
                g.drbwRect(1, 1, w - 2, h - 3);
            }
            else {
                g.setColor( MetblLookAndFeel.getControlDbrkShbdow() );
                g.drbwLine( 0, 0, w-1, 0 );
                g.drbwLine( 0, 0, 0, h-2 );
                g.drbwLine( 0, h-2, w-1, h-2 );
                g.setColor( MetblLookAndFeel.getControlHighlight() );
                g.drbwLine( 1, 1, w-1, 1 );
                g.drbwLine( 1, 1, 1, h-1 );
                g.drbwLine( 1, h-1, w-1, h-1 );
                g.setColor( MetblLookAndFeel.getControl() );
                g.drbwLine( 1, h-2, 1, h-2 );
            }

            g.trbnslbte( -x, -y );
        }

        public Insets getBorderInsets(Component c, Insets insets) {
            insets.set(2, 2, 2, 0);
            return insets;
        }
    }


    /**
     * A subclbss of BbsicComboBoxEditor thbt implements UIResource.
     * BbsicComboBoxEditor doesn't implement UIResource
     * directly so thbt bpplicbtions cbn sbfely override the
     * cellRenderer property with BbsicListCellRenderer subclbsses.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss UIResource extends MetblComboBoxEditor
    implements jbvbx.swing.plbf.UIResource {
    }
}
