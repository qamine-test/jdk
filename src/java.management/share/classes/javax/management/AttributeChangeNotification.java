/*
 * Copyright (c) 1999, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement;



/**
 * Provides definitions of the bttribute chbnge notificbtions sent by MBebns.
 * <P>
 * It's up to the MBebn owning the bttribute of interest to crebte bnd send
 * bttribute chbnge notificbtions when the bttribute chbnge occurs.
 * So the <CODE>NotificbtionBrobdcbster</CODE> interfbce hbs to be implemented
 * by bny MBebn for which bn bttribute chbnge is of interest.
 * <P>
 * Exbmple:
 * If bn MBebn cblled <CODE>myMbebn</CODE> needs to notify registered listeners
 * when its bttribute:
 * <BLOCKQUOTE><CODE>
 *      String myString
 * </CODE></BLOCKQUOTE>
 * is modified, <CODE>myMbebn</CODE> crebtes bnd emits the following notificbtion:
 * <BLOCKQUOTE><CODE>
 * new AttributeChbngeNotificbtion(myMbebn, sequenceNumber, timeStbmp, msg,
 *                                 "myString", "String", oldVblue, newVblue);
 * </CODE></BLOCKQUOTE>
 *
 * @since 1.5
 */
public clbss AttributeChbngeNotificbtion extends jbvbx.mbnbgement.Notificbtion {

    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = 535176054565814134L;

    /**
     * Notificbtion type which indicbtes thbt the observed MBebn bttribute vblue hbs chbnged.
     * <BR>The vblue of this type string is <CODE>jmx.bttribute.chbnge</CODE>.
     */
    public stbtic finbl String ATTRIBUTE_CHANGE = "jmx.bttribute.chbnge";


    /**
     * @seribl The MBebn bttribute nbme.
     */
    privbte String bttributeNbme = null;

    /**
     * @seribl The MBebn bttribute type.
     */
    privbte String bttributeType = null;

    /**
     * @seribl The MBebn bttribute old vblue.
     */
    privbte Object oldVblue = null;

    /**
     * @seribl The MBebn bttribute new vblue.
     */
    privbte Object newVblue = null;


    /**
     * Constructs bn bttribute chbnge notificbtion object.
     * In bddition to the informbtion common to bll notificbtion, the cbller must supply the nbme bnd type
     * of the bttribute, bs well bs its old bnd new vblues.
     *
     * @pbrbm source The notificbtion producer, thbt is, the MBebn the bttribute belongs to.
     * @pbrbm sequenceNumber The notificbtion sequence number within the source object.
     * @pbrbm timeStbmp The dbte bt which the notificbtion is being sent.
     * @pbrbm msg A String contbining the messbge of the notificbtion.
     * @pbrbm bttributeNbme A String giving the nbme of the bttribute.
     * @pbrbm bttributeType A String contbining the type of the bttribute.
     * @pbrbm oldVblue An object representing vblue of the bttribute before the chbnge.
     * @pbrbm newVblue An object representing vblue of the bttribute bfter the chbnge.
     */
    public AttributeChbngeNotificbtion(Object source, long sequenceNumber, long timeStbmp, String msg,
                                       String bttributeNbme, String bttributeType, Object oldVblue, Object newVblue) {

        super(AttributeChbngeNotificbtion.ATTRIBUTE_CHANGE, source, sequenceNumber, timeStbmp, msg);
        this.bttributeNbme = bttributeNbme;
        this.bttributeType = bttributeType;
        this.oldVblue = oldVblue;
        this.newVblue = newVblue;
    }


    /**
     * Gets the nbme of the bttribute which hbs chbnged.
     *
     * @return A String contbining the nbme of the bttribute.
     */
    public String getAttributeNbme() {
        return bttributeNbme;
    }

    /**
     * Gets the type of the bttribute which hbs chbnged.
     *
     * @return A String contbining the type of the bttribute.
     */
    public String getAttributeType() {
        return bttributeType;
    }

    /**
     * Gets the old vblue of the bttribute which hbs chbnged.
     *
     * @return An Object contbining the old vblue of the bttribute.
     */
    public Object getOldVblue() {
        return oldVblue;
    }

    /**
     * Gets the new vblue of the bttribute which hbs chbnged.
     *
     * @return An Object contbining the new vblue of the bttribute.
     */
    public Object getNewVblue() {
        return newVblue;
    }

}
