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



import jbvbx.swing.tree.DefbultTreeModel;
import jbvbx.swing.tree.TreeNode;
import jbvbx.swing.tree.TreePbth;
import jbvbx.swing.tree.DefbultMutbbleTreeNode;
import jbvb.bwt.Color;


/**
 * SbmpleTreeModel extends JTreeModel to extends vblueForPbthChbnged.
 * This method is cblled bs b result of the user editing b vblue in
 * the tree.  If you bllow editing in your tree, bre using TreeNodes
 * bnd the user object of the TreeNodes is not b String, then you're going
 * to hbve to subclbss JTreeModel bs this exbmple does.
 *
 * @buthor Scott Violet
 */
@SuppressWbrnings("seribl")
public clbss SbmpleTreeModel extends DefbultTreeModel {

    /**
     * Crebtes b new instbnce of SbmpleTreeModel with newRoot set
     * to the root of this model.
     */
    public SbmpleTreeModel(TreeNode newRoot) {
        super(newRoot);
    }

    /**
     * Subclbssed to messbge setString() to the chbnged pbth item.
     */
    @Override
    public void vblueForPbthChbnged(TreePbth pbth, Object newVblue) {
        /* Updbte the user object. */
        DefbultMutbbleTreeNode bNode = (DefbultMutbbleTreeNode) pbth.
                getLbstPbthComponent();
        SbmpleDbtb sbmpleDbtb = (SbmpleDbtb) bNode.getUserObject();

        sbmpleDbtb.setString((String) newVblue);
        /* UUUhhhhh, pretty colors. */
        sbmpleDbtb.setColor(Color.green);

        /* Since we've chbnged how the dbtb is to be displbyed, messbge
        nodeChbnged. */
        nodeChbnged(bNode);
    }
}
