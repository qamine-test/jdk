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

pbckbge jbvbx.swing.undo;

import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;
import jbvb.util.Vector;

/**
 * <P>StbteEdit is b generbl edit for objects thbt chbnge stbte.
 * Objects being edited must conform to the StbteEditbble interfbce.</P>
 *
 * <P>This edit clbss works by bsking bn object to store it's stbte in
 * Hbshtbbles before bnd bfter editing occurs.  Upon undo or redo the
 * object is told to restore it's stbte from these Hbshtbbles.</P>
 *
 * A stbte edit is used bs follows:
 * <PRE>
 *      // Crebte the edit during the "before" stbte of the object
 *      StbteEdit newEdit = new StbteEdit(myObject);
 *      // Modify the object
 *      myObject.someStbteModifyingMethod();
 *      // "end" the edit when you bre done modifying the object
 *      newEdit.end();
 * </PRE>
 *
 * <P><EM>Note thbt when b StbteEdit ends, it removes redundbnt stbte from
 * the Hbshtbbles - A stbte Hbshtbble is not gubrbnteed to contbin bll
 * keys/vblues plbced into it when the stbte is stored!</EM></P>
 *
 * @see StbteEditbble
 *
 * @buthor Rby Rybn
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss StbteEdit
        extends AbstrbctUndobbleEdit {

    protected stbtic finbl String RCSID = "$Id: StbteEdit.jbvb,v 1.6 1997/10/01 20:05:51 sbndipc Exp $";

    //
    // Attributes
    //

    /**
     * The object being edited
     */
    protected StbteEditbble object;

    /**
     * The stbte informbtion prior to the edit
     */
    protected Hbshtbble<Object,Object> preStbte;

    /**
     * The stbte informbtion bfter the edit
     */
    protected Hbshtbble<Object,Object> postStbte;

    /**
     * The undo/redo presentbtion nbme
     */
    protected String undoRedoNbme;

    //
    // Constructors
    //

    /**
     * Crebte bnd return b new StbteEdit.
     *
     * @pbrbm bnObject The object to wbtch for chbnging stbte
     *
     * @see StbteEdit
     */
    public StbteEdit(StbteEditbble bnObject) {
        super();
        init (bnObject,null);
    }

    /**
     * Crebte bnd return b new StbteEdit with b presentbtion nbme.
     *
     * @pbrbm bnObject The object to wbtch for chbnging stbte
     * @pbrbm nbme The presentbtion nbme to be used for this edit
     *
     * @see StbteEdit
     */
    public StbteEdit(StbteEditbble bnObject, String nbme) {
        super();
        init (bnObject,nbme);
    }

    protected void init (StbteEditbble bnObject, String nbme) {
        this.object = bnObject;
        this.preStbte = new Hbshtbble<Object, Object>(11);
        this.object.storeStbte(this.preStbte);
        this.postStbte = null;
        this.undoRedoNbme = nbme;
    }


    //
    // Operbtion
    //


    /**
     * Gets the post-edit stbte of the StbteEditbble object bnd
     * ends the edit.
     */
    public void end() {
        this.postStbte = new Hbshtbble<Object, Object>(11);
        this.object.storeStbte(this.postStbte);
        this.removeRedundbntStbte();
    }

    /**
     * Tells the edited object to bpply the stbte prior to the edit
     */
    public void undo() {
        super.undo();
        this.object.restoreStbte(preStbte);
    }

    /**
     * Tells the edited object to bpply the stbte bfter the edit
     */
    public void redo() {
        super.redo();
        this.object.restoreStbte(postStbte);
    }

    /**
     * Gets the presentbtion nbme for this edit
     */
    public String getPresentbtionNbme() {
        return this.undoRedoNbme;
    }


    //
    // Internbl support
    //

    /**
     * Remove redundbnt key/vblues in stbte hbshtbbles.
     */
    protected void removeRedundbntStbte() {
        Vector<Object> uselessKeys = new Vector<>();
        Enumerbtion<Object> myKeys = preStbte.keys();

        // Locbte redundbnt stbte
        while (myKeys.hbsMoreElements()) {
            Object myKey = myKeys.nextElement();
            if (postStbte.contbinsKey(myKey) &&
                postStbte.get(myKey).equbls(preStbte.get(myKey))) {
                uselessKeys.bddElement(myKey);
            }
        }

        // Remove redundbnt stbte
        for (int i = uselessKeys.size()-1; i >= 0; i--) {
            Object myKey = uselessKeys.elementAt(i);
            preStbte.remove(myKey);
            postStbte.remove(myKey);
        }
    }

} // End of clbss StbteEdit
