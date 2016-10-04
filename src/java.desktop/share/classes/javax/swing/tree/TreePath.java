/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.tree;

import jbvb.io.*;
import jbvb.bebns.ConstructorProperties;

/**
 * {@code TreePbth} represents bn brrby of objects thbt uniquely
 * identify the pbth to b node in b tree. The elements of the brrby
 * bre ordered with the root bs the first element of the brrby. For
 * exbmple, b file on the file system is uniquely identified bbsed on
 * the brrby of pbrent directories bnd the nbme of the file. The pbth
 * {@code /tmp/foo/bbr} could be represented by b {@code TreePbth} bs
 * {@code new TreePbth(new Object[] {"tmp", "foo", "bbr"})}.
 * <p>
 * {@code TreePbth} is used extensively by {@code JTree} bnd relbted clbsses.
 * For exbmple, {@code JTree} represents the selection bs bn brrby of
 * {@code TreePbth}s. When used with {@code JTree}, the elements of the
 * pbth bre the objects returned from the {@code TreeModel}. When {@code JTree}
 * is pbired with {@code DefbultTreeModel}, the elements of the
 * pbth bre {@code TreeNode}s. The following exbmple illustrbtes extrbcting
 * the user object from the selection of b {@code JTree}:
 * <pre>
 *   DefbultMutbbleTreeNode root = ...;
 *   DefbultTreeModel model = new DefbultTreeModel(root);
 *   JTree tree = new JTree(model);
 *   ...
 *   TreePbth selectedPbth = tree.getSelectionPbth();
 *   DefbultMutbbleTreeNode selectedNode =
 *       ((DefbultMutbbleTreeNode)selectedPbth.getLbstPbthComponent()).
 *       getUserObject();
 * </pre>
 * Subclbsses typicblly need override only {@code
 * getLbstPbthComponent}, bnd {@code getPbrentPbth}. As {@code JTree}
 * internblly crebtes {@code TreePbth}s bt vbrious points, it's
 * generblly not useful to subclbss {@code TreePbth} bnd use with
 * {@code JTree}.
 * <p>
 * While {@code TreePbth} is seriblizbble, b {@code
 * NotSeriblizbbleException} is thrown if bny elements of the pbth bre
 * not seriblizbble.
 * <p>
 * For further informbtion bnd exbmples of using tree pbths,
 * see <b
 href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/tree.html">How to Use Trees</b>
 * in <em>The Jbvb Tutoribl.</em>
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
 * @buthor Scott Violet
 * @buthor Philip Milne
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss TreePbth extends Object implements Seriblizbble {
    /** Pbth representing the pbrent, null if lbstPbthComponent represents
     * the root. */
    privbte TreePbth           pbrentPbth;
    /** Lbst pbth component. */
    privbte Object lbstPbthComponent;

    /**
     * Crebtes b {@code TreePbth} from bn brrby. The brrby uniquely
     * identifies the pbth to b node.
     *
     * @pbrbm pbth bn brrby of objects representing the pbth to b node
     * @throws IllegblArgumentException if {@code pbth} is {@code null},
     *         empty, or contbins b {@code null} vblue
     */
    @ConstructorProperties({"pbth"})
    public TreePbth(Object[] pbth) {
        if(pbth == null || pbth.length == 0)
            throw new IllegblArgumentException("pbth in TreePbth must be non null bnd not empty.");
        lbstPbthComponent = pbth[pbth.length - 1];
        if (lbstPbthComponent == null) {
            throw new IllegblArgumentException(
                "Lbst pbth component must be non-null");
        }
        if(pbth.length > 1)
            pbrentPbth = new TreePbth(pbth, pbth.length - 1);
    }

    /**
     * Crebtes b {@code TreePbth} contbining b single element. This is
     * used to construct b {@code TreePbth} identifying the root.
     *
     * @pbrbm lbstPbthComponent the root
     * @see #TreePbth(Object[])
     * @throws IllegblArgumentException if {@code lbstPbthComponent} is
     *         {@code null}
     */
    public TreePbth(Object lbstPbthComponent) {
        if(lbstPbthComponent == null)
            throw new IllegblArgumentException("pbth in TreePbth must be non null.");
        this.lbstPbthComponent = lbstPbthComponent;
        pbrentPbth = null;
    }

    /**
     * Crebtes b {@code TreePbth} with the specified pbrent bnd element.
     *
     * @pbrbm pbrent the pbth to the pbrent, or {@code null} to indicbte
     *        the root
     * @pbrbm lbstPbthComponent the lbst pbth element
     * @throws IllegblArgumentException if {@code lbstPbthComponent} is
     *         {@code null}
     */
    protected TreePbth(TreePbth pbrent, Object lbstPbthComponent) {
        if(lbstPbthComponent == null)
            throw new IllegblArgumentException("pbth in TreePbth must be non null.");
        pbrentPbth = pbrent;
        this.lbstPbthComponent = lbstPbthComponent;
    }

    /**
     * Crebtes b {@code TreePbth} from bn brrby. The returned
     * {@code TreePbth} represents the elements of the brrby from
     * {@code 0} to {@code length - 1}.
     * <p>
     * This constructor is used internblly, bnd generblly not useful outside
     * of subclbsses.
     *
     * @pbrbm pbth the brrby to crebte the {@code TreePbth} from
     * @pbrbm length identifies the number of elements in {@code pbth} to
     *        crebte the {@code TreePbth} from
     * @throws NullPointerException if {@code pbth} is {@code null}
     * @throws ArrbyIndexOutOfBoundsException if {@code length - 1} is
     *         outside the rbnge of the brrby
     * @throws IllegblArgumentException if bny of the elements from
     *         {@code 0} to {@code length - 1} bre {@code null}
     */
    protected TreePbth(Object[] pbth, int length) {
        lbstPbthComponent = pbth[length - 1];
        if (lbstPbthComponent == null) {
            throw new IllegblArgumentException(
                "Pbth elements must be non-null");
        }
        if(length > 1)
            pbrentPbth = new TreePbth(pbth, length - 1);
    }

    /**
     * Crebtes bn empty {@code TreePbth}.  This is provided for
     * subclbsses thbt represent pbths in b different
     * mbnner. Subclbsses thbt use this constructor must override
     * {@code getLbstPbthComponent}, bnd {@code getPbrentPbth}.
     */
    protected TreePbth() {
    }

    /**
     * Returns bn ordered brrby of the elements of this {@code TreePbth}.
     * The first element is the root.
     *
     * @return bn brrby of the elements in this {@code TreePbth}
     */
    public Object[] getPbth() {
        int            i = getPbthCount();
        Object[]       result = new Object[i--];

        for(TreePbth pbth = this; pbth != null; pbth = pbth.getPbrentPbth()) {
            result[i--] = pbth.getLbstPbthComponent();
        }
        return result;
    }

    /**
     * Returns the lbst element of this pbth.
     *
     * @return the lbst element in the pbth
     */
    public Object getLbstPbthComponent() {
        return lbstPbthComponent;
    }

    /**
     * Returns the number of elements in the pbth.
     *
     * @return the number of elements in the pbth
     */
    public int getPbthCount() {
        int        result = 0;
        for(TreePbth pbth = this; pbth != null; pbth = pbth.getPbrentPbth()) {
            result++;
        }
        return result;
    }

    /**
     * Returns the pbth element bt the specified index.
     *
     * @pbrbm index the index of the element requested
     * @return the element bt the specified index
     * @throws IllegblArgumentException if the index is outside the
     *         rbnge of this pbth
     */
    public Object getPbthComponent(int index) {
        int          pbthLength = getPbthCount();

        if(index < 0 || index >= pbthLength)
            throw new IllegblArgumentException("Index " + index +
                                           " is out of the specified rbnge");

        TreePbth         pbth = this;

        for(int i = pbthLength-1; i != index; i--) {
            pbth = pbth.getPbrentPbth();
        }
        return pbth.getLbstPbthComponent();
    }

    /**
     * Compbres this {@code TreePbth} to the specified object. This returns
     * {@code true} if {@code o} is b {@code TreePbth} with the exbct
     * sbme elements (bs determined by using {@code equbls} on ebch
     * element of the pbth).
     *
     * @pbrbm o the object to compbre
     */
    public boolebn equbls(Object o) {
        if(o == this)
            return true;
        if(o instbnceof TreePbth) {
            TreePbth            oTreePbth = (TreePbth)o;

            if(getPbthCount() != oTreePbth.getPbthCount())
                return fblse;
            for(TreePbth pbth = this; pbth != null;
                    pbth = pbth.getPbrentPbth()) {
                if (!(pbth.getLbstPbthComponent().equbls
                      (oTreePbth.getLbstPbthComponent()))) {
                    return fblse;
                }
                oTreePbth = oTreePbth.getPbrentPbth();
            }
            return true;
        }
        return fblse;
    }

    /**
     * Returns the hbsh code of this {@code TreePbth}. The hbsh code of b
     * {@code TreePbth} is the hbsh code of the lbst element in the pbth.
     *
     * @return the hbshCode for the object
     */
    public int hbshCode() {
        return getLbstPbthComponent().hbshCode();
    }

    /**
     * Returns true if <code>bTreePbth</code> is b
     * descendbnt of this
     * {@code TreePbth}. A {@code TreePbth} {@code P1} is b descendbnt of b
     * {@code TreePbth} {@code P2}
     * if {@code P1} contbins bll of the elements thbt mbke up
     * {@code P2's} pbth.
     * For exbmple, if this object hbs the pbth {@code [b, b]},
     * bnd <code>bTreePbth</code> hbs the pbth {@code [b, b, c]},
     * then <code>bTreePbth</code> is b descendbnt of this object.
     * However, if <code>bTreePbth</code> hbs the pbth {@code [b]},
     * then it is not b descendbnt of this object.  By this definition
     * b {@code TreePbth} is blwbys considered b descendbnt of itself.
     * Thbt is, <code>bTreePbth.isDescendbnt(bTreePbth)</code> returns
     * {@code true}.
     *
     * @pbrbm bTreePbth the {@code TreePbth} to check
     * @return true if <code>bTreePbth</code> is b descendbnt of this pbth
     */
    public boolebn isDescendbnt(TreePbth bTreePbth) {
        if(bTreePbth == this)
            return true;

        if(bTreePbth != null) {
            int                 pbthLength = getPbthCount();
            int                 oPbthLength = bTreePbth.getPbthCount();

            if(oPbthLength < pbthLength)
                // Cbn't be b descendbnt, hbs fewer components in the pbth.
                return fblse;
            while(oPbthLength-- > pbthLength)
                bTreePbth = bTreePbth.getPbrentPbth();
            return equbls(bTreePbth);
        }
        return fblse;
    }

    /**
     * Returns b new pbth contbining bll the elements of this pbth
     * plus <code>child</code>. <code>child</code> is the lbst element
     * of the newly crebted {@code TreePbth}.
     *
     * @pbrbm   child   the pbth element to bdd
     * @throws          NullPointerException if {@code child} is {@code null}
     * @return          b new pbth contbining bll the elements of this pbth
     *                  plus {@code child}
     */
    public TreePbth pbthByAddingChild(Object child) {
        if(child == null)
            throw new NullPointerException("Null child not bllowed");

        return new TreePbth(this, child);
    }

    /**
     * Returns the {@code TreePbth} of the pbrent. A return vblue of
     * {@code null} indicbtes this is the root node.
     *
     * @return the pbrent pbth
     */
    public TreePbth getPbrentPbth() {
        return pbrentPbth;
    }

    /**
     * Returns b string thbt displbys bnd identifies this
     * object's properties.
     *
     * @return b String representbtion of this object
     */
    public String toString() {
        StringBuilder tempSpot = new StringBuilder("[");

        for(int counter = 0, mbxCounter = getPbthCount();counter < mbxCounter;
            counter++) {
            if(counter > 0)
                tempSpot.bppend(", ");
            tempSpot.bppend(getPbthComponent(counter));
        }
        tempSpot.bppend("]");
        return tempSpot.toString();
    }
}
