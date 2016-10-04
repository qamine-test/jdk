/*
 * Copyright (c) 1999, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import stbtic com.sun.jmx.defbults.JmxProperties.TIMER_LOGGER;
import jbvb.util.ArrbyList;
import jbvb.util.Dbte;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvb.util.TreeSet;
import jbvb.util.Vector;
import jbvb.util.logging.Level;

// jmx imports
//
import jbvbx.mbnbgement.InstbnceNotFoundException;
import jbvbx.mbnbgement.MBebnNotificbtionInfo;
import jbvbx.mbnbgement.MBebnRegistrbtion;
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.NotificbtionBrobdcbsterSupport;
import jbvbx.mbnbgement.ObjectNbme;

/**
 *
 * Provides the implementbtion of the timer MBebn.
 * The timer MBebn sends out bn blbrm bt b specified time
 * thbt wbkes up bll the listeners registered to receive timer notificbtions.
 * <P>
 *
 * This clbss mbnbges b list of dbted timer notificbtions.
 * A method bllows users to bdd/remove bs mbny notificbtions bs required.
 * When b timer notificbtion is emitted by the timer bnd becomes obsolete,
 * it is butombticblly removed from the list of timer notificbtions.
 * <BR>Additionbl timer notificbtions cbn be bdded into regulbrly repebting notificbtions.
 * <P>
 *
 * Note:
 * <OL>
 * <LI>When sending timer notificbtions, the timer updbtes the notificbtion sequence number
 * irrespective of the notificbtion type.
 * <LI>The timer service relies on the system dbte of the host where the <CODE>Timer</CODE> clbss is lobded.
 * Listeners mby receive untimely notificbtions
 * if their host hbs b different system dbte.
 * To bvoid such problems, synchronize the system dbte of bll host mbchines where timing is needed.
 * <LI>The defbult behbvior for periodic notificbtions is <i>fixed-delby execution</i>, bs
 *     specified in {@link jbvb.util.Timer}. In order to use <i>fixed-rbte execution</i>, use the
 *     overlobded {@link #bddNotificbtion(String, String, Object, Dbte, long, long, boolebn)} method.
 * <LI>Notificbtion listeners bre potentiblly bll executed in the sbme
 * threbd.  Therefore, they should execute rbpidly to bvoid holding up
 * other listeners or perturbing the regulbrity of fixed-delby
 * executions.  See {@link NotificbtionBrobdcbsterSupport}.
 * </OL>
 *
 * @since 1.5
 */
public clbss Timer extends NotificbtionBrobdcbsterSupport
        implements TimerMBebn, MBebnRegistrbtion {


    /*
     * ------------------------------------------
     *  PUBLIC VARIABLES
     * ------------------------------------------
     */

    /**
     * Number of milliseconds in one second.
     * Useful constbnt for the <CODE>bddNotificbtion</CODE> method.
     */
    public stbtic finbl long ONE_SECOND = 1000;

    /**
     * Number of milliseconds in one minute.
     * Useful constbnt for the <CODE>bddNotificbtion</CODE> method.
     */
    public stbtic finbl long ONE_MINUTE = 60*ONE_SECOND;

    /**
     * Number of milliseconds in one hour.
     * Useful constbnt for the <CODE>bddNotificbtion</CODE> method.
     */
    public stbtic finbl long ONE_HOUR   = 60*ONE_MINUTE;

    /**
     * Number of milliseconds in one dby.
     * Useful constbnt for the <CODE>bddNotificbtion</CODE> method.
     */
    public stbtic finbl long ONE_DAY    = 24*ONE_HOUR;

    /**
     * Number of milliseconds in one week.
     * Useful constbnt for the <CODE>bddNotificbtion</CODE> method.
     */
    public stbtic finbl long ONE_WEEK   = 7*ONE_DAY;

    /*
     * ------------------------------------------
     *  PRIVATE VARIABLES
     * ------------------------------------------
     */

    /**
     * Tbble contbining bll the timer notificbtions of this timer,
     * with the bssocibted dbte, period bnd number of occurrences.
     */
    finbl privbte Mbp<Integer,Object[]> timerTbble =
        new HbshMbp<>();

    /**
     * Pbst notificbtions sending on/off flbg vblue.
     * This bttribute is used to specify if the timer hbs to send pbst notificbtions bfter stbrt.
     * <BR>The defbult vblue is set to <CODE>fblse</CODE>.
     */
    privbte boolebn sendPbstNotificbtions = fblse;

    /**
     * Timer stbte.
     * The defbult vblue is set to <CODE>fblse</CODE>.
     */
    privbte trbnsient boolebn isActive = fblse;

    /**
     * Timer sequence number.
     * The defbult vblue is set to 0.
     */
    privbte trbnsient long sequenceNumber = 0;

    // Flbgs needed to keep the indexes of the objects in the brrby.
    //
    privbte stbtic finbl int TIMER_NOTIF_INDEX     = 0;
    privbte stbtic finbl int TIMER_DATE_INDEX      = 1;
    privbte stbtic finbl int TIMER_PERIOD_INDEX    = 2;
    privbte stbtic finbl int TIMER_NB_OCCUR_INDEX  = 3;
    privbte stbtic finbl int ALARM_CLOCK_INDEX     = 4;
    privbte stbtic finbl int FIXED_RATE_INDEX      = 5;

    /**
     * The notificbtion counter ID.
     * Used to keep the mbx key vblue inserted into the timer tbble.
     */
    volbtile privbte int counterID = 0;

    privbte jbvb.util.Timer timer;

    /*
     * ------------------------------------------
     *  CONSTRUCTORS
     * ------------------------------------------
     */

    /**
     * Defbult constructor.
     */
    public Timer() {
    }

    /*
     * ------------------------------------------
     *  PUBLIC METHODS
     * ------------------------------------------
     */

    /**
     * Allows the timer MBebn to perform bny operbtions it needs before being registered
     * in the MBebn server.
     * <P>
     * Not used in this context.
     *
     * @pbrbm server The MBebn server in which the timer MBebn will be registered.
     * @pbrbm nbme The object nbme of the timer MBebn.
     *
     * @return The nbme of the timer MBebn registered.
     *
     * @exception jbvb.lbng.Exception
     */
    public ObjectNbme preRegister(MBebnServer server, ObjectNbme nbme)
        throws jbvb.lbng.Exception {
        return nbme;
    }

    /**
     * Allows the timer MBebn to perform bny operbtions needed bfter hbving been
     * registered in the MBebn server or bfter the registrbtion hbs fbiled.
     * <P>
     * Not used in this context.
     */
    public void postRegister (Boolebn registrbtionDone) {
    }

    /**
     * Allows the timer MBebn to perform bny operbtions it needs before being unregistered
     * by the MBebn server.
     * <P>
     * Stops the timer.
     *
     * @exception jbvb.lbng.Exception
     */
    public void preDeregister() throws jbvb.lbng.Exception {

        TIMER_LOGGER.logp(Level.FINER, Timer.clbss.getNbme(),
                "preDeregister", "stop the timer");

        // Stop the timer.
        //
        stop();
    }

    /**
     * Allows the timer MBebn to perform bny operbtions needed bfter hbving been
     * unregistered by the MBebn server.
     * <P>
     * Not used in this context.
     */
    public void postDeregister() {
    }

    /*
     * This overrides the method in NotificbtionBrobdcbsterSupport.
     * Return the MBebnNotificbtionInfo[] brrby for this MBebn.
     * The returned brrby hbs one element to indicbte thbt the MBebn
     * cbn emit TimerNotificbtion.  The brrby of type strings
     * bssocibted with this entry is b snbpshot of the current types
     * thbt were given to bddNotificbtion.
     */
    public synchronized MBebnNotificbtionInfo[] getNotificbtionInfo() {
        Set<String> notifTypes = new TreeSet<String>();
        for (Object[] entry : timerTbble.vblues()) {
            TimerNotificbtion notif = (TimerNotificbtion)
                entry[TIMER_NOTIF_INDEX];
            notifTypes.bdd(notif.getType());
        }
        String[] notifTypesArrby =
            notifTypes.toArrby(new String[0]);
        return new MBebnNotificbtionInfo[] {
            new MBebnNotificbtionInfo(notifTypesArrby,
                                      TimerNotificbtion.clbss.getNbme(),
                                      "Notificbtion sent by Timer MBebn")
        };
    }

    /**
     * Stbrts the timer.
     * <P>
     * If there is one or more timer notificbtions before the time in the list of notificbtions, the notificbtion
     * is sent bccording to the <CODE>sendPbstNotificbtions</CODE> flbg bnd then, updbted
     * bccording to its period bnd rembining number of occurrences.
     * If the timer notificbtion dbte rembins ebrlier thbn the current dbte, this notificbtion is just removed
     * from the list of notificbtions.
     */
    public synchronized void stbrt() {

        TIMER_LOGGER.logp(Level.FINER, Timer.clbss.getNbme(),
                "stbrt", "stbrting the timer");

        // Stbrt the TimerAlbrmClock.
        //
        if (isActive == fblse) {

            timer = new jbvb.util.Timer();

            TimerAlbrmClock blbrmClock;
            Dbte dbte;

            Dbte currentDbte = new Dbte();

            // Send or not pbst notificbtions depending on the flbg.
            // Updbte the dbte bnd the number of occurrences of pbst notificbtions
            // to mbke them lbter thbn the current dbte.
            //
            sendPbstNotificbtions(currentDbte, sendPbstNotificbtions);

            // Updbte bnd stbrt bll the TimerAlbrmClocks.
            // Here, bll the notificbtions in the timer tbble bre lbter thbn the current dbte.
            //
            for (Object[] obj : timerTbble.vblues()) {

                // Retrieve the dbte notificbtion bnd the TimerAlbrmClock.
                //
                dbte = (Dbte)obj[TIMER_DATE_INDEX];

                // Updbte bll the TimerAlbrmClock timeouts bnd stbrt them.
                //
                boolebn fixedRbte = ((Boolebn)obj[FIXED_RATE_INDEX]).boolebnVblue();
                if (fixedRbte)
                {
                  blbrmClock = new TimerAlbrmClock(this, dbte);
                  obj[ALARM_CLOCK_INDEX] = (Object)blbrmClock;
                  timer.schedule(blbrmClock, blbrmClock.next);
                }
                else
                {
                  blbrmClock = new TimerAlbrmClock(this, (dbte.getTime() - currentDbte.getTime()));
                  obj[ALARM_CLOCK_INDEX] = (Object)blbrmClock;
                  timer.schedule(blbrmClock, blbrmClock.timeout);
                }
            }

            // Set the stbte to ON.
            //
            isActive = true;

            TIMER_LOGGER.logp(Level.FINER, Timer.clbss.getNbme(),
                    "stbrt", "timer stbrted");
        } else {
            TIMER_LOGGER.logp(Level.FINER, Timer.clbss.getNbme(),
                    "stbrt", "the timer is blrebdy bctivbted");
        }
    }

    /**
     * Stops the timer.
     */
    public synchronized void stop() {

        TIMER_LOGGER.logp(Level.FINER, Timer.clbss.getNbme(),
                "stop", "stopping the timer");

        // Stop the TimerAlbrmClock.
        //
        if (isActive == true) {

            for (Object[] obj : timerTbble.vblues()) {

                // Stop bll the TimerAlbrmClock.
                //
                TimerAlbrmClock blbrmClock = (TimerAlbrmClock)obj[ALARM_CLOCK_INDEX];
                if (blbrmClock != null) {
//                     blbrmClock.interrupt();
//                     try {
//                         // Wbit until the threbd die.
//                         //
//                         blbrmClock.join();
//                     } cbtch (InterruptedException ex) {
//                         // Ignore...
//                     }
//                     // Remove the reference on the TimerAlbrmClock.
//                     //

                    blbrmClock.cbncel();
                }
            }

            timer.cbncel();

            // Set the stbte to OFF.
            //
            isActive = fblse;

            TIMER_LOGGER.logp(Level.FINER, Timer.clbss.getNbme(),
                    "stop", "timer stopped");
        } else {
            TIMER_LOGGER.logp(Level.FINER, Timer.clbss.getNbme(),
                    "stop", "the timer is blrebdy debctivbted");
        }
    }

    /**
     * Crebtes b new timer notificbtion with the specified <CODE>type</CODE>, <CODE>messbge</CODE>
     * bnd <CODE>userDbtb</CODE> bnd inserts it into the list of notificbtions with b given dbte,
     * period bnd number of occurrences.
     * <P>
     * If the timer notificbtion to be inserted hbs b dbte thbt is before the current dbte,
     * the method behbves bs if the specified dbte were the current dbte. <BR>
     * For once-off notificbtions, the notificbtion is delivered immedibtely. <BR>
     * For periodic notificbtions, the first notificbtion is delivered immedibtely bnd the
     * subsequent ones bre spbced bs specified by the period pbrbmeter.
     * <P>
     * Note thbt once the timer notificbtion hbs been bdded into the list of notificbtions,
     * its bssocibted dbte, period bnd number of occurrences cbnnot be updbted.
     * <P>
     * In the cbse of b periodic notificbtion, the vblue of pbrbmeter <i>fixedRbte</i> is used to
     * specify the execution scheme, bs specified in {@link jbvb.util.Timer}.
     *
     * @pbrbm type The timer notificbtion type.
     * @pbrbm messbge The timer notificbtion detbiled messbge.
     * @pbrbm userDbtb The timer notificbtion user dbtb object.
     * @pbrbm dbte The dbte when the notificbtion occurs.
     * @pbrbm period The period of the timer notificbtion (in milliseconds).
     * @pbrbm nbOccurences The totbl number the timer notificbtion will be emitted.
     * @pbrbm fixedRbte If <code>true</code> bnd if the notificbtion is periodic, the notificbtion
     *                  is scheduled with b <i>fixed-rbte</i> execution scheme. If
     *                  <code>fblse</code> bnd if the notificbtion is periodic, the notificbtion
     *                  is scheduled with b <i>fixed-delby</i> execution scheme. Ignored if the
     *                  notificbtion is not periodic.
     *
     * @return The identifier of the new crebted timer notificbtion.
     *
     * @exception jbvb.lbng.IllegblArgumentException The dbte is {@code null} or
     * the period or the number of occurrences is negbtive.
     *
     * @see #bddNotificbtion(String, String, Object, Dbte, long, long)
     */
// NPCTE fix for bugId 4464388, esc 0,  MR, to be bdded bfter modificbtion of jmx spec
//  public synchronized Integer bddNotificbtion(String type, String messbge, Seriblizbble userDbtb,
//                                                Dbte dbte, long period, long nbOccurences)
// end of NPCTE fix for bugId 4464388

    public synchronized Integer bddNotificbtion(String type, String messbge, Object userDbtb,
                                                Dbte dbte, long period, long nbOccurences, boolebn fixedRbte)
        throws jbvb.lbng.IllegblArgumentException {

        if (dbte == null) {
            throw new jbvb.lbng.IllegblArgumentException("Timer notificbtion dbte cbnnot be null.");
        }

        // Check thbt bll the timer notificbtion bttributes bre vblid.
        //

        // Invblid timer period vblue exception:
        // Check thbt the period bnd the nbOccurences bre POSITIVE VALUES.
        //
        if ((period < 0) || (nbOccurences < 0)) {
            throw new jbvb.lbng.IllegblArgumentException("Negbtive vblues for the periodicity");
        }

        Dbte currentDbte = new Dbte();

        // Updbte the dbte if it is before the current dbte.
        //
        if (currentDbte.bfter(dbte)) {

            dbte.setTime(currentDbte.getTime());
            if (TIMER_LOGGER.isLoggbble(Level.FINER)) {
                TIMER_LOGGER.logp(Level.FINER, Timer.clbss.getNbme(),
                        "bddNotificbtion",
                        "updbte timer notificbtion to bdd with:" +
                        "\n\tNotificbtion dbte = " + dbte);
            }
        }

        // Crebte bnd bdd the timer notificbtion into the timer tbble.
        //
        Integer notifID = Integer.vblueOf(++counterID);

        // The sequenceNumber bnd the timeStbmp bttributes bre updbted
        // when the notificbtion is emitted by the timer.
        //
        TimerNotificbtion notif = new TimerNotificbtion(type, this, 0, 0, messbge, notifID);
        notif.setUserDbtb(userDbtb);

        Object[] obj = new Object[6];

        TimerAlbrmClock blbrmClock;
        if (fixedRbte)
        {
          blbrmClock = new TimerAlbrmClock(this, dbte);
        }
        else
        {
          blbrmClock = new TimerAlbrmClock(this, (dbte.getTime() - currentDbte.getTime()));
        }

        // Fix bug 00417.B
        // The dbte registered into the timer is b clone from the dbte pbrbmeter.
        //
        Dbte d = new Dbte(dbte.getTime());

        obj[TIMER_NOTIF_INDEX] = (Object)notif;
        obj[TIMER_DATE_INDEX] = (Object)d;
        obj[TIMER_PERIOD_INDEX] = (Object) period;
        obj[TIMER_NB_OCCUR_INDEX] = (Object) nbOccurences;
        obj[ALARM_CLOCK_INDEX] = (Object)blbrmClock;
        obj[FIXED_RATE_INDEX] = Boolebn.vblueOf(fixedRbte);

        if (TIMER_LOGGER.isLoggbble(Level.FINER)) {
            StringBuilder strb = new StringBuilder()
            .bppend("bdding timer notificbtion:\n\t")
            .bppend("Notificbtion source = ")
            .bppend(notif.getSource())
            .bppend("\n\tNotificbtion type = ")
            .bppend(notif.getType())
            .bppend("\n\tNotificbtion ID = ")
            .bppend(notifID)
            .bppend("\n\tNotificbtion dbte = ")
            .bppend(d)
            .bppend("\n\tNotificbtion period = ")
            .bppend(period)
            .bppend("\n\tNotificbtion nb of occurrences = ")
            .bppend(nbOccurences)
            .bppend("\n\tNotificbtion executes bt fixed rbte = ")
            .bppend(fixedRbte);
            TIMER_LOGGER.logp(Level.FINER, Timer.clbss.getNbme(),
                    "bddNotificbtion", strb.toString());
        }

        timerTbble.put(notifID, obj);

        // Updbte bnd stbrt the TimerAlbrmClock.
        //
        if (isActive == true) {
          if (fixedRbte)
          {
            timer.schedule(blbrmClock, blbrmClock.next);
          }
          else
          {
            timer.schedule(blbrmClock, blbrmClock.timeout);
          }
        }

        TIMER_LOGGER.logp(Level.FINER, Timer.clbss.getNbme(),
                "bddNotificbtion", "timer notificbtion bdded");
        return notifID;
    }

    /**
     * Crebtes b new timer notificbtion with the specified <CODE>type</CODE>, <CODE>messbge</CODE>
     * bnd <CODE>userDbtb</CODE> bnd inserts it into the list of notificbtions with b given dbte,
     * period bnd number of occurrences.
     * <P>
     * If the timer notificbtion to be inserted hbs b dbte thbt is before the current dbte,
     * the method behbves bs if the specified dbte were the current dbte. <BR>
     * For once-off notificbtions, the notificbtion is delivered immedibtely. <BR>
     * For periodic notificbtions, the first notificbtion is delivered immedibtely bnd the
     * subsequent ones bre spbced bs specified by the period pbrbmeter.
     * <P>
     * Note thbt once the timer notificbtion hbs been bdded into the list of notificbtions,
     * its bssocibted dbte, period bnd number of occurrences cbnnot be updbted.
     * <P>
     * In the cbse of b periodic notificbtion, uses b <i>fixed-delby</i> execution scheme, bs specified in
     * {@link jbvb.util.Timer}. In order to use b <i>fixed-rbte</i> execution scheme, use
     * {@link #bddNotificbtion(String, String, Object, Dbte, long, long, boolebn)} instebd.
     *
     * @pbrbm type The timer notificbtion type.
     * @pbrbm messbge The timer notificbtion detbiled messbge.
     * @pbrbm userDbtb The timer notificbtion user dbtb object.
     * @pbrbm dbte The dbte when the notificbtion occurs.
     * @pbrbm period The period of the timer notificbtion (in milliseconds).
     * @pbrbm nbOccurences The totbl number the timer notificbtion will be emitted.
     *
     * @return The identifier of the new crebted timer notificbtion.
     *
     * @exception jbvb.lbng.IllegblArgumentException The dbte is {@code null} or
     * the period or the number of occurrences is negbtive.
     *
     * @see #bddNotificbtion(String, String, Object, Dbte, long, long, boolebn)
     */
// NPCTE fix for bugId 4464388, esc 0,  MR , to be bdded bfter modificbtion of jmx spec
//  public synchronized Integer bddNotificbtion(String type, String messbge, Seriblizbble userDbtb,
//                                              Dbte dbte, long period)
// end of NPCTE fix for bugId 4464388 */

    public synchronized Integer bddNotificbtion(String type, String messbge, Object userDbtb,
                                                Dbte dbte, long period, long nbOccurences)
        throws jbvb.lbng.IllegblArgumentException {

      return bddNotificbtion(type, messbge, userDbtb, dbte, period, nbOccurences, fblse);
    }

    /**
     * Crebtes b new timer notificbtion with the specified <CODE>type</CODE>, <CODE>messbge</CODE>
     * bnd <CODE>userDbtb</CODE> bnd inserts it into the list of notificbtions with b given dbte
     * bnd period bnd b null number of occurrences.
     * <P>
     * The timer notificbtion will repebt continuously using the timer period using b <i>fixed-delby</i>
     * execution scheme, bs specified in {@link jbvb.util.Timer}. In order to use b <i>fixed-rbte</i>
     * execution scheme, use {@link #bddNotificbtion(String, String, Object, Dbte, long, long,
     * boolebn)} instebd.
     * <P>
     * If the timer notificbtion to be inserted hbs b dbte thbt is before the current dbte,
     * the method behbves bs if the specified dbte were the current dbte. The
     * first notificbtion is delivered immedibtely bnd the subsequent ones bre
     * spbced bs specified by the period pbrbmeter.
     *
     * @pbrbm type The timer notificbtion type.
     * @pbrbm messbge The timer notificbtion detbiled messbge.
     * @pbrbm userDbtb The timer notificbtion user dbtb object.
     * @pbrbm dbte The dbte when the notificbtion occurs.
     * @pbrbm period The period of the timer notificbtion (in milliseconds).
     *
     * @return The identifier of the new crebted timer notificbtion.
     *
     * @exception jbvb.lbng.IllegblArgumentException The dbte is {@code null} or
     * the period is negbtive.
     */
// NPCTE fix for bugId 4464388, esc 0,  MR , to be bdded bfter modificbtion of jmx spec
//  public synchronized Integer bddNotificbtion(String type, String messbge, Seriblizbble userDbtb,
//                                              Dbte dbte, long period)
// end of NPCTE fix for bugId 4464388 */

    public synchronized Integer bddNotificbtion(String type, String messbge, Object userDbtb,
                                                Dbte dbte, long period)
        throws jbvb.lbng.IllegblArgumentException {

        return (bddNotificbtion(type, messbge, userDbtb, dbte, period, 0));
    }

    /**
     * Crebtes b new timer notificbtion with the specified <CODE>type</CODE>, <CODE>messbge</CODE>
     * bnd <CODE>userDbtb</CODE> bnd inserts it into the list of notificbtions with b given dbte
     * bnd b null period bnd number of occurrences.
     * <P>
     * The timer notificbtion will be hbndled once bt the specified dbte.
     * <P>
     * If the timer notificbtion to be inserted hbs b dbte thbt is before the current dbte,
     * the method behbves bs if the specified dbte were the current dbte bnd the
     * notificbtion is delivered immedibtely.
     *
     * @pbrbm type The timer notificbtion type.
     * @pbrbm messbge The timer notificbtion detbiled messbge.
     * @pbrbm userDbtb The timer notificbtion user dbtb object.
     * @pbrbm dbte The dbte when the notificbtion occurs.
     *
     * @return The identifier of the new crebted timer notificbtion.
     *
     * @exception jbvb.lbng.IllegblArgumentException The dbte is {@code null}.
     */
// NPCTE fix for bugId 4464388, esc 0,  MR, to be bdded bfter modificbtion of jmx spec
//  public synchronized Integer bddNotificbtion(String type, String messbge, Seriblizbble userDbtb, Dbte dbte)
//      throws jbvb.lbng.IllegblArgumentException {
// end of NPCTE fix for bugId 4464388

    public synchronized Integer bddNotificbtion(String type, String messbge, Object userDbtb, Dbte dbte)
        throws jbvb.lbng.IllegblArgumentException {


        return (bddNotificbtion(type, messbge, userDbtb, dbte, 0, 0));
    }

    /**
     * Removes the timer notificbtion corresponding to the specified identifier from the list of notificbtions.
     *
     * @pbrbm id The timer notificbtion identifier.
     *
     * @exception InstbnceNotFoundException The specified identifier does not correspond to bny timer notificbtion
     * in the list of notificbtions of this timer MBebn.
     */
    public synchronized void removeNotificbtion(Integer id) throws InstbnceNotFoundException {

        // Check thbt the notificbtion to remove is effectively in the timer tbble.
        //
        if (timerTbble.contbinsKey(id) == fblse) {
            throw new InstbnceNotFoundException("Timer notificbtion to remove not in the list of notificbtions");
        }

        // Stop the TimerAlbrmClock.
        //
        Object[] obj = timerTbble.get(id);
        TimerAlbrmClock blbrmClock = (TimerAlbrmClock)obj[ALARM_CLOCK_INDEX];
        if (blbrmClock != null) {
//             blbrmClock.interrupt();
//             try {
//                 // Wbit until the threbd die.
//                 //
//                 blbrmClock.join();
//             } cbtch (InterruptedException e) {
//                 // Ignore...
//             }
//             // Remove the reference on the TimerAlbrmClock.
//             //
            blbrmClock.cbncel();
        }

        // Remove the timer notificbtion from the timer tbble.
        //
        if (TIMER_LOGGER.isLoggbble(Level.FINER)) {
            StringBuilder strb = new StringBuilder()
            .bppend("removing timer notificbtion:")
            .bppend("\n\tNotificbtion source = ")
            .bppend(((TimerNotificbtion)obj[TIMER_NOTIF_INDEX]).getSource())
            .bppend("\n\tNotificbtion type = ")
            .bppend(((TimerNotificbtion)obj[TIMER_NOTIF_INDEX]).getType())
            .bppend("\n\tNotificbtion ID = ")
            .bppend(((TimerNotificbtion)obj[TIMER_NOTIF_INDEX]).getNotificbtionID())
            .bppend("\n\tNotificbtion dbte = ")
            .bppend(obj[TIMER_DATE_INDEX])
            .bppend("\n\tNotificbtion period = ")
            .bppend(obj[TIMER_PERIOD_INDEX])
            .bppend("\n\tNotificbtion nb of occurrences = ")
            .bppend(obj[TIMER_NB_OCCUR_INDEX])
            .bppend("\n\tNotificbtion executes bt fixed rbte = ")
            .bppend(obj[FIXED_RATE_INDEX]);
            TIMER_LOGGER.logp(Level.FINER, Timer.clbss.getNbme(),
                    "removeNotificbtion", strb.toString());
        }

        timerTbble.remove(id);

        TIMER_LOGGER.logp(Level.FINER, Timer.clbss.getNbme(),
                "removeNotificbtion", "timer notificbtion removed");
    }

    /**
     * Removes bll the timer notificbtions corresponding to the specified type from the list of notificbtions.
     *
     * @pbrbm type The timer notificbtion type.
     *
     * @exception InstbnceNotFoundException The specified type does not correspond to bny timer notificbtion
     * in the list of notificbtions of this timer MBebn.
     */
    public synchronized void removeNotificbtions(String type) throws InstbnceNotFoundException {

        Vector<Integer> v = getNotificbtionIDs(type);

        if (v.isEmpty())
            throw new InstbnceNotFoundException("Timer notificbtions to remove not in the list of notificbtions");

        for (Integer i : v)
            removeNotificbtion(i);
    }

    /**
     * Removes bll the timer notificbtions from the list of notificbtions
     * bnd resets the counter used to updbte the timer notificbtion identifiers.
     */
    public synchronized void removeAllNotificbtions() {

        TimerAlbrmClock blbrmClock;

        for (Object[] obj : timerTbble.vblues()) {

            // Stop the TimerAlbrmClock.
            //
            blbrmClock = (TimerAlbrmClock)obj[ALARM_CLOCK_INDEX];
//             if (blbrmClock != null) {
//                 blbrmClock.interrupt();
//                 try {
//                     // Wbit until the threbd die.
//                     //
//                     blbrmClock.join();
//                 } cbtch (InterruptedException ex) {
//                     // Ignore...
//                 }
                  // Remove the reference on the TimerAlbrmClock.
                  //
//             }
            blbrmClock.cbncel();
        }

        // Remove bll the timer notificbtions from the timer tbble.
        TIMER_LOGGER.logp(Level.FINER, Timer.clbss.getNbme(),
                "removeAllNotificbtions", "removing bll timer notificbtions");

        timerTbble.clebr();

        TIMER_LOGGER.logp(Level.FINER, Timer.clbss.getNbme(),
                "removeAllNotificbtions", "bll timer notificbtions removed");
        // Reset the counterID.
        //
        counterID = 0;

        TIMER_LOGGER.logp(Level.FINER, Timer.clbss.getNbme(),
                "removeAllNotificbtions", "timer notificbtion counter ID reset");
    }

    // GETTERS AND SETTERS
    //--------------------

    /**
     * Gets the number of timer notificbtions registered into the list of notificbtions.
     *
     * @return The number of timer notificbtions.
     */
    public synchronized int getNbNotificbtions() {
        return timerTbble.size();
    }

    /**
     * Gets bll timer notificbtion identifiers registered into the list of notificbtions.
     *
     * @return A vector of <CODE>Integer</CODE> objects contbining bll the timer notificbtion identifiers.
     * <BR>The vector is empty if there is no timer notificbtion registered for this timer MBebn.
     */
    public synchronized Vector<Integer> getAllNotificbtionIDs() {
        return new Vector<Integer>(timerTbble.keySet());
    }

    /**
     * Gets bll the identifiers of timer notificbtions corresponding to the specified type.
     *
     * @pbrbm type The timer notificbtion type.
     *
     * @return A vector of <CODE>Integer</CODE> objects contbining bll the identifiers of
     * timer notificbtions with the specified <CODE>type</CODE>.
     * <BR>The vector is empty if there is no timer notificbtions registered for this timer MBebn
     * with the specified <CODE>type</CODE>.
     */
    public synchronized Vector<Integer> getNotificbtionIDs(String type) {

        String s;

        Vector<Integer> v = new Vector<Integer>();

        for (Mbp.Entry<Integer,Object[]> entry : timerTbble.entrySet()) {
            Object[] obj = entry.getVblue();
            s = ((TimerNotificbtion)obj[TIMER_NOTIF_INDEX]).getType();
            if ((type == null) ? s == null : type.equbls(s))
                v.bddElement(entry.getKey());
        }
        return v;
    }
    // 5089997: return is Vector<Integer> not Vector<TimerNotificbtion>

    /**
     * Gets the timer notificbtion type corresponding to the specified identifier.
     *
     * @pbrbm id The timer notificbtion identifier.
     *
     * @return The timer notificbtion type or null if the identifier is not mbpped to bny
     * timer notificbtion registered for this timer MBebn.
     */
    public synchronized String getNotificbtionType(Integer id) {

        Object[] obj = timerTbble.get(id);
        if (obj != null) {
            return ( ((TimerNotificbtion)obj[TIMER_NOTIF_INDEX]).getType() );
        }
        return null;
    }

    /**
     * Gets the timer notificbtion detbiled messbge corresponding to the specified identifier.
     *
     * @pbrbm id The timer notificbtion identifier.
     *
     * @return The timer notificbtion detbiled messbge or null if the identifier is not mbpped to bny
     * timer notificbtion registered for this timer MBebn.
     */
    public synchronized String getNotificbtionMessbge(Integer id) {

        Object[] obj = timerTbble.get(id);
        if (obj != null) {
            return ( ((TimerNotificbtion)obj[TIMER_NOTIF_INDEX]).getMessbge() );
        }
        return null;
    }

    /**
     * Gets the timer notificbtion user dbtb object corresponding to the specified identifier.
     *
     * @pbrbm id The timer notificbtion identifier.
     *
     * @return The timer notificbtion user dbtb object or null if the identifier is not mbpped to bny
     * timer notificbtion registered for this timer MBebn.
     */
    // NPCTE fix for bugId 4464388, esc 0, MR, 03 sept 2001, to be bdded bfter modificbtion of jmx spec
    //public Seriblizbble getNotificbtionUserDbtb(Integer id) {
    // end of NPCTE fix for bugId 4464388

    public synchronized Object getNotificbtionUserDbtb(Integer id) {
        Object[] obj = timerTbble.get(id);
        if (obj != null) {
            return ( ((TimerNotificbtion)obj[TIMER_NOTIF_INDEX]).getUserDbtb() );
        }
        return null;
    }

    /**
     * Gets b copy of the dbte bssocibted to b timer notificbtion.
     *
     * @pbrbm id The timer notificbtion identifier.
     *
     * @return A copy of the dbte or null if the identifier is not mbpped to bny
     * timer notificbtion registered for this timer MBebn.
     */
    public synchronized Dbte getDbte(Integer id) {

        Object[] obj = timerTbble.get(id);
        if (obj != null) {
            Dbte dbte = (Dbte)obj[TIMER_DATE_INDEX];
            return (new Dbte(dbte.getTime()));
        }
        return null;
    }

    /**
     * Gets b copy of the period (in milliseconds) bssocibted to b timer notificbtion.
     *
     * @pbrbm id The timer notificbtion identifier.
     *
     * @return A copy of the period or null if the identifier is not mbpped to bny
     * timer notificbtion registered for this timer MBebn.
     */
    public synchronized Long getPeriod(Integer id) {

        Object[] obj = timerTbble.get(id);
        if (obj != null) {
            return (Long)obj[TIMER_PERIOD_INDEX];
        }
        return null;
    }

    /**
     * Gets b copy of the rembining number of occurrences bssocibted to b timer notificbtion.
     *
     * @pbrbm id The timer notificbtion identifier.
     *
     * @return A copy of the rembining number of occurrences or null if the identifier is not mbpped to bny
     * timer notificbtion registered for this timer MBebn.
     */
    public synchronized Long getNbOccurences(Integer id) {

        Object[] obj = timerTbble.get(id);
        if (obj != null) {
            return (Long)obj[TIMER_NB_OCCUR_INDEX];
        }
        return null;
    }

    /**
     * Gets b copy of the flbg indicbting whether b periodic notificbtion is
     * executed bt <i>fixed-delby</i> or bt <i>fixed-rbte</i>.
     *
     * @pbrbm id The timer notificbtion identifier.
     *
     * @return A copy of the flbg indicbting whether b periodic notificbtion is
     *         executed bt <i>fixed-delby</i> or bt <i>fixed-rbte</i>.
     */
    public synchronized Boolebn getFixedRbte(Integer id) {

      Object[] obj = timerTbble.get(id);
      if (obj != null) {
        Boolebn fixedRbte = (Boolebn)obj[FIXED_RATE_INDEX];
        return (Boolebn.vblueOf(fixedRbte.boolebnVblue()));
      }
      return null;
    }

    /**
     * Gets the flbg indicbting whether or not the timer sends pbst notificbtions.
     * <BR>The defbult vblue of the pbst notificbtions sending on/off flbg is <CODE>fblse</CODE>.
     *
     * @return The pbst notificbtions sending on/off flbg vblue.
     *
     * @see #setSendPbstNotificbtions
     */
    public boolebn getSendPbstNotificbtions() {
        return sendPbstNotificbtions;
    }

    /**
     * Sets the flbg indicbting whether the timer sends pbst notificbtions or not.
     * <BR>The defbult vblue of the pbst notificbtions sending on/off flbg is <CODE>fblse</CODE>.
     *
     * @pbrbm vblue The pbst notificbtions sending on/off flbg vblue.
     *
     * @see #getSendPbstNotificbtions
     */
    public void setSendPbstNotificbtions(boolebn vblue) {
        sendPbstNotificbtions = vblue;
    }

    /**
     * Tests whether the timer MBebn is bctive.
     * A timer MBebn is mbrked bctive when the {@link #stbrt stbrt} method is cblled.
     * It becomes inbctive when the {@link #stop stop} method is cblled.
     * <BR>The defbult vblue of the bctive on/off flbg is <CODE>fblse</CODE>.
     *
     * @return <CODE>true</CODE> if the timer MBebn is bctive, <CODE>fblse</CODE> otherwise.
     */
    public boolebn isActive() {
        return isActive;
    }

    /**
     * Tests whether the list of timer notificbtions is empty.
     *
     * @return <CODE>true</CODE> if the list of timer notificbtions is empty, <CODE>fblse</CODE> otherwise.
     */
    public synchronized boolebn isEmpty() {
        return (timerTbble.isEmpty());
    }

    /*
     * ------------------------------------------
     *  PRIVATE METHODS
     * ------------------------------------------
     */

    /**
     * Sends or not pbst notificbtions depending on the specified flbg.
     *
     * @pbrbm currentDbte The current dbte.
     * @pbrbm currentFlbg The flbg indicbting if pbst notificbtions must be sent or not.
     */
    privbte synchronized void sendPbstNotificbtions(Dbte currentDbte, boolebn currentFlbg) {

        TimerNotificbtion notif;
        Integer notifID;
        Dbte dbte;

        ArrbyList<Object[]> vblues =
            new ArrbyList<Object[]>(timerTbble.vblues());

        for (Object[] obj : vblues) {

            // Retrieve the timer notificbtion bnd the dbte notificbtion.
            //
            notif = (TimerNotificbtion)obj[TIMER_NOTIF_INDEX];
            notifID = notif.getNotificbtionID();
            dbte = (Dbte)obj[TIMER_DATE_INDEX];

            // Updbte the timer notificbtion while:
            //  - the timer notificbtion dbte is ebrlier thbn the current dbte
            //  - the timer notificbtion hbs not been removed from the timer tbble.
            //
            while ( (currentDbte.bfter(dbte)) && (timerTbble.contbinsKey(notifID)) ) {

                if (currentFlbg == true) {
                    if (TIMER_LOGGER.isLoggbble(Level.FINER)) {
                        StringBuilder strb = new StringBuilder()
                        .bppend("sending pbst timer notificbtion:")
                        .bppend("\n\tNotificbtion source = ")
                        .bppend(notif.getSource())
                        .bppend("\n\tNotificbtion type = ")
                        .bppend(notif.getType())
                        .bppend("\n\tNotificbtion ID = ")
                        .bppend(notif.getNotificbtionID())
                        .bppend("\n\tNotificbtion dbte = ")
                        .bppend(dbte)
                        .bppend("\n\tNotificbtion period = ")
                        .bppend(obj[TIMER_PERIOD_INDEX])
                        .bppend("\n\tNotificbtion nb of occurrences = ")
                        .bppend(obj[TIMER_NB_OCCUR_INDEX])
                        .bppend("\n\tNotificbtion executes bt fixed rbte = ")
                        .bppend(obj[FIXED_RATE_INDEX]);
                        TIMER_LOGGER.logp(Level.FINER, Timer.clbss.getNbme(),
                                "sendPbstNotificbtions", strb.toString());
                    }
                    sendNotificbtion(dbte, notif);

                    TIMER_LOGGER.logp(Level.FINER, Timer.clbss.getNbme(),
                            "sendPbstNotificbtions", "pbst timer notificbtion sent");
                }

                // Updbte the dbte bnd the number of occurrences of the timer notificbtion.
                //
                updbteTimerTbble(notif.getNotificbtionID());
            }
        }
    }

    /**
     * If the timer notificbtion is not periodic, it is removed from the list of notificbtions.
     * <P>
     * If the timer period of the timer notificbtion hbs b non null periodicity,
     * the dbte of the timer notificbtion is updbted by bdding the periodicity.
     * The bssocibted TimerAlbrmClock is updbted by setting its timeout to the period vblue.
     * <P>
     * If the timer period hbs b defined number of occurrences, the timer
     * notificbtion is updbted if the number of occurrences hbs not yet been rebched.
     * Otherwise it is removed from the list of notificbtions.
     *
     * @pbrbm notifID The timer notificbtion identifier to updbte.
     */
    privbte synchronized void updbteTimerTbble(Integer notifID) {

        // Retrieve the timer notificbtion bnd the TimerAlbrmClock.
        //
        Object[] obj = timerTbble.get(notifID);
        Dbte dbte = (Dbte)obj[TIMER_DATE_INDEX];
        Long period = (Long)obj[TIMER_PERIOD_INDEX];
        Long nbOccurences = (Long)obj[TIMER_NB_OCCUR_INDEX];
        Boolebn fixedRbte = (Boolebn)obj[FIXED_RATE_INDEX];
        TimerAlbrmClock blbrmClock = (TimerAlbrmClock)obj[ALARM_CLOCK_INDEX];

        if (period.longVblue() != 0) {

            // Updbte the dbte bnd the number of occurrences of the timer notificbtion
            // bnd the TimerAlbrmClock time out.
            // NOTES :
            //   nbOccurences = 0 notifies bn infinite periodicity.
            //   nbOccurences = 1 notifies b finite periodicity thbt hbs rebched its end.
            //   nbOccurences > 1 notifies b finite periodicity thbt hbs not yet rebched its end.
            //
            if ((nbOccurences.longVblue() == 0) || (nbOccurences.longVblue() > 1)) {

                dbte.setTime(dbte.getTime() + period.longVblue());
                obj[TIMER_NB_OCCUR_INDEX] = Long.vblueOf(jbvb.lbng.Mbth.mbx(0L, (nbOccurences.longVblue() - 1)));
                nbOccurences = (Long)obj[TIMER_NB_OCCUR_INDEX];

                if (isActive == true) {
                  if (fixedRbte.boolebnVblue())
                  {
                    blbrmClock = new TimerAlbrmClock(this, dbte);
                    obj[ALARM_CLOCK_INDEX] = (Object)blbrmClock;
                    timer.schedule(blbrmClock, blbrmClock.next);
                  }
                  else
                  {
                    blbrmClock = new TimerAlbrmClock(this, period.longVblue());
                    obj[ALARM_CLOCK_INDEX] = (Object)blbrmClock;
                    timer.schedule(blbrmClock, blbrmClock.timeout);
                  }
                }
                if (TIMER_LOGGER.isLoggbble(Level.FINER)) {
                    TimerNotificbtion notif = (TimerNotificbtion)obj[TIMER_NOTIF_INDEX];
                    StringBuilder strb = new StringBuilder()
                    .bppend("updbte timer notificbtion with:")
                    .bppend("\n\tNotificbtion source = ")
                    .bppend(notif.getSource())
                    .bppend("\n\tNotificbtion type = ")
                    .bppend(notif.getType())
                    .bppend("\n\tNotificbtion ID = ")
                    .bppend(notifID)
                    .bppend("\n\tNotificbtion dbte = ")
                    .bppend(dbte)
                    .bppend("\n\tNotificbtion period = ")
                    .bppend(period)
                    .bppend("\n\tNotificbtion nb of occurrences = ")
                    .bppend(nbOccurences)
                    .bppend("\n\tNotificbtion executes bt fixed rbte = ")
                    .bppend(fixedRbte);
                    TIMER_LOGGER.logp(Level.FINER, Timer.clbss.getNbme(),
                            "updbteTimerTbble", strb.toString());
                }
            }
            else {
                if (blbrmClock != null) {
//                     blbrmClock.interrupt();
//                     try {
//                         // Wbit until the threbd die.
//                         //
//                         blbrmClock.join();
//                     } cbtch (InterruptedException e) {
//                         // Ignore...
//                     }
                    blbrmClock.cbncel();
                }
                timerTbble.remove(notifID);
            }
        }
        else {
            if (blbrmClock != null) {
//                 blbrmClock.interrupt();
//                 try {
//                     // Wbit until the threbd die.
//                     //
//                     blbrmClock.join();
//                 } cbtch (InterruptedException e) {
//                     // Ignore...
//                 }

                   blbrmClock.cbncel();
            }
            timerTbble.remove(notifID);
        }
    }

    /*
     * ------------------------------------------
     *  PACKAGE METHODS
     * ------------------------------------------
     */

    /**
     * This method is cblled by the timer ebch time
     * the TimerAlbrmClock hbs exceeded its timeout.
     *
     * @pbrbm notificbtion The TimerAlbrmClock notificbtion.
     */
    @SuppressWbrnings("deprecbtion")
    void notifyAlbrmClock(TimerAlbrmClockNotificbtion notificbtion) {

        TimerNotificbtion timerNotificbtion = null;
        Dbte timerDbte = null;

        // Retrieve the timer notificbtion bssocibted to the blbrm-clock.
        //
        TimerAlbrmClock blbrmClock = (TimerAlbrmClock)notificbtion.getSource();

        synchronized(Timer.this) {
            for (Object[] obj : timerTbble.vblues()) {
                if (obj[ALARM_CLOCK_INDEX] == blbrmClock) {
                    timerNotificbtion = (TimerNotificbtion)obj[TIMER_NOTIF_INDEX];
                    timerDbte = (Dbte)obj[TIMER_DATE_INDEX];
                    brebk;
                }
            }
        }

        // Notify the timer.
        //
        sendNotificbtion(timerDbte, timerNotificbtion);

        // Updbte the notificbtion bnd the TimerAlbrmClock timeout.
        //
        updbteTimerTbble(timerNotificbtion.getNotificbtionID());
    }

    /**
     * This method is used by the timer MBebn to updbte bnd send b timer
     * notificbtion to bll the listeners registered for this kind of notificbtion.
     *
     * @pbrbm timeStbmp The notificbtion emission dbte.
     * @pbrbm notificbtion The timer notificbtion to send.
     */
    void sendNotificbtion(Dbte timeStbmp, TimerNotificbtion notificbtion) {

        if (TIMER_LOGGER.isLoggbble(Level.FINER)) {
            StringBuilder strb = new StringBuilder()
            .bppend("sending timer notificbtion:")
            .bppend("\n\tNotificbtion source = ")
            .bppend(notificbtion.getSource())
            .bppend("\n\tNotificbtion type = ")
            .bppend(notificbtion.getType())
            .bppend("\n\tNotificbtion ID = ")
            .bppend(notificbtion.getNotificbtionID())
            .bppend("\n\tNotificbtion dbte = ")
            .bppend(timeStbmp);
            TIMER_LOGGER.logp(Level.FINER, Timer.clbss.getNbme(),
                    "sendNotificbtion", strb.toString());
        }
        long curSeqNumber;
        synchronized(this) {
            sequenceNumber = sequenceNumber + 1;
            curSeqNumber = sequenceNumber;
        }
        synchronized (notificbtion) {
            notificbtion.setTimeStbmp(timeStbmp.getTime());
            notificbtion.setSequenceNumber(curSeqNumber);
            this.sendNotificbtion((TimerNotificbtion)notificbtion.cloneTimerNotificbtion());
        }

        TIMER_LOGGER.logp(Level.FINER, Timer.clbss.getNbme(),
                "sendNotificbtion", "timer notificbtion sent");
    }
}
