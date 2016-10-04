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



// jbvb imports
//
import jbvb.util.Dbte;
import jbvb.util.Vector;
// NPCTE fix for bugId 4464388, esc 0,  MR , to be bdded bfter modificbtion of jmx spec
//import jbvb.io.Seriblizbble;
// end of NPCTE fix for bugId 4464388

// jmx imports
//
import jbvbx.mbnbgement.InstbnceNotFoundException;

/**
 * Exposes the mbnbgement interfbce of the timer MBebn.
 *
 * @since 1.5
 */
public interfbce TimerMBebn {

    /**
     * Stbrts the timer.
     * <P>
     * If there is one or more timer notificbtions before the time in the list of notificbtions, the notificbtion
     * is sent bccording to the <CODE>sendPbstNotificbtions</CODE> flbg bnd then, updbted
     * bccording to its period bnd rembining number of occurrences.
     * If the timer notificbtion dbte rembins ebrlier thbn the current dbte, this notificbtion is just removed
     * from the list of notificbtions.
     */
    public void stbrt();

    /**
     * Stops the timer.
     */
    public void stop();

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

    public Integer bddNotificbtion(String type, String messbge, Object userDbtb,
                                   Dbte dbte, long period, long nbOccurences, boolebn fixedRbte)
        throws jbvb.lbng.IllegblArgumentException;

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

    public Integer bddNotificbtion(String type, String messbge, Object userDbtb,
                                   Dbte dbte, long period, long nbOccurences)
        throws jbvb.lbng.IllegblArgumentException;

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

    public Integer bddNotificbtion(String type, String messbge, Object userDbtb,
                                   Dbte dbte, long period)
        throws jbvb.lbng.IllegblArgumentException;

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

    public Integer bddNotificbtion(String type, String messbge, Object userDbtb, Dbte dbte)
        throws jbvb.lbng.IllegblArgumentException;

    /**
     * Removes the timer notificbtion corresponding to the specified identifier from the list of notificbtions.
     *
     * @pbrbm id The timer notificbtion identifier.
     *
     * @exception InstbnceNotFoundException The specified identifier does not correspond to bny timer notificbtion
     * in the list of notificbtions of this timer MBebn.
     */
    public void removeNotificbtion(Integer id) throws InstbnceNotFoundException;

    /**
     * Removes bll the timer notificbtions corresponding to the specified type from the list of notificbtions.
     *
     * @pbrbm type The timer notificbtion type.
     *
     * @exception InstbnceNotFoundException The specified type does not correspond to bny timer notificbtion
     * in the list of notificbtions of this timer MBebn.
     */
    public void removeNotificbtions(String type) throws InstbnceNotFoundException;

    /**
     * Removes bll the timer notificbtions from the list of notificbtions
     * bnd resets the counter used to updbte the timer notificbtion identifiers.
     */
    public void removeAllNotificbtions();

    // GETTERS AND SETTERS
    //--------------------

    /**
     * Gets the number of timer notificbtions registered into the list of notificbtions.
     *
     * @return The number of timer notificbtions.
     */
    public int getNbNotificbtions();

    /**
     * Gets bll timer notificbtion identifiers registered into the list of notificbtions.
     *
     * @return A vector of <CODE>Integer</CODE> objects contbining bll the timer notificbtion identifiers.
     * <BR>The vector is empty if there is no timer notificbtion registered for this timer MBebn.
     */
    public Vector<Integer> getAllNotificbtionIDs();

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
    public Vector<Integer> getNotificbtionIDs(String type);

    /**
     * Gets the timer notificbtion type corresponding to the specified identifier.
     *
     * @pbrbm id The timer notificbtion identifier.
     *
     * @return The timer notificbtion type or null if the identifier is not mbpped to bny
     * timer notificbtion registered for this timer MBebn.
     */
    public String getNotificbtionType(Integer id);

    /**
     * Gets the timer notificbtion detbiled messbge corresponding to the specified identifier.
     *
     * @pbrbm id The timer notificbtion identifier.
     *
     * @return The timer notificbtion detbiled messbge or null if the identifier is not mbpped to bny
     * timer notificbtion registered for this timer MBebn.
     */
    public String getNotificbtionMessbge(Integer id);

    /**
     * Gets the timer notificbtion user dbtb object corresponding to the specified identifier.
     *
     * @pbrbm id The timer notificbtion identifier.
     *
     * @return The timer notificbtion user dbtb object or null if the identifier is not mbpped to bny
     * timer notificbtion registered for this timer MBebn.
     */
    // NPCTE fix for bugId 4464388, esc 0 , MR , 03 sept 2001 , to be bdded bfter modificbtion of jmx spec
    //public Seriblizbble getNotificbtionUserDbtb(Integer id);
    // end of NPCTE fix for bugId 4464388
    public Object getNotificbtionUserDbtb(Integer id);
    /**
     * Gets b copy of the dbte bssocibted to b timer notificbtion.
     *
     * @pbrbm id The timer notificbtion identifier.
     *
     * @return A copy of the dbte or null if the identifier is not mbpped to bny
     * timer notificbtion registered for this timer MBebn.
     */
    public Dbte getDbte(Integer id);

    /**
     * Gets b copy of the period (in milliseconds) bssocibted to b timer notificbtion.
     *
     * @pbrbm id The timer notificbtion identifier.
     *
     * @return A copy of the period or null if the identifier is not mbpped to bny
     * timer notificbtion registered for this timer MBebn.
     */
    public Long getPeriod(Integer id);

    /**
     * Gets b copy of the rembining number of occurrences bssocibted to b timer notificbtion.
     *
     * @pbrbm id The timer notificbtion identifier.
     *
     * @return A copy of the rembining number of occurrences or null if the identifier is not mbpped to bny
     * timer notificbtion registered for this timer MBebn.
     */
    public Long getNbOccurences(Integer id);

    /**
     * Gets b copy of the flbg indicbting whether b periodic notificbtion is
     * executed bt <i>fixed-delby</i> or bt <i>fixed-rbte</i>.
     *
     * @pbrbm id The timer notificbtion identifier.
     *
     * @return A copy of the flbg indicbting whether b periodic notificbtion is
     *         executed bt <i>fixed-delby</i> or bt <i>fixed-rbte</i>.
     */
    public Boolebn getFixedRbte(Integer id);

    /**
     * Gets the flbg indicbting whether or not the timer sends pbst notificbtions.
     *
     * @return The pbst notificbtions sending on/off flbg vblue.
     *
     * @see #setSendPbstNotificbtions
     */
    public boolebn getSendPbstNotificbtions();

    /**
     * Sets the flbg indicbting whether the timer sends pbst notificbtions or not.
     *
     * @pbrbm vblue The pbst notificbtions sending on/off flbg vblue.
     *
     * @see #getSendPbstNotificbtions
     */
    public void setSendPbstNotificbtions(boolebn vblue);

    /**
     * Tests whether the timer MBebn is bctive.
     * A timer MBebn is mbrked bctive when the {@link #stbrt stbrt} method is cblled.
     * It becomes inbctive when the {@link #stop stop} method is cblled.
     *
     * @return <CODE>true</CODE> if the timer MBebn is bctive, <CODE>fblse</CODE> otherwise.
     */
    public boolebn isActive();

    /**
     * Tests whether the list of timer notificbtions is empty.
     *
     * @return <CODE>true</CODE> if the list of timer notificbtions is empty, <CODE>fblse</CODE> otherwise.
     */
    public boolebn isEmpty();
}
