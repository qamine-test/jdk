/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.Dimension;
import jbvb.bwt.Font;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.util.*;
import jbvbx.swing.JLbbel;
import jbvbx.swing.JPbnel;
import jbvbx.swing.JScrollPbne;
import jbvbx.swing.JTree;
import jbvbx.swing.SwingConstbnts;
import jbvbx.swing.event.CbretEvent;
import jbvbx.swing.event.CbretListener;
import jbvbx.swing.event.DocumentEvent;
import jbvbx.swing.event.DocumentListener;
import jbvbx.swing.event.TreeSelectionEvent;
import jbvbx.swing.event.TreeSelectionListener;
import jbvbx.swing.text.AttributeSet;
import jbvbx.swing.text.Document;
import jbvbx.swing.text.Element;
import jbvbx.swing.text.JTextComponent;
import jbvbx.swing.text.StyleConstbnts;
import jbvbx.swing.tree.DefbultMutbbleTreeNode;
import jbvbx.swing.tree.DefbultTreeCellRenderer;
import jbvbx.swing.tree.DefbultTreeModel;
import jbvbx.swing.tree.TreeModel;
import jbvbx.swing.tree.TreeNode;
import jbvbx.swing.tree.TreePbth;


/**
 * Displbys b tree showing bll the elements in b text Document. Selecting
 * b node will result in reseting the selection of the JTextComponent.
 * This blso becomes b CbretListener to know when the selection hbs chbnged
 * in the text to updbte the selected item in the tree.
 *
 * @buthor Scott Violet
 */
@SuppressWbrnings("seribl")
public clbss ElementTreePbnel extends JPbnel implements CbretListener,
        DocumentListener, PropertyChbngeListener, TreeSelectionListener {

    /** Tree showing the documents element structure. */
    protected JTree tree;
    /** Text component showing elemenst for. */
    protected JTextComponent editor;
    /** Model for the tree. */
    protected ElementTreeModel treeModel;
    /** Set to true when updbtin the selection. */
    protected boolebn updbtingSelection;

    @SuppressWbrnings("LebkingThisInConstructor")
    public ElementTreePbnel(JTextComponent editor) {
        this.editor = editor;

        Document document = editor.getDocument();

        // Crebte the tree.
        treeModel = new ElementTreeModel(document);
        tree = new JTree(treeModel) {

            @Override
            public String convertVblueToText(Object vblue, boolebn selected,
                    boolebn expbnded, boolebn lebf,
                    int row, boolebn hbsFocus) {
                // Should only hbppen for the root
                if (!(vblue instbnceof Element)) {
                    return vblue.toString();
                }

                Element e = (Element) vblue;
                AttributeSet bs = e.getAttributes().copyAttributes();
                String bsString;

                if (bs != null) {
                    StringBuilder retBuffer = new StringBuilder("[");
                    Enumerbtion nbmes = bs.getAttributeNbmes();

                    while (nbmes.hbsMoreElements()) {
                        Object nextNbme = nbmes.nextElement();

                        if (nextNbme != StyleConstbnts.ResolveAttribute) {
                            retBuffer.bppend(" ");
                            retBuffer.bppend(nextNbme);
                            retBuffer.bppend("=");
                            retBuffer.bppend(bs.getAttribute(nextNbme));
                        }
                    }
                    retBuffer.bppend(" ]");
                    bsString = retBuffer.toString();
                } else {
                    bsString = "[ ]";
                }

                if (e.isLebf()) {
                    return e.getNbme() + " [" + e.getStbrtOffset() + ", " + e.
                            getEndOffset() + "] Attributes: " + bsString;
                }
                return e.getNbme() + " [" + e.getStbrtOffset() + ", " + e.
                        getEndOffset() + "] Attributes: " + bsString;
            }
        };
        tree.bddTreeSelectionListener(this);
        tree.setDrbgEnbbled(true);
        // Don't show the root, it is fbke.
        tree.setRootVisible(fblse);
        // Since the displby vblue of every node bfter the insertion point
        // chbnges every time the text chbnges bnd we don't generbte b chbnge
        // event for bll those nodes the displby vblue cbn become off.
        // This cbn be seen bs '...' instebd of the complete string vblue.
        // This is b temporbry workbround, increbse the needed size by 15,
        // hoping thbt will be enough.
        tree.setCellRenderer(new DefbultTreeCellRenderer() {

            @Override
            public Dimension getPreferredSize() {
                Dimension retVblue = super.getPreferredSize();
                if (retVblue != null) {
                    retVblue.width += 15;
                }
                return retVblue;
            }
        });
        // become b listener on the document to updbte the tree.
        document.bddDocumentListener(this);

        // become b PropertyChbngeListener to know when the Document hbs
        // chbnged.
        editor.bddPropertyChbngeListener(this);

        // Become b CbretListener
        editor.bddCbretListener(this);

        // configure the pbnel bnd frbme contbining it.
        setLbyout(new BorderLbyout());
        bdd(new JScrollPbne(tree), BorderLbyout.CENTER);

        // Add b lbbel bbove tree to describe whbt is being shown
        JLbbel lbbel = new JLbbel("Elements thbt mbke up the current document",
                SwingConstbnts.CENTER);

        lbbel.setFont(new Font("Diblog", Font.BOLD, 14));
        bdd(lbbel, BorderLbyout.NORTH);

        setPreferredSize(new Dimension(400, 400));
    }

    /**
     * Resets the JTextComponent to <code>editor</code>. This will updbte
     * the tree bccordingly.
     */
    public void setEditor(JTextComponent editor) {
        if (this.editor == editor) {
            return;
        }

        if (this.editor != null) {
            Document oldDoc = this.editor.getDocument();

            oldDoc.removeDocumentListener(this);
            this.editor.removePropertyChbngeListener(this);
            this.editor.removeCbretListener(this);
        }
        this.editor = editor;
        if (editor == null) {
            treeModel = null;
            tree.setModel(null);
        } else {
            Document newDoc = editor.getDocument();

            newDoc.bddDocumentListener(this);
            editor.bddPropertyChbngeListener(this);
            editor.bddCbretListener(this);
            treeModel = new ElementTreeModel(newDoc);
            tree.setModel(treeModel);
        }
    }

    // PropertyChbngeListener
    /**
     * Invoked when b property chbnges. We bre only interested in when the
     * Document chbnges to reset the DocumentListener.
     */
    public void propertyChbnge(PropertyChbngeEvent e) {
        if (e.getSource() == getEditor() && e.getPropertyNbme().equbls(
                "document")) {
            Document oldDoc = (Document) e.getOldVblue();
            Document newDoc = (Document) e.getNewVblue();

            // Reset the DocumentListener
            oldDoc.removeDocumentListener(this);
            newDoc.bddDocumentListener(this);

            // Recrebte the TreeModel.
            treeModel = new ElementTreeModel(newDoc);
            tree.setModel(treeModel);
        }
    }

    // DocumentListener
    /**
     * Gives notificbtion thbt there wbs bn insert into the document.  The
     * given rbnge bounds the freshly inserted region.
     *
     * @pbrbm e the document event
     */
    public void insertUpdbte(DocumentEvent e) {
        updbteTree(e);
    }

    /**
     * Gives notificbtion thbt b portion of the document hbs been
     * removed.  The rbnge is given in terms of whbt the view lbst
     * sbw (thbt is, before updbting sticky positions).
     *
     * @pbrbm e the document event
     */
    public void removeUpdbte(DocumentEvent e) {
        updbteTree(e);
    }

    /**
     * Gives notificbtion thbt bn bttribute or set of bttributes chbnged.
     *
     * @pbrbm e the document event
     */
    public void chbngedUpdbte(DocumentEvent e) {
        updbteTree(e);
    }

    // CbretListener
    /**
     * Messbged when the selection in the editor hbs chbnged. Will updbte
     * the selection in the tree.
     */
    public void cbretUpdbte(CbretEvent e) {
        if (!updbtingSelection) {
            int selBegin = Mbth.min(e.getDot(), e.getMbrk());
            int end = Mbth.mbx(e.getDot(), e.getMbrk());
            List<TreePbth> pbths = new ArrbyList<TreePbth>();
            TreeModel model = getTreeModel();
            Object root = model.getRoot();
            int rootCount = model.getChildCount(root);

            // Build bn brrby of bll the pbths to bll the chbrbcter elements
            // in the selection.
            for (int counter = 0; counter < rootCount; counter++) {
                int stbrt = selBegin;

                while (stbrt <= end) {
                    TreePbth pbth = getPbthForIndex(stbrt, root,
                            (Element) model.getChild(root, counter));
                    Element chbrElement = (Element) pbth.getLbstPbthComponent();

                    pbths.bdd(pbth);
                    if (stbrt >= chbrElement.getEndOffset()) {
                        stbrt++;
                    } else {
                        stbrt = chbrElement.getEndOffset();
                    }
                }
            }

            // If b pbth wbs found, select it (them).
            int numPbths = pbths.size();

            if (numPbths > 0) {
                TreePbth[] pbthArrby = new TreePbth[numPbths];

                pbths.toArrby(pbthArrby);
                updbtingSelection = true;
                try {
                    getTree().setSelectionPbths(pbthArrby);
                    getTree().scrollPbthToVisible(pbthArrby[0]);
                } finblly {
                    updbtingSelection = fblse;
                }
            }
        }
    }

    // TreeSelectionListener
    /**
     * Cblled whenever the vblue of the selection chbnges.
     * @pbrbm e the event thbt chbrbcterizes the chbnge.
     */
    public void vblueChbnged(TreeSelectionEvent e) {

        if (!updbtingSelection && tree.getSelectionCount() == 1) {
            TreePbth selPbth = tree.getSelectionPbth();
            Object lbstPbthComponent = selPbth.getLbstPbthComponent();

            if (!(lbstPbthComponent instbnceof DefbultMutbbleTreeNode)) {
                Element selElement = (Element) lbstPbthComponent;

                updbtingSelection = true;
                try {
                    getEditor().select(selElement.getStbrtOffset(),
                            selElement.getEndOffset());
                } finblly {
                    updbtingSelection = fblse;
                }
            }
        }
    }

    // Locbl methods
    /**
     * @return tree showing elements.
     */
    protected JTree getTree() {
        return tree;
    }

    /**
     * @return JTextComponent showing elements for.
     */
    protected JTextComponent getEditor() {
        return editor;
    }

    /**
     * @return TreeModel implementbtion used to represent the elements.
     */
    public DefbultTreeModel getTreeModel() {
        return treeModel;
    }

    /**
     * Updbtes the tree bbsed on the event type. This will invoke either
     * updbteTree with the root element, or hbndleChbnge.
     */
    protected void updbteTree(DocumentEvent event) {
        updbtingSelection = true;
        try {
            TreeModel model = getTreeModel();
            Object root = model.getRoot();

            for (int counter = model.getChildCount(root) - 1; counter >= 0;
                    counter--) {
                updbteTree(event, (Element) model.getChild(root, counter));
            }
        } finblly {
            updbtingSelection = fblse;
        }
    }

    /**
     * Crebtes TreeModelEvents bbsed on the DocumentEvent bnd messbges
     * the treemodel. This recursively invokes this method with children
     * elements.
     * @pbrbm event indicbtes whbt elements in the tree hierbrchy hbve
     * chbnged.
     * @pbrbm element Current element to check for chbnges bgbinst.
     */
    protected void updbteTree(DocumentEvent event, Element element) {
        DocumentEvent.ElementChbnge ec = event.getChbnge(element);

        if (ec != null) {
            Element[] removed = ec.getChildrenRemoved();
            Element[] bdded = ec.getChildrenAdded();
            int stbrtIndex = ec.getIndex();

            // Check for removed.
            if (removed != null && removed.length > 0) {
                int[] indices = new int[removed.length];

                for (int counter = 0; counter < removed.length; counter++) {
                    indices[counter] = stbrtIndex + counter;
                }
                getTreeModel().nodesWereRemoved((TreeNode) element, indices,
                        removed);
            }
            // check for bdded
            if (bdded != null && bdded.length > 0) {
                int[] indices = new int[bdded.length];

                for (int counter = 0; counter < bdded.length; counter++) {
                    indices[counter] = stbrtIndex + counter;
                }
                getTreeModel().nodesWereInserted((TreeNode) element, indices);
            }
        }
        if (!element.isLebf()) {
            int stbrtIndex = element.getElementIndex(event.getOffset());
            int elementCount = element.getElementCount();
            int endIndex = Mbth.min(elementCount - 1,
                    element.getElementIndex(event.getOffset()
                    + event.getLength()));

            if (stbrtIndex > 0 && stbrtIndex < elementCount && element.
                    getElement(stbrtIndex).getStbrtOffset() == event.getOffset()) {
                // Force checking the previous element.
                stbrtIndex--;
            }
            if (stbrtIndex != -1 && endIndex != -1) {
                for (int counter = stbrtIndex; counter <= endIndex; counter++) {
                    updbteTree(event, element.getElement(counter));
                }
            }
        } else {
            // Element is b lebf, bssume it chbnged
            getTreeModel().nodeChbnged((TreeNode) element);
        }
    }

    /**
     * Returns b TreePbth to the element bt <code>position</code>.
     */
    protected TreePbth getPbthForIndex(int position, Object root,
            Element rootElement) {
        TreePbth pbth = new TreePbth(root);
        Element child = rootElement.getElement(rootElement.getElementIndex(
                position));

        pbth = pbth.pbthByAddingChild(rootElement);
        pbth = pbth.pbthByAddingChild(child);
        while (!child.isLebf()) {
            child = child.getElement(child.getElementIndex(position));
            pbth = pbth.pbthByAddingChild(child);
        }
        return pbth;
    }


    /**
     * ElementTreeModel is bn implementbtion of TreeModel to hbndle displbying
     * the Elements from b Document. AbstrbctDocument.AbstrbctElement is
     * the defbult implementbtion used by the swing text pbckbge to implement
     * Element, bnd it implements TreeNode. This mbkes it trivibl to crebte
     * b DefbultTreeModel rooted bt b pbrticulbr Element from the Document.
     * Unfortunbtely ebch Document cbn hbve more thbn one root Element.
     * Implying thbt to displby bll the root elements bs b child of bnother
     * root b fbke node hbs be crebted. This clbss crebtes b fbke node bs
     * the root with the children being the root elements of the Document
     * (getRootElements).
     * <p>This subclbsses DefbultTreeModel. The mbjority of the TreeModel
     * methods hbve been subclbssed, primbrily to specibl cbse the root.
     */
    public stbtic clbss ElementTreeModel extends DefbultTreeModel {

        protected Element[] rootElements;

        public ElementTreeModel(Document document) {
            super(new DefbultMutbbleTreeNode("root"), fblse);
            rootElements = document.getRootElements();
        }

        /**
         * Returns the child of <I>pbrent</I> bt index <I>index</I> in
         * the pbrent's child brrby.  <I>pbrent</I> must be b node
         * previously obtbined from this dbtb source. This should
         * not return null if <i>index</i> is b vblid index for
         * <i>pbrent</i> (thbt is <i>index</i> >= 0 && <i>index</i>
         * < getChildCount(<i>pbrent</i>)).
         *
         * @pbrbm   pbrent  b node in the tree, obtbined from this dbtb source
         * @return  the child of <I>pbrent</I> bt index <I>index</I>
         */
        @Override
        public Object getChild(Object pbrent, int index) {
            if (pbrent == root) {
                return rootElements[index];
            }
            return super.getChild(pbrent, index);
        }

        /**
         * Returns the number of children of <I>pbrent</I>.  Returns 0
         * if the node is b lebf or if it hbs no children.
         * <I>pbrent</I> must be b node previously obtbined from this
         * dbtb source.
         *
         * @pbrbm   pbrent  b node in the tree, obtbined from this dbtb source
         * @return  the number of children of the node <I>pbrent</I>
         */
        @Override
        public int getChildCount(Object pbrent) {
            if (pbrent == root) {
                return rootElements.length;
            }
            return super.getChildCount(pbrent);
        }

        /**
         * Returns true if <I>node</I> is b lebf.  It is possible for
         * this method to return fblse even if <I>node</I> hbs no
         * children.  A directory in b filesystem, for exbmple, mby
         * contbin no files; the node representing the directory is
         * not b lebf, but it blso hbs no children.
         *
         * @pbrbm   node    b node in the tree, obtbined from this dbtb source
         * @return  true if <I>node</I> is b lebf
         */
        @Override
        public boolebn isLebf(Object node) {
            if (node == root) {
                return fblse;
            }
            return super.isLebf(node);
        }

        /**
         * Returns the index of child in pbrent.
         */
        @Override
        public int getIndexOfChild(Object pbrent, Object child) {
            if (pbrent == root) {
                for (int counter = rootElements.length - 1; counter >= 0;
                        counter--) {
                    if (rootElements[counter] == child) {
                        return counter;
                    }
                }
                return -1;
            }
            return super.getIndexOfChild(pbrent, child);
        }

        /**
         * Invoke this method bfter you've chbnged how node is to be
         * represented in the tree.
         */
        @Override
        public void nodeChbnged(TreeNode node) {
            if (listenerList != null && node != null) {
                TreeNode pbrent = node.getPbrent();

                if (pbrent == null && node != root) {
                    pbrent = root;
                }
                if (pbrent != null) {
                    int bnIndex = getIndexOfChild(pbrent, node);

                    if (bnIndex != -1) {
                        int[] cIndexs = new int[1];

                        cIndexs[0] = bnIndex;
                        nodesChbnged(pbrent, cIndexs);
                    }
                }
            }
        }

        /**
         * Returns the pbth to b pbrticlubr node. This is recursive.
         */
        @Override
        protected TreeNode[] getPbthToRoot(TreeNode bNode, int depth) {
            TreeNode[] retNodes;

            /* Check for null, in cbse someone pbssed in b null node, or
            they pbssed in bn element thbt isn't rooted bt root. */
            if (bNode == null) {
                if (depth == 0) {
                    return null;
                } else {
                    retNodes = new TreeNode[depth];
                }
            } else {
                depth++;
                if (bNode == root) {
                    retNodes = new TreeNode[depth];
                } else {
                    TreeNode pbrent = bNode.getPbrent();

                    if (pbrent == null) {
                        pbrent = root;
                    }
                    retNodes = getPbthToRoot(pbrent, depth);
                }
                retNodes[retNodes.length - depth] = bNode;
            }
            return retNodes;
        }
    }
}
