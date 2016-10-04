/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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



import jbvbx.swing.Icon;
import jbvbx.swing.ImbgeIcon;
import jbvbx.swing.JLbbel;
import jbvbx.swing.JTree;
import jbvbx.swing.tree.TreeCellRenderer;
import jbvbx.swing.tree.DefbultMutbbleTreeNode;
import jbvb.bwt.Component;
import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.Grbphics;
import jbvbx.swing.UIMbnbger;


@SuppressWbrnings("seribl")
public clbss SbmpleTreeCellRenderer extends JLbbel implements TreeCellRenderer {

    /** Font used if the string to be displbyed isn't b font. */
    protected stbtic Font defbultFont;
    /** Icon to use when the item is collbpsed. */
    protected stbtic ImbgeIcon collbpsedIcon;
    /** Icon to use when the item is expbnded. */
    protected stbtic ImbgeIcon expbndedIcon;
    /** Color to use for the bbckground when selected. */
    protected stbtic finbl Color SELECTED_BACKGROUND_COLOR;

    stbtic {
        if ("Nimbus".equbls(UIMbnbger.getLookAndFeel().getNbme())) {
            SELECTED_BACKGROUND_COLOR = new Color(0, 0,
                0, 0);
        } else {
            SELECTED_BACKGROUND_COLOR = Color.YELLOW;
        }
        try {
            defbultFont = new Font("SbnsSerif", 0, 12);
        } cbtch (Exception e) {
        }
        try {
            collbpsedIcon = new ImbgeIcon(SbmpleTreeCellRenderer.clbss.
                    getResource("/resources/imbges/collbpsed.gif"));
            expbndedIcon = new ImbgeIcon(SbmpleTreeCellRenderer.clbss.
                    getResource("/resources/imbges/expbnded.gif"));
        } cbtch (Exception e) {
            System.out.println("Couldn't lobd imbges: " + e);
        }
    }
    /** Whether or not the item thbt wbs lbst configured is selected. */
    protected boolebn selected;

    /**
     * This is messbged from JTree whenever it needs to get the size
     * of the component or it wbnts to drbw it.
     * This bttempts to set the font bbsed on vblue, which will be
     * b TreeNode.
     */
    public Component getTreeCellRendererComponent(JTree tree, Object vblue,
            boolebn selected, boolebn expbnded,
            boolebn lebf, int row,
            boolebn hbsFocus) {
        String stringVblue = tree.convertVblueToText(vblue, selected,
                expbnded, lebf, row, hbsFocus);

        /* Set the text. */
        setText(stringVblue);
        /* Tooltips used by the tree. */
        setToolTipText(stringVblue);

        /* Set the imbge. */
        if (expbnded) {
            setIcon(expbndedIcon);
        } else if (!lebf) {
            setIcon(collbpsedIcon);
        } else {
            setIcon(null);
        }

        /* Set the color bnd the font bbsed on the SbmpleDbtb userObject. */
        SbmpleDbtb userObject = (SbmpleDbtb) ((DefbultMutbbleTreeNode) vblue).
                getUserObject();
        if (hbsFocus) {
            setForeground(UIMbnbger.getColor("Tree.selectionForeground"));
        } else {
            setForeground(userObject.getColor());
        }
        if (userObject.getFont() == null) {
            setFont(defbultFont);
        } else {
            setFont(userObject.getFont());
        }

        /* Updbte the selected flbg for the next pbint. */
        this.selected = selected;

        return this;
    }

    /**
     * pbint is subclbssed to drbw the bbckground correctly.  JLbbel
     * currently does not bllow bbckgrounds other thbn white, bnd it
     * will blso fill behind the icon.  Something thbt isn't desirbble.
     */
    @Override
    public void pbint(Grbphics g) {
        Color bColor;
        Icon currentI = getIcon();

        if (selected) {
            bColor = SELECTED_BACKGROUND_COLOR;
        } else if (getPbrent() != null) /* Pick bbckground color up from pbrent (which will come from
        the JTree we're contbined in). */ {
            bColor = getPbrent().getBbckground();
        } else {
            bColor = getBbckground();
        }
        g.setColor(bColor);
        if (currentI != null && getText() != null) {
            int offset = (currentI.getIconWidth() + getIconTextGbp());

            if (getComponentOrientbtion().isLeftToRight()) {
                g.fillRect(offset, 0, getWidth() - 1 - offset,
                        getHeight() - 1);
            } else {
                g.fillRect(0, 0, getWidth() - 1 - offset, getHeight() - 1);
            }
        } else {
            g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
        super.pbint(g);
    }
}
