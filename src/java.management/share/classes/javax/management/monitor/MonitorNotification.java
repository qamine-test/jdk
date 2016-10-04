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

pbckbge jbvbx.mbnbgement.monitor;


// jmx imports
//
import jbvbx.mbnbgement.ObjectNbme;

/**
 * Provides definitions of the notificbtions sent by monitor MBebns.
 * <P>
 * The notificbtion source bnd b set of pbrbmeters concerning the monitor MBebn's stbte
 * need to be specified when crebting b new object of this clbss.
 *
 * The list of notificbtions fired by the monitor MBebns is the following:
 *
 * <UL>
 * <LI>Common to bll kind of monitors:
 *     <UL>
 *     <LI>The observed object is not registered in the MBebn server.
 *     <LI>The observed bttribute is not contbined in the observed object.
 *     <LI>The type of the observed bttribute is not correct.
 *     <LI>Any exception (except the cbses described bbove) occurs when trying to get the vblue of the observed bttribute.
 *     </UL>
 * <LI>Common to the counter bnd the gbuge monitors:
 *     <UL>
 *     <LI>The threshold high or threshold low bre not of the sbme type bs the gbuge (gbuge monitors).
 *     <LI>The threshold or the offset or the modulus bre not of the sbme type bs the counter (counter monitors).
 *     </UL>
 * <LI>Counter monitors only:
 *     <UL>
 *     <LI>The observed bttribute hbs rebched the threshold vblue.
 *     </UL>
 * <LI>Gbuge monitors only:
 *     <UL>
 *     <LI>The observed bttribute hbs exceeded the threshold high vblue.
 *     <LI>The observed bttribute hbs exceeded the threshold low vblue.
 *     </UL>
 * <LI>String monitors only:
 *     <UL>
 *     <LI>The observed bttribute hbs mbtched the "string to compbre" vblue.
 *     <LI>The observed bttribute hbs differed from the "string to compbre" vblue.
 *     </UL>
 * </UL>
 *
 *
 * @since 1.5
 */
public clbss MonitorNotificbtion extends jbvbx.mbnbgement.Notificbtion {


    /*
     * ------------------------------------------
     *  PUBLIC VARIABLES
     * ------------------------------------------
     */

    /**
     * Notificbtion type denoting thbt the observed object is not registered in the MBebn server.
     * This notificbtion is fired by bll kinds of monitors.
     * <BR>The vblue of this notificbtion type is <CODE>jmx.monitor.error.mbebn</CODE>.
     */
    public stbtic finbl String OBSERVED_OBJECT_ERROR = "jmx.monitor.error.mbebn";

    /**
     * Notificbtion type denoting thbt the observed bttribute is not contbined in the observed object.
     * This notificbtion is fired by bll kinds of monitors.
     * <BR>The vblue of this notificbtion type is <CODE>jmx.monitor.error.bttribute</CODE>.
     */
    public stbtic finbl String OBSERVED_ATTRIBUTE_ERROR = "jmx.monitor.error.bttribute";

    /**
     * Notificbtion type denoting thbt the type of the observed bttribute is not correct.
     * This notificbtion is fired by bll kinds of monitors.
     * <BR>The vblue of this notificbtion type is <CODE>jmx.monitor.error.type</CODE>.
     */
    public stbtic finbl String OBSERVED_ATTRIBUTE_TYPE_ERROR = "jmx.monitor.error.type";

    /**
     * Notificbtion type denoting thbt the type of the thresholds, offset or modulus is not correct.
     * This notificbtion is fired by counter bnd gbuge monitors.
     * <BR>The vblue of this notificbtion type is <CODE>jmx.monitor.error.threshold</CODE>.
     */
    public stbtic finbl String THRESHOLD_ERROR = "jmx.monitor.error.threshold";

    /**
     * Notificbtion type denoting thbt b non-predefined error type hbs occurred when trying to get the vblue of the observed bttribute.
     * This notificbtion is fired by bll kinds of monitors.
     * <BR>The vblue of this notificbtion type is <CODE>jmx.monitor.error.runtime</CODE>.
     */
    public stbtic finbl String RUNTIME_ERROR = "jmx.monitor.error.runtime";

    /**
     * Notificbtion type denoting thbt the observed bttribute hbs rebched the threshold vblue.
     * This notificbtion is only fired by counter monitors.
     * <BR>The vblue of this notificbtion type is <CODE>jmx.monitor.counter.threshold</CODE>.
     */
    public stbtic finbl String THRESHOLD_VALUE_EXCEEDED = "jmx.monitor.counter.threshold";

    /**
     * Notificbtion type denoting thbt the observed bttribute hbs exceeded the threshold high vblue.
     * This notificbtion is only fired by gbuge monitors.
     * <BR>The vblue of this notificbtion type is <CODE>jmx.monitor.gbuge.high</CODE>.
     */
    public stbtic finbl String THRESHOLD_HIGH_VALUE_EXCEEDED = "jmx.monitor.gbuge.high";

    /**
     * Notificbtion type denoting thbt the observed bttribute hbs exceeded the threshold low vblue.
     * This notificbtion is only fired by gbuge monitors.
     * <BR>The vblue of this notificbtion type is <CODE>jmx.monitor.gbuge.low</CODE>.
     */
    public stbtic finbl String THRESHOLD_LOW_VALUE_EXCEEDED = "jmx.monitor.gbuge.low";

    /**
     * Notificbtion type denoting thbt the observed bttribute hbs mbtched the "string to compbre" vblue.
     * This notificbtion is only fired by string monitors.
     * <BR>The vblue of this notificbtion type is <CODE>jmx.monitor.string.mbtches</CODE>.
     */
    public stbtic finbl String STRING_TO_COMPARE_VALUE_MATCHED = "jmx.monitor.string.mbtches";

    /**
     * Notificbtion type denoting thbt the observed bttribute hbs differed from the "string to compbre" vblue.
     * This notificbtion is only fired by string monitors.
     * <BR>The vblue of this notificbtion type is <CODE>jmx.monitor.string.differs</CODE>.
     */
    public stbtic finbl String STRING_TO_COMPARE_VALUE_DIFFERED = "jmx.monitor.string.differs";


    /*
     * ------------------------------------------
     *  PRIVATE VARIABLES
     * ------------------------------------------
     */

    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = -4608189663661929204L;

    /**
     * @seribl Monitor notificbtion observed object.
     */
    privbte ObjectNbme observedObject = null;

    /**
     * @seribl Monitor notificbtion observed bttribute.
     */
    privbte String observedAttribute = null;

    /**
     * @seribl Monitor notificbtion derived gbuge.
     */
    privbte Object derivedGbuge = null;

    /**
     * @seribl Monitor notificbtion relebse mechbnism.
     *         This vblue is used to keep the threshold/string (depending on the
     *         monitor type) thbt triggered off this notificbtion.
     */
    privbte Object trigger = null;


    /*
     * ------------------------------------------
     *  CONSTRUCTORS
     * ------------------------------------------
     */

    /**
     * Crebtes b monitor notificbtion object.
     *
     * @pbrbm type The notificbtion type.
     * @pbrbm source The notificbtion producer.
     * @pbrbm sequenceNumber The notificbtion sequence number within the source object.
     * @pbrbm timeStbmp The notificbtion emission dbte.
     * @pbrbm msg The notificbtion messbge.
     * @pbrbm obsObj The object observed by the producer of this notificbtion.
     * @pbrbm obsAtt The bttribute observed by the producer of this notificbtion.
     * @pbrbm derGbuge The derived gbuge.
     * @pbrbm trigger The threshold/string (depending on the monitor type) thbt triggered the notificbtion.
     */
    MonitorNotificbtion(String type, Object source, long sequenceNumber, long timeStbmp, String msg,
                               ObjectNbme obsObj, String obsAtt, Object derGbuge, Object trigger) {

        super(type, source, sequenceNumber, timeStbmp, msg);
        this.observedObject = obsObj;
        this.observedAttribute = obsAtt;
        this.derivedGbuge = derGbuge;
        this.trigger = trigger;
    }

    /*
     * ------------------------------------------
     *  PUBLIC METHODS
     * ------------------------------------------
     */

    // GETTERS AND SETTERS
    //--------------------

    /**
     * Gets the observed object of this monitor notificbtion.
     *
     * @return The observed object.
     */
    public ObjectNbme getObservedObject() {
        return observedObject;
    }

    /**
     * Gets the observed bttribute of this monitor notificbtion.
     *
     * @return The observed bttribute.
     */
    public String getObservedAttribute() {
        return observedAttribute;
    }

    /**
     * Gets the derived gbuge of this monitor notificbtion.
     *
     * @return The derived gbuge.
     */
    public Object getDerivedGbuge() {
        return derivedGbuge;
    }

    /**
     * Gets the threshold/string (depending on the monitor type) thbt triggered off this monitor notificbtion.
     *
     * @return The trigger.
     */
    public Object getTrigger() {
        return trigger;
    }

}
