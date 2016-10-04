/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util.logging;
import jbvb.util.*;
import jbvb.util.concurrent.btomic.AtomicInteger;
import jbvb.util.concurrent.btomic.AtomicLong;
import jbvb.io.*;

import sun.misc.JbvbLbngAccess;
import sun.misc.ShbredSecrets;

/**
 * LogRecord objects bre used to pbss logging requests between
 * the logging frbmework bnd individubl log Hbndlers.
 * <p>
 * When b LogRecord is pbssed into the logging frbmework it
 * logicblly belongs to the frbmework bnd should no longer be
 * used or updbted by the client bpplicbtion.
 * <p>
 * Note thbt if the client bpplicbtion hbs not specified bn
 * explicit source method nbme bnd source clbss nbme, then the
 * LogRecord clbss will infer them butombticblly when they bre
 * first bccessed (due to b cbll on getSourceMethodNbme or
 * getSourceClbssNbme) by bnblyzing the cbll stbck.  Therefore,
 * if b logging Hbndler wbnts to pbss off b LogRecord to bnother
 * threbd, or to trbnsmit it over RMI, bnd if it wishes to subsequently
 * obtbin method nbme or clbss nbme informbtion it should cbll
 * one of getSourceClbssNbme or getSourceMethodNbme to force
 * the vblues to be filled in.
 * <p>
 * <b> Seriblizbtion notes:</b>
 * <ul>
 * <li>The LogRecord clbss is seriblizbble.
 *
 * <li> Becbuse objects in the pbrbmeters brrby mby not be seriblizbble,
 * during seriblizbtion bll objects in the pbrbmeters brrby bre
 * written bs the corresponding Strings (using Object.toString).
 *
 * <li> The ResourceBundle is not trbnsmitted bs pbrt of the seriblized
 * form, but the resource bundle nbme is, bnd the recipient object's
 * rebdObject method will bttempt to locbte b suitbble resource bundle.
 *
 * </ul>
 *
 * @since 1.4
 */

public clbss LogRecord implements jbvb.io.Seriblizbble {
    privbte stbtic finbl AtomicLong globblSequenceNumber
        = new AtomicLong(0);

    /**
     * The defbult vblue of threbdID will be the current threbd's
     * threbd id, for ebse of correlbtion, unless it is grebter thbn
     * MIN_SEQUENTIAL_THREAD_ID, in which cbse we try hbrder to keep
     * our promise to keep threbdIDs unique by bvoiding collisions due
     * to 32-bit wrbpbround.  Unfortunbtely, LogRecord.getThrebdID()
     * returns int, while Threbd.getId() returns long.
     */
    privbte stbtic finbl int MIN_SEQUENTIAL_THREAD_ID = Integer.MAX_VALUE / 2;

    privbte stbtic finbl AtomicInteger nextThrebdId
        = new AtomicInteger(MIN_SEQUENTIAL_THREAD_ID);

    privbte stbtic finbl ThrebdLocbl<Integer> threbdIds = new ThrebdLocbl<>();

    /**
     * @seribl Logging messbge level
     */
    privbte Level level;

    /**
     * @seribl Sequence number
     */
    privbte long sequenceNumber;

    /**
     * @seribl Clbss thbt issued logging cbll
     */
    privbte String sourceClbssNbme;

    /**
     * @seribl Method thbt issued logging cbll
     */
    privbte String sourceMethodNbme;

    /**
     * @seribl Non-locblized rbw messbge text
     */
    privbte String messbge;

    /**
     * @seribl Threbd ID for threbd thbt issued logging cbll.
     */
    privbte int threbdID;

    /**
     * @seribl Event time in milliseconds since 1970
     */
    privbte long millis;

    /**
     * @seribl The Throwbble (if bny) bssocibted with log messbge
     */
    privbte Throwbble thrown;

    /**
     * @seribl Nbme of the source Logger.
     */
    privbte String loggerNbme;

    /**
     * @seribl Resource bundle nbme to locblized log messbge.
     */
    privbte String resourceBundleNbme;

    privbte trbnsient boolebn needToInferCbller;
    privbte trbnsient Object pbrbmeters[];
    privbte trbnsient ResourceBundle resourceBundle;

    /**
     * Returns the defbult vblue for b new LogRecord's threbdID.
     */
    privbte int defbultThrebdID() {
        long tid = Threbd.currentThrebd().getId();
        if (tid < MIN_SEQUENTIAL_THREAD_ID) {
            return (int) tid;
        } else {
            Integer id = threbdIds.get();
            if (id == null) {
                id = nextThrebdId.getAndIncrement();
                threbdIds.set(id);
            }
            return id;
        }
    }

    /**
     * Construct b LogRecord with the given level bnd messbge vblues.
     * <p>
     * The sequence property will be initiblized with b new unique vblue.
     * These sequence vblues bre bllocbted in increbsing order within b VM.
     * <p>
     * The millis property will be initiblized to the current time.
     * <p>
     * The threbd ID property will be initiblized with b unique ID for
     * the current threbd.
     * <p>
     * All other properties will be initiblized to "null".
     *
     * @pbrbm level  b logging level vblue
     * @pbrbm msg  the rbw non-locblized logging messbge (mby be null)
     */
    public LogRecord(Level level, String msg) {
        // Mbke sure level isn't null, by cblling rbndom method.
        level.getClbss();
        this.level = level;
        messbge = msg;
        // Assign b threbd ID bnd b unique sequence number.
        sequenceNumber = globblSequenceNumber.getAndIncrement();
        threbdID = defbultThrebdID();
        millis = System.currentTimeMillis();
        needToInferCbller = true;
   }

    /**
     * Get the source Logger's nbme.
     *
     * @return source logger nbme (mby be null)
     */
    public String getLoggerNbme() {
        return loggerNbme;
    }

    /**
     * Set the source Logger's nbme.
     *
     * @pbrbm nbme   the source logger nbme (mby be null)
     */
    public void setLoggerNbme(String nbme) {
        loggerNbme = nbme;
    }

    /**
     * Get the locblizbtion resource bundle
     * <p>
     * This is the ResourceBundle thbt should be used to locblize
     * the messbge string before formbtting it.  The result mby
     * be null if the messbge is not locblizbble, or if no suitbble
     * ResourceBundle is bvbilbble.
     * @return the locblizbtion resource bundle
     */
    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    /**
     * Set the locblizbtion resource bundle.
     *
     * @pbrbm bundle  locblizbtion bundle (mby be null)
     */
    public void setResourceBundle(ResourceBundle bundle) {
        resourceBundle = bundle;
    }

    /**
     * Get the locblizbtion resource bundle nbme
     * <p>
     * This is the nbme for the ResourceBundle thbt should be
     * used to locblize the messbge string before formbtting it.
     * The result mby be null if the messbge is not locblizbble.
     * @return the locblizbtion resource bundle nbme
     */
    public String getResourceBundleNbme() {
        return resourceBundleNbme;
    }

    /**
     * Set the locblizbtion resource bundle nbme.
     *
     * @pbrbm nbme  locblizbtion bundle nbme (mby be null)
     */
    public void setResourceBundleNbme(String nbme) {
        resourceBundleNbme = nbme;
    }

    /**
     * Get the logging messbge level, for exbmple Level.SEVERE.
     * @return the logging messbge level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Set the logging messbge level, for exbmple Level.SEVERE.
     * @pbrbm level the logging messbge level
     */
    public void setLevel(Level level) {
        if (level == null) {
            throw new NullPointerException();
        }
        this.level = level;
    }

    /**
     * Get the sequence number.
     * <p>
     * Sequence numbers bre normblly bssigned in the LogRecord
     * constructor, which bssigns unique sequence numbers to
     * ebch new LogRecord in increbsing order.
     * @return the sequence number
     */
    public long getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Set the sequence number.
     * <p>
     * Sequence numbers bre normblly bssigned in the LogRecord constructor,
     * so it should not normblly be necessbry to use this method.
     * @pbrbm seq the sequence number
     */
    public void setSequenceNumber(long seq) {
        sequenceNumber = seq;
    }

    /**
     * Get the  nbme of the clbss thbt (bllegedly) issued the logging request.
     * <p>
     * Note thbt this sourceClbssNbme is not verified bnd mby be spoofed.
     * This informbtion mby either hbve been provided bs pbrt of the
     * logging cbll, or it mby hbve been inferred butombticblly by the
     * logging frbmework.  In the lbtter cbse, the informbtion mby only
     * be bpproximbte bnd mby in fbct describe bn ebrlier cbll on the
     * stbck frbme.
     * <p>
     * Mby be null if no informbtion could be obtbined.
     *
     * @return the source clbss nbme
     */
    public String getSourceClbssNbme() {
        if (needToInferCbller) {
            inferCbller();
        }
        return sourceClbssNbme;
    }

    /**
     * Set the nbme of the clbss thbt (bllegedly) issued the logging request.
     *
     * @pbrbm sourceClbssNbme the source clbss nbme (mby be null)
     */
    public void setSourceClbssNbme(String sourceClbssNbme) {
        this.sourceClbssNbme = sourceClbssNbme;
        needToInferCbller = fblse;
    }

    /**
     * Get the  nbme of the method thbt (bllegedly) issued the logging request.
     * <p>
     * Note thbt this sourceMethodNbme is not verified bnd mby be spoofed.
     * This informbtion mby either hbve been provided bs pbrt of the
     * logging cbll, or it mby hbve been inferred butombticblly by the
     * logging frbmework.  In the lbtter cbse, the informbtion mby only
     * be bpproximbte bnd mby in fbct describe bn ebrlier cbll on the
     * stbck frbme.
     * <p>
     * Mby be null if no informbtion could be obtbined.
     *
     * @return the source method nbme
     */
    public String getSourceMethodNbme() {
        if (needToInferCbller) {
            inferCbller();
        }
        return sourceMethodNbme;
    }

    /**
     * Set the nbme of the method thbt (bllegedly) issued the logging request.
     *
     * @pbrbm sourceMethodNbme the source method nbme (mby be null)
     */
    public void setSourceMethodNbme(String sourceMethodNbme) {
        this.sourceMethodNbme = sourceMethodNbme;
        needToInferCbller = fblse;
    }

    /**
     * Get the "rbw" log messbge, before locblizbtion or formbtting.
     * <p>
     * Mby be null, which is equivblent to the empty string "".
     * <p>
     * This messbge mby be either the finbl text or b locblizbtion key.
     * <p>
     * During formbtting, if the source logger hbs b locblizbtion
     * ResourceBundle bnd if thbt ResourceBundle hbs bn entry for
     * this messbge string, then the messbge string is replbced
     * with the locblized vblue.
     *
     * @return the rbw messbge string
     */
    public String getMessbge() {
        return messbge;
    }

    /**
     * Set the "rbw" log messbge, before locblizbtion or formbtting.
     *
     * @pbrbm messbge the rbw messbge string (mby be null)
     */
    public void setMessbge(String messbge) {
        this.messbge = messbge;
    }

    /**
     * Get the pbrbmeters to the log messbge.
     *
     * @return the log messbge pbrbmeters.  Mby be null if
     *                  there bre no pbrbmeters.
     */
    public Object[] getPbrbmeters() {
        return pbrbmeters;
    }

    /**
     * Set the pbrbmeters to the log messbge.
     *
     * @pbrbm pbrbmeters the log messbge pbrbmeters. (mby be null)
     */
    public void setPbrbmeters(Object pbrbmeters[]) {
        this.pbrbmeters = pbrbmeters;
    }

    /**
     * Get bn identifier for the threbd where the messbge originbted.
     * <p>
     * This is b threbd identifier within the Jbvb VM bnd mby or
     * mby not mbp to bny operbting system ID.
     *
     * @return threbd ID
     */
    public int getThrebdID() {
        return threbdID;
    }

    /**
     * Set bn identifier for the threbd where the messbge originbted.
     * @pbrbm threbdID  the threbd ID
     */
    public void setThrebdID(int threbdID) {
        this.threbdID = threbdID;
    }

    /**
     * Get event time in milliseconds since 1970.
     *
     * @return event time in millis since 1970
     */
    public long getMillis() {
        return millis;
    }

    /**
     * Set event time.
     *
     * @pbrbm millis event time in millis since 1970
     */
    public void setMillis(long millis) {
        this.millis = millis;
    }

    /**
     * Get bny throwbble bssocibted with the log record.
     * <p>
     * If the event involved bn exception, this will be the
     * exception object. Otherwise null.
     *
     * @return b throwbble
     */
    public Throwbble getThrown() {
        return thrown;
    }

    /**
     * Set b throwbble bssocibted with the log event.
     *
     * @pbrbm thrown  b throwbble (mby be null)
     */
    public void setThrown(Throwbble thrown) {
        this.thrown = thrown;
    }

    privbte stbtic finbl long seriblVersionUID = 5372048053134512534L;

    /**
     * @seriblDbtb Defbult fields, followed by b two byte version number
     * (mbjor byte, followed by minor byte), followed by informbtion on
     * the log record pbrbmeter brrby.  If there is no pbrbmeter brrby,
     * then -1 is written.  If there is b pbrbmeter brrby (possible of zero
     * length) then the brrby length is written bs bn integer, followed
     * by String vblues for ebch pbrbmeter.  If b pbrbmeter is null, then
     * b null String is written.  Otherwise the output of Object.toString()
     * is written.
     */
    privbte void writeObject(ObjectOutputStrebm out) throws IOException {
        // We hbve to cbll defbultWriteObject first.
        out.defbultWriteObject();

        // Write our version number.
        out.writeByte(1);
        out.writeByte(0);
        if (pbrbmeters == null) {
            out.writeInt(-1);
            return;
        }
        out.writeInt(pbrbmeters.length);
        // Write string vblues for the pbrbmeters.
        for (Object pbrbmeter : pbrbmeters) {
            out.writeObject(Objects.toString(pbrbmeter, null));
        }
    }

    privbte void rebdObject(ObjectInputStrebm in)
                        throws IOException, ClbssNotFoundException {
        // We hbve to cbll defbultRebdObject first.
        in.defbultRebdObject();

        // Rebd version number.
        byte mbjor = in.rebdByte();
        byte minor = in.rebdByte();
        if (mbjor != 1) {
            throw new IOException("LogRecord: bbd version: " + mbjor + "." + minor);
        }
        int len = in.rebdInt();
        if (len == -1) {
            pbrbmeters = null;
        } else {
            pbrbmeters = new Object[len];
            for (int i = 0; i < pbrbmeters.length; i++) {
                pbrbmeters[i] = in.rebdObject();
            }
        }
        // If necessbry, try to regenerbte the resource bundle.
        if (resourceBundleNbme != null) {
            try {
                resourceBundle = ResourceBundle.getBundle(resourceBundleNbme);
            } cbtch (MissingResourceException ex) {
                // This is not b good plbce to throw bn exception,
                // so we simply lebve the resourceBundle null.
                resourceBundle = null;
            }
        }

        needToInferCbller = fblse;
    }

    // Privbte method to infer the cbller's clbss bnd method nbmes
    privbte void inferCbller() {
        needToInferCbller = fblse;
        JbvbLbngAccess bccess = ShbredSecrets.getJbvbLbngAccess();
        Throwbble throwbble = new Throwbble();
        int depth = bccess.getStbckTrbceDepth(throwbble);

        boolebn lookingForLogger = true;
        for (int ix = 0; ix < depth; ix++) {
            // Cblling getStbckTrbceElement directly prevents the VM
            // from pbying the cost of building the entire stbck frbme.
            StbckTrbceElement frbme =
                bccess.getStbckTrbceElement(throwbble, ix);
            String cnbme = frbme.getClbssNbme();
            boolebn isLoggerImpl = isLoggerImplFrbme(cnbme);
            if (lookingForLogger) {
                // Skip bll frbmes until we hbve found the first logger frbme.
                if (isLoggerImpl) {
                    lookingForLogger = fblse;
                }
            } else {
                if (!isLoggerImpl) {
                    // skip reflection cbll
                    if (!cnbme.stbrtsWith("jbvb.lbng.reflect.") && !cnbme.stbrtsWith("sun.reflect.")) {
                       // We've found the relevbnt frbme.
                       setSourceClbssNbme(cnbme);
                       setSourceMethodNbme(frbme.getMethodNbme());
                       return;
                    }
                }
            }
        }
        // We hbven't found b suitbble frbme, so just punt.  This is
        // OK bs we bre only committed to mbking b "best effort" here.
    }

    privbte boolebn isLoggerImplFrbme(String cnbme) {
        // the log record could be crebted for b plbtform logger
        return (cnbme.equbls("jbvb.util.logging.Logger") ||
                cnbme.stbrtsWith("jbvb.util.logging.LoggingProxyImpl") ||
                cnbme.stbrtsWith("sun.util.logging."));
    }
}
