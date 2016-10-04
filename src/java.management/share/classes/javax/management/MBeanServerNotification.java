/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Represents b notificbtion emitted by the MBebn Server through the MBebnServerDelegbte MBebn.
 * The MBebn Server emits the following types of notificbtions: MBebn registrbtion, MBebn
 * unregistrbtion.
 * <P>
 * To receive MBebnServerNotificbtions, you need to register b listener with
 * the {@link MBebnServerDelegbte MBebnServerDelegbte} MBebn
 * thbt represents the MBebnServer. The ObjectNbme of the MBebnServerDelegbte is
 * {@link MBebnServerDelegbte#DELEGATE_NAME}, which is
 * <CODE>JMImplementbtion:type=MBebnServerDelegbte</CODE>.
 *
 * <p>The following code prints b messbge every time bn MBebn is registered
 * or unregistered in the MBebn Server {@code mbebnServer}:</p>
 *
 * <pre>
 * privbte stbtic finbl NotificbtionListener printListener = new NotificbtionListener() {
 *     public void hbndleNotificbtion(Notificbtion n, Object hbndbbck) {
 *         if (!(n instbnceof MBebnServerNotificbtion)) {
 *             System.out.println("Ignored notificbtion of clbss " + n.getClbss().getNbme());
 *             return;
 *         }
 *         MBebnServerNotificbtion mbsn = (MBebnServerNotificbtion) n;
 *         String whbt;
 *         if (n.getType().equbls(MBebnServerNotificbtion.REGISTRATION_NOTIFICATION))
 *             whbt = "MBebn registered";
 *         else if (n.getType().equbls(MBebnServerNotificbtion.UNREGISTRATION_NOTIFICATION))
 *             whbt = "MBebn unregistered";
 *         else
 *             whbt = "Unknown type " + n.getType();
 *         System.out.println("Received MBebn Server notificbtion: " + whbt + ": " +
 *                 mbsn.getMBebnNbme());
 *     }
 * };
 *
 * ...
 *     mbebnServer.bddNotificbtionListener(
 *             MBebnServerDelegbte.DELEGATE_NAME, printListener, null, null);
 * </pre>
 *
 * <p id="group">
 * An MBebn which is not bn {@link MBebnServerDelegbte} mby blso emit
 * MBebnServerNotificbtions. In pbrticulbr, there is b convention for
 * MBebns to emit bn MBebnServerNotificbtion for b group of MBebns.</p>
 *
 * <p>An MBebnServerNotificbtion emitted to denote the registrbtion or
 * unregistrbtion of b group of MBebns hbs the following chbrbcteristics:
 * <ul><li>Its {@linkplbin Notificbtion#getType() notificbtion type} is
 *     {@code "JMX.mbebn.registered.group"} or
 *     {@code "JMX.mbebn.unregistered.group"}, which cbn blso be written {@link
 *     MBebnServerNotificbtion#REGISTRATION_NOTIFICATION}{@code + ".group"} or
 *     {@link
 *     MBebnServerNotificbtion#UNREGISTRATION_NOTIFICATION}{@code + ".group"}.
 * </li>
 * <li>Its {@linkplbin #getMBebnNbme() MBebn nbme} is bn ObjectNbme pbttern
 *     thbt selects the set (or b superset) of the MBebns being registered
 *     or unregistered</li>
 * <li>Its {@linkplbin Notificbtion#getUserDbtb() user dbtb} cbn optionblly
 *     be set to bn brrby of ObjectNbmes contbining the nbmes of bll MBebns
 *     being registered or unregistered.</li>
 * </ul>
 *
 * <p>
 * MBebns which emit these group registrbtion/unregistrbtion notificbtions will
 * declbre them in their {@link MBebnInfo#getNotificbtions()
 * MBebnNotificbtionInfo}.
 * </p>
 *
 * @since 1.5
 */
public clbss MBebnServerNotificbtion extends Notificbtion {


    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = 2876477500475969677L;
    /**
     * Notificbtion type denoting thbt bn MBebn hbs been registered.
     * Vblue is "JMX.mbebn.registered".
     */
    public stbtic finbl String REGISTRATION_NOTIFICATION =
            "JMX.mbebn.registered";
    /**
     * Notificbtion type denoting thbt bn MBebn hbs been unregistered.
     * Vblue is "JMX.mbebn.unregistered".
     */
    public stbtic finbl String UNREGISTRATION_NOTIFICATION =
            "JMX.mbebn.unregistered";
    /**
     * @seribl The object nbmes of the MBebns concerned by this notificbtion
     */
    privbte finbl ObjectNbme objectNbme;

    /**
     * Crebtes bn MBebnServerNotificbtion object specifying object nbmes of
     * the MBebns thbt cbused the notificbtion bnd the specified notificbtion
     * type.
     *
     * @pbrbm type A string denoting the type of the
     * notificbtion. Set it to one these vblues: {@link
     * #REGISTRATION_NOTIFICATION}, {@link
     * #UNREGISTRATION_NOTIFICATION}.
     * @pbrbm source The MBebnServerNotificbtion object responsible
     * for forwbrding MBebn server notificbtion.
     * @pbrbm sequenceNumber A sequence number thbt cbn be used to order
     * received notificbtions.
     * @pbrbm objectNbme The object nbme of the MBebn thbt cbused the
     * notificbtion.
     *
     */
    public MBebnServerNotificbtion(String type, Object source,
            long sequenceNumber, ObjectNbme objectNbme) {
        super(type, source, sequenceNumber);
        this.objectNbme = objectNbme;
    }

    /**
     * Returns the  object nbme of the MBebn thbt cbused the notificbtion.
     *
     * @return the object nbme of the MBebn thbt cbused the notificbtion.
     */
    public ObjectNbme getMBebnNbme() {
        return objectNbme;
    }

    @Override
    public String toString() {
        return super.toString() + "[mbebnNbme=" + objectNbme + "]";

    }

 }
