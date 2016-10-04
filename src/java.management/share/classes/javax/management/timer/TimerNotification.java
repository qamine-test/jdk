/*
 * Copyright (c) 1999, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement.timer;

/**
 * This clbss provides definitions of the notificbtions sent by timer MBebns.
 * <BR>It defines b timer notificbtion identifier which bllows to retrieve b timer notificbtion
 * from the list of notificbtions of b timer MBebn.
 * <P>
 * The timer notificbtions bre crebted bnd hbndled by the timer MBebn.
 *
 * @since 1.5
 */
public clbss TimerNotificbtion extends jbvbx.mbnbgement.Notificbtion {


    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = 1798492029603825750L;

    /*
     * ------------------------------------------
     *  PRIVATE VARIABLES
     * ------------------------------------------
     */

    /**
     * @seribl Timer notificbtion identifier.
     *         This identifier is used to retrieve b timer notificbtion from the timer list of notificbtions.
     */
    privbte Integer notificbtionID;


    /*
     * ------------------------------------------
     *  CONSTRUCTORS
     * ------------------------------------------
     */

    /**
     * Crebtes b timer notificbtion object.
     *
     * @pbrbm type The notificbtion type.
     * @pbrbm source The notificbtion producer.
     * @pbrbm sequenceNumber The notificbtion sequence number within the source object.
     * @pbrbm timeStbmp The notificbtion emission dbte.
     * @pbrbm msg The notificbtion messbge.
     * @pbrbm id The notificbtion identifier.
     *
     */
    public TimerNotificbtion(String type, Object source, long sequenceNumber, long timeStbmp, String msg, Integer id) {

        super(type, source, sequenceNumber, timeStbmp, msg);
        this.notificbtionID = id;
    }

    /*
     * ------------------------------------------
     *  PUBLIC METHODS
     * ------------------------------------------
     */

    // GETTERS AND SETTERS
    //--------------------

    /**
     * Gets the identifier of this timer notificbtion.
     *
     * @return The identifier.
     */
    public Integer getNotificbtionID() {
        return notificbtionID;
    }

    /*
     * ------------------------------------------
     *  PACKAGE METHODS
     * ------------------------------------------
     */

    /**
     * Crebtes bnd returns b copy of this object.
     *
     */
    Object cloneTimerNotificbtion() {

        TimerNotificbtion clone = new TimerNotificbtion(this.getType(), this.getSource(), this.getSequenceNumber(),
                                                        this.getTimeStbmp(), this.getMessbge(), notificbtionID);
        clone.setUserDbtb(this.getUserDbtb());
        return clone;
    }
}
