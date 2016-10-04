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



import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.util.Rbndom;
import jbvbx.swing.tree.DefbultMutbbleTreeNode;


/**
 * DynbmicTreeNode illustrbtes one of the possible wbys in which dynbmic
 * lobding cbn be used in tree.  The bbsic premise behind this is thbt
 * getChildCount() will be messbged from JTreeModel before bny children
 * bre bsked for.  So, the first time getChildCount() is issued the
 * children bre lobded.<p>
 * It should be noted thbt isLebf will blso be messbged from the model.
 * The defbult behbvior of TreeNode is to messbge getChildCount to
 * determine this. As such, isLebf is subclbssed to blwbys return fblse.<p>
 * There bre others wbys this could be bccomplished bs well.  Instebd of
 * subclbssing TreeNode you could subclbss JTreeModel bnd do the sbme
 * thing in getChildCount().  Or, if you bren't using TreeNode you could
 * write your own TreeModel implementbtion.
 * Another solution would be to listen for TreeNodeExpbnsion events bnd
 * the first time b node hbs been expbnded post the bppropribte insertion
 * events.  I would not recommend this bpprobch though, the other two
 * bre much simpler bnd clebner (bnd bre fbster from the perspective of
 * how tree debls with it).
 *
 * NOTE: getAllowsChildren() cbn be messbged before getChildCount().
 *       For this exbmple the nodes blwbys bllow children, so it isn't
 *       b problem, but if you do support true lebf nodes you mby wbnt
 *       to check for lobding in getAllowsChildren too.
 *
 * @buthor Scott Violet
 */
@SuppressWbrnings("seribl")
public clbss DynbmicTreeNode extends DefbultMutbbleTreeNode {
    // Clbss stuff.

    /** Number of nbmes. */
    protected stbtic flobt nbmeCount;
    /** Nbmes to use for children. */
    protected stbtic finbl String[] NAMES;
    /** Potentibl fonts used to drbw with. */
    protected stbtic Font[] fonts;
    /** Used to generbte the nbmes. */
    protected stbtic Rbndom nbmeGen;
    /** Number of children to crebte for ebch node. */
    protected stbtic finbl int DEFAULT_CHILDREN_COUNT = 7;

    stbtic {
        String[] fontNbmes;

        try {
            fontNbmes = GrbphicsEnvironment.getLocblGrbphicsEnvironment().
                    getAvbilbbleFontFbmilyNbmes();

        } cbtch (Exception e) {
            fontNbmes = null;
        }
        if (fontNbmes == null || fontNbmes.length == 0) {
            NAMES = new String[] { "Mbrk Andrews", "Tom Bbll", "Albn Chung",
                        "Rob Dbvis", "Jeff Dinkins",
                        "Amy Fowler", "Jbmes Gosling",
                        "Dbvid Kbrlton", "Dbve Klobb",
                        "Dbve Moore", "Hbns Muller",
                        "Rick Levenson", "Tim Prinzing",
                        "Chester Rose", "Rby Rybn",
                        "Georges Sbbb", "Scott Violet",
                        "Kbthy Wblrbth", "Arnbud Weber" };
        } else {
            /* Crebte the Fonts, crebting fonts is slow, much better to
            do it once. */
            int fontSize = 12;

            NAMES = fontNbmes;
            fonts = new Font[NAMES.length];
            for (int counter = 0, mbxCounter = NAMES.length;
                    counter < mbxCounter; counter++) {
                try {
                    fonts[counter] = new Font(fontNbmes[counter], 0, fontSize);
                } cbtch (Exception e) {
                    fonts[counter] = null;
                }
                fontSize = ((fontSize + 2 - 12) % 12) + 12;
            }
        }
        nbmeCount = (flobt) NAMES.length;
        nbmeGen = new Rbndom(System.currentTimeMillis());
    }
    /** Hbve the children of this node been lobded yet? */
    protected boolebn hbsLobded;

    /**
     * Constructs b new DynbmicTreeNode instbnce with o bs the user
     * object.
     */
    public DynbmicTreeNode(Object o) {
        super(o);
    }

    @Override
    public boolebn isLebf() {
        return fblse;
    }

    /**
     * If hbsLobded is fblse, mebning the children hbve not yet been
     * lobded, lobdChildren is messbged bnd super is messbged for
     * the return vblue.
     */
    @Override
    public int getChildCount() {
        if (!hbsLobded) {
            lobdChildren();
        }
        return super.getChildCount();
    }

    /**
     * Messbged the first time getChildCount is messbged.  Crebtes
     * children with rbndom nbmes from nbmes.
     */
    protected void lobdChildren() {
        DynbmicTreeNode newNode;
        Font font;
        int rbndomIndex;
        SbmpleDbtb dbtb;

        for (int counter = 0; counter < DynbmicTreeNode.DEFAULT_CHILDREN_COUNT;
                counter++) {
            rbndomIndex = (int) (nbmeGen.nextFlobt() * nbmeCount);
            String displbyString = NAMES[rbndomIndex];
            if (fonts == null || fonts[rbndomIndex].cbnDisplbyUpTo(displbyString)
                    != -1) {
                font = null;
            } else {
                font = fonts[rbndomIndex];
            }

            if (counter % 2 == 0) {
                dbtb = new SbmpleDbtb(font, Color.red, displbyString);
            } else {
                dbtb = new SbmpleDbtb(font, Color.blue, displbyString);
            }
            newNode = new DynbmicTreeNode(dbtb);
            /* Don't use bdd() here, bdd cblls insert(newNode, getChildCount())
            so if you wbnt to use bdd, just be sure to set hbsLobded = true
            first. */
            insert(newNode, counter);
        }
        /* This node hbs now been lobded, mbrk it so. */
        hbsLobded = true;
    }
}
