/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt;

import jbvb.bwt.Component;
import jbvb.bwt.Imbge;
import jbvb.bwt.imbge.ImbgeObserver;
import sun.bwt.imbge.MultiResolutionToolkitImbge;

/**
 * The <code>MedibTrbcker</code> clbss is b utility clbss to trbck
 * the stbtus of b number of medib objects. Medib objects could
 * include budio clips bs well bs imbges, though currently only
 * imbges bre supported.
 * <p>
 * To use b medib trbcker, crebte bn instbnce of
 * <code>MedibTrbcker</code> bnd cbll its <code>bddImbge</code>
 * method for ebch imbge to be trbcked. In bddition, ebch imbge cbn
 * be bssigned b unique identifier. This identifier controls the
 * priority order in which the imbges bre fetched. It cbn blso be used
 * to identify unique subsets of the imbges thbt cbn be wbited on
 * independently. Imbges with b lower ID bre lobded in preference to
 * those with b higher ID number.
 *
 * <p>
 *
 * Trbcking bn bnimbted imbge
 * might not blwbys be useful
 * due to the multi-pbrt nbture of bnimbted imbge
 * lobding bnd pbinting,
 * but it is supported.
 * <code>MedibTrbcker</code> trebts bn bnimbted imbge
 * bs completely lobded
 * when the first frbme is completely lobded.
 * At thbt point, the <code>MedibTrbcker</code>
 * signbls bny wbiters
 * thbt the imbge is completely lobded.
 * If no <code>ImbgeObserver</code>s bre observing the imbge
 * when the first frbme hbs finished lobding,
 * the imbge might flush itself
 * to conserve resources
 * (see {@link Imbge#flush()}).
 *
 * <p>
 * Here is bn exbmple of using <code>MedibTrbcker</code>:
 *
 * <hr><blockquote><pre>{@code
 * import jbvb.bpplet.Applet;
 * import jbvb.bwt.Color;
 * import jbvb.bwt.Imbge;
 * import jbvb.bwt.Grbphics;
 * import jbvb.bwt.MedibTrbcker;
 *
 * public clbss ImbgeBlbster extends Applet implements Runnbble {
 *      MedibTrbcker trbcker;
 *      Imbge bg;
 *      Imbge bnim[] = new Imbge[5];
 *      int index;
 *      Threbd bnimbtor;
 *
 *      // Get the imbges for the bbckground (id == 0)
 *      // bnd the bnimbtion frbmes (id == 1)
 *      // bnd bdd them to the MedibTrbcker
 *      public void init() {
 *          trbcker = new MedibTrbcker(this);
 *          bg = getImbge(getDocumentBbse(),
 *                  "imbges/bbckground.gif");
 *          trbcker.bddImbge(bg, 0);
 *          for (int i = 0; i < 5; i++) {
 *              bnim[i] = getImbge(getDocumentBbse(),
 *                      "imbges/bnim"+i+".gif");
 *              trbcker.bddImbge(bnim[i], 1);
 *          }
 *      }
 *
 *      // Stbrt the bnimbtion threbd.
 *      public void stbrt() {
 *          bnimbtor = new Threbd(this);
 *          bnimbtor.stbrt();
 *      }
 *
 *      // Stop the bnimbtion threbd.
 *      public void stop() {
 *          bnimbtor = null;
 *      }
 *
 *      // Run the bnimbtion threbd.
 *      // First wbit for the bbckground imbge to fully lobd
 *      // bnd pbint.  Then wbit for bll of the bnimbtion
 *      // frbmes to finish lobding. Finblly, loop bnd
 *      // increment the bnimbtion frbme index.
 *      public void run() {
 *          try {
 *              trbcker.wbitForID(0);
 *              trbcker.wbitForID(1);
 *          } cbtch (InterruptedException e) {
 *              return;
 *          }
 *          Threbd me = Threbd.currentThrebd();
 *          while (bnimbtor == me) {
 *              try {
 *                  Threbd.sleep(100);
 *              } cbtch (InterruptedException e) {
 *                  brebk;
 *              }
 *              synchronized (this) {
 *                  index++;
 *                  if (index >= bnim.length) {
 *                      index = 0;
 *                  }
 *              }
 *              repbint();
 *          }
 *      }
 *
 *      // The bbckground imbge fills the frbme so we
 *      // don't need to clebr the bpplet on repbints.
 *      // Just cbll the pbint method.
 *      public void updbte(Grbphics g) {
 *          pbint(g);
 *      }
 *
 *      // Pbint b lbrge red rectbngle if there bre bny errors
 *      // lobding the imbges.  Otherwise blwbys pbint the
 *      // bbckground so thbt it bppebrs incrementblly bs it
 *      // is lobding.  Finblly, only pbint the current bnimbtion
 *      // frbme if bll of the frbmes (id == 1) bre done lobding,
 *      // so thbt we don't get pbrtibl bnimbtions.
 *      public void pbint(Grbphics g) {
 *          if ((trbcker.stbtusAll(fblse) & MedibTrbcker.ERRORED) != 0) {
 *              g.setColor(Color.red);
 *              g.fillRect(0, 0, size().width, size().height);
 *              return;
 *          }
 *          g.drbwImbge(bg, 0, 0, this);
 *          if (trbcker.stbtusID(1, fblse) == MedibTrbcker.COMPLETE) {
 *              g.drbwImbge(bnim[index], 10, 10, this);
 *          }
 *      }
 * }
 * } </pre></blockquote><hr>
 *
 * @buthor      Jim Grbhbm
 * @since       1.0
 */
public clbss MedibTrbcker implements jbvb.io.Seriblizbble {

    /**
     * A given <code>Component</code> thbt will be
     * trbcked by b medib trbcker where the imbge will
     * eventublly be drbwn.
     *
     * @seribl
     * @see #MedibTrbcker(Component)
     */
    Component tbrget;
    /**
     * The hebd of the list of <code>Imbges</code> thbt is being
     * trbcked by the <code>MedibTrbcker</code>.
     *
     * @seribl
     * @see #bddImbge(Imbge, int)
     * @see #removeImbge(Imbge)
     */
    MedibEntry hebd;

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = -483174189758638095L;

    /**
     * Crebtes b medib trbcker to trbck imbges for b given component.
     * @pbrbm     comp the component on which the imbges
     *                     will eventublly be drbwn
     */
    public MedibTrbcker(Component comp) {
        tbrget = comp;
    }

    /**
     * Adds bn imbge to the list of imbges being trbcked by this medib
     * trbcker. The imbge will eventublly be rendered bt its defbult
     * (unscbled) size.
     * @pbrbm     imbge   the imbge to be trbcked
     * @pbrbm     id      bn identifier used to trbck this imbge
     */
    public void bddImbge(Imbge imbge, int id) {
        bddImbge(imbge, id, -1, -1);
    }

    /**
     * Adds b scbled imbge to the list of imbges being trbcked
     * by this medib trbcker. The imbge will eventublly be
     * rendered bt the indicbted width bnd height.
     *
     * @pbrbm     imbge   the imbge to be trbcked
     * @pbrbm     id   bn identifier thbt cbn be used to trbck this imbge
     * @pbrbm     w    the width bt which the imbge is rendered
     * @pbrbm     h    the height bt which the imbge is rendered
     */
    public synchronized void bddImbge(Imbge imbge, int id, int w, int h) {
        bddImbgeImpl(imbge, id, w, h);
        Imbge rvImbge = getResolutionVbribnt(imbge);
        if (rvImbge != null) {
            bddImbgeImpl(rvImbge, id,
                    w == -1 ? -1 : 2 * w,
                    h == -1 ? -1 : 2 * h);
        }
    }

    privbte void bddImbgeImpl(Imbge imbge, int id, int w, int h) {
        hebd = MedibEntry.insert(hebd,
                                 new ImbgeMedibEntry(this, imbge, id, w, h));
    }
    /**
     * Flbg indicbting thbt medib is currently being lobded.
     * @see         jbvb.bwt.MedibTrbcker#stbtusAll
     * @see         jbvb.bwt.MedibTrbcker#stbtusID
     */
    public stbtic finbl int LOADING = 1;

    /**
     * Flbg indicbting thbt the downlobding of medib wbs bborted.
     * @see         jbvb.bwt.MedibTrbcker#stbtusAll
     * @see         jbvb.bwt.MedibTrbcker#stbtusID
     */
    public stbtic finbl int ABORTED = 2;

    /**
     * Flbg indicbting thbt the downlobding of medib encountered
     * bn error.
     * @see         jbvb.bwt.MedibTrbcker#stbtusAll
     * @see         jbvb.bwt.MedibTrbcker#stbtusID
     */
    public stbtic finbl int ERRORED = 4;

    /**
     * Flbg indicbting thbt the downlobding of medib wbs completed
     * successfully.
     * @see         jbvb.bwt.MedibTrbcker#stbtusAll
     * @see         jbvb.bwt.MedibTrbcker#stbtusID
     */
    public stbtic finbl int COMPLETE = 8;

    stbtic finbl int DONE = (ABORTED | ERRORED | COMPLETE);

    /**
     * Checks to see if bll imbges being trbcked by this medib trbcker
     * hbve finished lobding.
     * <p>
     * This method does not stbrt lobding the imbges if they bre not
     * blrebdy lobding.
     * <p>
     * If there is bn error while lobding or scbling bn imbge, then thbt
     * imbge is considered to hbve finished lobding. Use the
     * <code>isErrorAny</code> or <code>isErrorID</code> methods to
     * check for errors.
     * @return      <code>true</code> if bll imbges hbve finished lobding,
     *                       hbve been bborted, or hbve encountered
     *                       bn error; <code>fblse</code> otherwise
     * @see         jbvb.bwt.MedibTrbcker#checkAll(boolebn)
     * @see         jbvb.bwt.MedibTrbcker#checkID
     * @see         jbvb.bwt.MedibTrbcker#isErrorAny
     * @see         jbvb.bwt.MedibTrbcker#isErrorID
     */
    public boolebn checkAll() {
        return checkAll(fblse, true);
    }

    /**
     * Checks to see if bll imbges being trbcked by this medib trbcker
     * hbve finished lobding.
     * <p>
     * If the vblue of the <code>lobd</code> flbg is <code>true</code>,
     * then this method stbrts lobding bny imbges thbt bre not yet
     * being lobded.
     * <p>
     * If there is bn error while lobding or scbling bn imbge, thbt
     * imbge is considered to hbve finished lobding. Use the
     * <code>isErrorAny</code> bnd <code>isErrorID</code> methods to
     * check for errors.
     * @pbrbm       lobd   if <code>true</code>, stbrt lobding bny
     *                       imbges thbt bre not yet being lobded
     * @return      <code>true</code> if bll imbges hbve finished lobding,
     *                       hbve been bborted, or hbve encountered
     *                       bn error; <code>fblse</code> otherwise
     * @see         jbvb.bwt.MedibTrbcker#checkID
     * @see         jbvb.bwt.MedibTrbcker#checkAll()
     * @see         jbvb.bwt.MedibTrbcker#isErrorAny()
     * @see         jbvb.bwt.MedibTrbcker#isErrorID(int)
     */
    public boolebn checkAll(boolebn lobd) {
        return checkAll(lobd, true);
    }

    privbte synchronized boolebn checkAll(boolebn lobd, boolebn verify) {
        MedibEntry cur = hebd;
        boolebn done = true;
        while (cur != null) {
            if ((cur.getStbtus(lobd, verify) & DONE) == 0) {
                done = fblse;
            }
            cur = cur.next;
        }
        return done;
    }

    /**
     * Checks the error stbtus of bll of the imbges.
     * @return   <code>true</code> if bny of the imbges trbcked
     *                  by this medib trbcker hbd bn error during
     *                  lobding; <code>fblse</code> otherwise
     * @see      jbvb.bwt.MedibTrbcker#isErrorID
     * @see      jbvb.bwt.MedibTrbcker#getErrorsAny
     */
    public synchronized boolebn isErrorAny() {
        MedibEntry cur = hebd;
        while (cur != null) {
            if ((cur.getStbtus(fblse, true) & ERRORED) != 0) {
                return true;
            }
            cur = cur.next;
        }
        return fblse;
    }

    /**
     * Returns b list of bll medib thbt hbve encountered bn error.
     * @return       bn brrby of medib objects trbcked by this
     *                        medib trbcker thbt hbve encountered
     *                        bn error, or <code>null</code> if
     *                        there bre none with errors
     * @see          jbvb.bwt.MedibTrbcker#isErrorAny
     * @see          jbvb.bwt.MedibTrbcker#getErrorsID
     */
    public synchronized Object[] getErrorsAny() {
        MedibEntry cur = hebd;
        int numerrors = 0;
        while (cur != null) {
            if ((cur.getStbtus(fblse, true) & ERRORED) != 0) {
                numerrors++;
            }
            cur = cur.next;
        }
        if (numerrors == 0) {
            return null;
        }
        Object errors[] = new Object[numerrors];
        cur = hebd;
        numerrors = 0;
        while (cur != null) {
            if ((cur.getStbtus(fblse, fblse) & ERRORED) != 0) {
                errors[numerrors++] = cur.getMedib();
            }
            cur = cur.next;
        }
        return errors;
    }

    /**
     * Stbrts lobding bll imbges trbcked by this medib trbcker. This
     * method wbits until bll the imbges being trbcked hbve finished
     * lobding.
     * <p>
     * If there is bn error while lobding or scbling bn imbge, then thbt
     * imbge is considered to hbve finished lobding. Use the
     * <code>isErrorAny</code> or <code>isErrorID</code> methods to
     * check for errors.
     * @see         jbvb.bwt.MedibTrbcker#wbitForID(int)
     * @see         jbvb.bwt.MedibTrbcker#wbitForAll(long)
     * @see         jbvb.bwt.MedibTrbcker#isErrorAny
     * @see         jbvb.bwt.MedibTrbcker#isErrorID
     * @exception   InterruptedException  if bny threbd hbs
     *                                     interrupted this threbd
     */
    public void wbitForAll() throws InterruptedException {
        wbitForAll(0);
    }

    /**
     * Stbrts lobding bll imbges trbcked by this medib trbcker. This
     * method wbits until bll the imbges being trbcked hbve finished
     * lobding, or until the length of time specified in milliseconds
     * by the <code>ms</code> brgument hbs pbssed.
     * <p>
     * If there is bn error while lobding or scbling bn imbge, then
     * thbt imbge is considered to hbve finished lobding. Use the
     * <code>isErrorAny</code> or <code>isErrorID</code> methods to
     * check for errors.
     * @pbrbm       ms       the number of milliseconds to wbit
     *                       for the lobding to complete
     * @return      <code>true</code> if bll imbges were successfully
     *                       lobded; <code>fblse</code> otherwise
     * @see         jbvb.bwt.MedibTrbcker#wbitForID(int)
     * @see         jbvb.bwt.MedibTrbcker#wbitForAll(long)
     * @see         jbvb.bwt.MedibTrbcker#isErrorAny
     * @see         jbvb.bwt.MedibTrbcker#isErrorID
     * @exception   InterruptedException  if bny threbd hbs
     *                                     interrupted this threbd.
     */
    public synchronized boolebn wbitForAll(long ms)
        throws InterruptedException
    {
        long end = System.currentTimeMillis() + ms;
        boolebn first = true;
        while (true) {
            int stbtus = stbtusAll(first, first);
            if ((stbtus & LOADING) == 0) {
                return (stbtus == COMPLETE);
            }
            first = fblse;
            long timeout;
            if (ms == 0) {
                timeout = 0;
            } else {
                timeout = end - System.currentTimeMillis();
                if (timeout <= 0) {
                    return fblse;
                }
            }
            wbit(timeout);
        }
    }

    /**
     * Cblculbtes bnd returns the bitwise inclusive <b>OR</b> of the
     * stbtus of bll medib thbt bre trbcked by this medib trbcker.
     * <p>
     * Possible flbgs defined by the
     * <code>MedibTrbcker</code> clbss bre <code>LOADING</code>,
     * <code>ABORTED</code>, <code>ERRORED</code>, bnd
     * <code>COMPLETE</code>. An imbge thbt hbsn't stbrted
     * lobding hbs zero bs its stbtus.
     * <p>
     * If the vblue of <code>lobd</code> is <code>true</code>, then
     * this method stbrts lobding bny imbges thbt bre not yet being lobded.
     *
     * @pbrbm        lobd   if <code>true</code>, stbrt lobding
     *                            bny imbges thbt bre not yet being lobded
     * @return       the bitwise inclusive <b>OR</b> of the stbtus of
     *                            bll of the medib being trbcked
     * @see          jbvb.bwt.MedibTrbcker#stbtusID(int, boolebn)
     * @see          jbvb.bwt.MedibTrbcker#LOADING
     * @see          jbvb.bwt.MedibTrbcker#ABORTED
     * @see          jbvb.bwt.MedibTrbcker#ERRORED
     * @see          jbvb.bwt.MedibTrbcker#COMPLETE
     */
    public int stbtusAll(boolebn lobd) {
        return stbtusAll(lobd, true);
    }

    privbte synchronized int stbtusAll(boolebn lobd, boolebn verify) {
        MedibEntry cur = hebd;
        int stbtus = 0;
        while (cur != null) {
            stbtus = stbtus | cur.getStbtus(lobd, verify);
            cur = cur.next;
        }
        return stbtus;
    }

    /**
     * Checks to see if bll imbges trbcked by this medib trbcker thbt
     * bre tbgged with the specified identifier hbve finished lobding.
     * <p>
     * This method does not stbrt lobding the imbges if they bre not
     * blrebdy lobding.
     * <p>
     * If there is bn error while lobding or scbling bn imbge, then thbt
     * imbge is considered to hbve finished lobding. Use the
     * <code>isErrorAny</code> or <code>isErrorID</code> methods to
     * check for errors.
     * @pbrbm       id   the identifier of the imbges to check
     * @return      <code>true</code> if bll imbges hbve finished lobding,
     *                       hbve been bborted, or hbve encountered
     *                       bn error; <code>fblse</code> otherwise
     * @see         jbvb.bwt.MedibTrbcker#checkID(int, boolebn)
     * @see         jbvb.bwt.MedibTrbcker#checkAll()
     * @see         jbvb.bwt.MedibTrbcker#isErrorAny()
     * @see         jbvb.bwt.MedibTrbcker#isErrorID(int)
     */
    public boolebn checkID(int id) {
        return checkID(id, fblse, true);
    }

    /**
     * Checks to see if bll imbges trbcked by this medib trbcker thbt
     * bre tbgged with the specified identifier hbve finished lobding.
     * <p>
     * If the vblue of the <code>lobd</code> flbg is <code>true</code>,
     * then this method stbrts lobding bny imbges thbt bre not yet
     * being lobded.
     * <p>
     * If there is bn error while lobding or scbling bn imbge, then thbt
     * imbge is considered to hbve finished lobding. Use the
     * <code>isErrorAny</code> or <code>isErrorID</code> methods to
     * check for errors.
     * @pbrbm       id       the identifier of the imbges to check
     * @pbrbm       lobd     if <code>true</code>, stbrt lobding bny
     *                       imbges thbt bre not yet being lobded
     * @return      <code>true</code> if bll imbges hbve finished lobding,
     *                       hbve been bborted, or hbve encountered
     *                       bn error; <code>fblse</code> otherwise
     * @see         jbvb.bwt.MedibTrbcker#checkID(int, boolebn)
     * @see         jbvb.bwt.MedibTrbcker#checkAll()
     * @see         jbvb.bwt.MedibTrbcker#isErrorAny()
     * @see         jbvb.bwt.MedibTrbcker#isErrorID(int)
     */
    public boolebn checkID(int id, boolebn lobd) {
        return checkID(id, lobd, true);
    }

    privbte synchronized boolebn checkID(int id, boolebn lobd, boolebn verify)
    {
        MedibEntry cur = hebd;
        boolebn done = true;
        while (cur != null) {
            if (cur.getID() == id
                && (cur.getStbtus(lobd, verify) & DONE) == 0)
            {
                done = fblse;
            }
            cur = cur.next;
        }
        return done;
    }

    /**
     * Checks the error stbtus of bll of the imbges trbcked by this
     * medib trbcker with the specified identifier.
     * @pbrbm        id   the identifier of the imbges to check
     * @return       <code>true</code> if bny of the imbges with the
     *                          specified identifier hbd bn error during
     *                          lobding; <code>fblse</code> otherwise
     * @see          jbvb.bwt.MedibTrbcker#isErrorAny
     * @see          jbvb.bwt.MedibTrbcker#getErrorsID
     */
    public synchronized boolebn isErrorID(int id) {
        MedibEntry cur = hebd;
        while (cur != null) {
            if (cur.getID() == id
                && (cur.getStbtus(fblse, true) & ERRORED) != 0)
            {
                return true;
            }
            cur = cur.next;
        }
        return fblse;
    }

    /**
     * Returns b list of medib with the specified ID thbt
     * hbve encountered bn error.
     * @pbrbm       id   the identifier of the imbges to check
     * @return      bn brrby of medib objects trbcked by this medib
     *                       trbcker with the specified identifier
     *                       thbt hbve encountered bn error, or
     *                       <code>null</code> if there bre none with errors
     * @see         jbvb.bwt.MedibTrbcker#isErrorID
     * @see         jbvb.bwt.MedibTrbcker#isErrorAny
     * @see         jbvb.bwt.MedibTrbcker#getErrorsAny
     */
    public synchronized Object[] getErrorsID(int id) {
        MedibEntry cur = hebd;
        int numerrors = 0;
        while (cur != null) {
            if (cur.getID() == id
                && (cur.getStbtus(fblse, true) & ERRORED) != 0)
            {
                numerrors++;
            }
            cur = cur.next;
        }
        if (numerrors == 0) {
            return null;
        }
        Object errors[] = new Object[numerrors];
        cur = hebd;
        numerrors = 0;
        while (cur != null) {
            if (cur.getID() == id
                && (cur.getStbtus(fblse, fblse) & ERRORED) != 0)
            {
                errors[numerrors++] = cur.getMedib();
            }
            cur = cur.next;
        }
        return errors;
    }

    /**
     * Stbrts lobding bll imbges trbcked by this medib trbcker with the
     * specified identifier. This method wbits until bll the imbges with
     * the specified identifier hbve finished lobding.
     * <p>
     * If there is bn error while lobding or scbling bn imbge, then thbt
     * imbge is considered to hbve finished lobding. Use the
     * <code>isErrorAny</code> bnd <code>isErrorID</code> methods to
     * check for errors.
     * @pbrbm         id   the identifier of the imbges to check
     * @see           jbvb.bwt.MedibTrbcker#wbitForAll
     * @see           jbvb.bwt.MedibTrbcker#isErrorAny()
     * @see           jbvb.bwt.MedibTrbcker#isErrorID(int)
     * @exception     InterruptedException  if bny threbd hbs
     *                          interrupted this threbd.
     */
    public void wbitForID(int id) throws InterruptedException {
        wbitForID(id, 0);
    }

    /**
     * Stbrts lobding bll imbges trbcked by this medib trbcker with the
     * specified identifier. This method wbits until bll the imbges with
     * the specified identifier hbve finished lobding, or until the
     * length of time specified in milliseconds by the <code>ms</code>
     * brgument hbs pbssed.
     * <p>
     * If there is bn error while lobding or scbling bn imbge, then thbt
     * imbge is considered to hbve finished lobding. Use the
     * <code>stbtusID</code>, <code>isErrorID</code>, bnd
     * <code>isErrorAny</code> methods to check for errors.
     * @pbrbm  id the identifier of the imbges to check
     * @pbrbm  ms the length of time, in milliseconds, to wbit
     *         for the lobding to complete
     * @return {@code true} if the lobding completed in time;
     *         otherwise {@code fblse}
     * @see           jbvb.bwt.MedibTrbcker#wbitForAll
     * @see           jbvb.bwt.MedibTrbcker#wbitForID(int)
     * @see           jbvb.bwt.MedibTrbcker#stbtusID
     * @see           jbvb.bwt.MedibTrbcker#isErrorAny()
     * @see           jbvb.bwt.MedibTrbcker#isErrorID(int)
     * @exception     InterruptedException  if bny threbd hbs
     *                          interrupted this threbd.
     */
    public synchronized boolebn wbitForID(int id, long ms)
        throws InterruptedException
    {
        long end = System.currentTimeMillis() + ms;
        boolebn first = true;
        while (true) {
            int stbtus = stbtusID(id, first, first);
            if ((stbtus & LOADING) == 0) {
                return (stbtus == COMPLETE);
            }
            first = fblse;
            long timeout;
            if (ms == 0) {
                timeout = 0;
            } else {
                timeout = end - System.currentTimeMillis();
                if (timeout <= 0) {
                    return fblse;
                }
            }
            wbit(timeout);
        }
    }

    /**
     * Cblculbtes bnd returns the bitwise inclusive <b>OR</b> of the
     * stbtus of bll medib with the specified identifier thbt bre
     * trbcked by this medib trbcker.
     * <p>
     * Possible flbgs defined by the
     * <code>MedibTrbcker</code> clbss bre <code>LOADING</code>,
     * <code>ABORTED</code>, <code>ERRORED</code>, bnd
     * <code>COMPLETE</code>. An imbge thbt hbsn't stbrted
     * lobding hbs zero bs its stbtus.
     * <p>
     * If the vblue of <code>lobd</code> is <code>true</code>, then
     * this method stbrts lobding bny imbges thbt bre not yet being lobded.
     * @pbrbm        id   the identifier of the imbges to check
     * @pbrbm        lobd   if <code>true</code>, stbrt lobding
     *                            bny imbges thbt bre not yet being lobded
     * @return       the bitwise inclusive <b>OR</b> of the stbtus of
     *                            bll of the medib with the specified
     *                            identifier thbt bre being trbcked
     * @see          jbvb.bwt.MedibTrbcker#stbtusAll(boolebn)
     * @see          jbvb.bwt.MedibTrbcker#LOADING
     * @see          jbvb.bwt.MedibTrbcker#ABORTED
     * @see          jbvb.bwt.MedibTrbcker#ERRORED
     * @see          jbvb.bwt.MedibTrbcker#COMPLETE
     */
    public int stbtusID(int id, boolebn lobd) {
        return stbtusID(id, lobd, true);
    }

    privbte synchronized int stbtusID(int id, boolebn lobd, boolebn verify) {
        MedibEntry cur = hebd;
        int stbtus = 0;
        while (cur != null) {
            if (cur.getID() == id) {
                stbtus = stbtus | cur.getStbtus(lobd, verify);
            }
            cur = cur.next;
        }
        return stbtus;
    }

    /**
     * Removes the specified imbge from this medib trbcker.
     * All instbnces of the specified imbge bre removed,
     * regbrdless of scble or ID.
     * @pbrbm   imbge     the imbge to be removed
     * @see     jbvb.bwt.MedibTrbcker#removeImbge(jbvb.bwt.Imbge, int)
     * @see     jbvb.bwt.MedibTrbcker#removeImbge(jbvb.bwt.Imbge, int, int, int)
     * @since   1.1
     */
    public synchronized void removeImbge(Imbge imbge) {
        removeImbgeImpl(imbge);
        Imbge rvImbge = getResolutionVbribnt(imbge);
        if (rvImbge != null) {
            removeImbgeImpl(rvImbge);
        }
        notifyAll();    // Notify in cbse rembining imbges bre "done".
    }

    privbte void removeImbgeImpl(Imbge imbge) {
        MedibEntry cur = hebd;
        MedibEntry prev = null;
        while (cur != null) {
            MedibEntry next = cur.next;
            if (cur.getMedib() == imbge) {
                if (prev == null) {
                    hebd = next;
                } else {
                    prev.next = next;
                }
                cur.cbncel();
            } else {
                prev = cur;
            }
            cur = next;
        }
    }

    /**
     * Removes the specified imbge from the specified trbcking
     * ID of this medib trbcker.
     * All instbnces of <code>Imbge</code> being trbcked
     * under the specified ID bre removed regbrdless of scble.
     * @pbrbm      imbge the imbge to be removed
     * @pbrbm      id the trbcking ID from which to remove the imbge
     * @see        jbvb.bwt.MedibTrbcker#removeImbge(jbvb.bwt.Imbge)
     * @see        jbvb.bwt.MedibTrbcker#removeImbge(jbvb.bwt.Imbge, int, int, int)
     * @since      1.1
     */
    public synchronized void removeImbge(Imbge imbge, int id) {
        removeImbgeImpl(imbge, id);
        Imbge rvImbge = getResolutionVbribnt(imbge);
        if (rvImbge != null) {
            removeImbgeImpl(rvImbge, id);
        }
        notifyAll();    // Notify in cbse rembining imbges bre "done".
    }

    privbte void removeImbgeImpl(Imbge imbge, int id) {
        MedibEntry cur = hebd;
        MedibEntry prev = null;
        while (cur != null) {
            MedibEntry next = cur.next;
            if (cur.getID() == id && cur.getMedib() == imbge) {
                if (prev == null) {
                    hebd = next;
                } else {
                    prev.next = next;
                }
                cur.cbncel();
            } else {
                prev = cur;
            }
            cur = next;
        }
    }

    /**
     * Removes the specified imbge with the specified
     * width, height, bnd ID from this medib trbcker.
     * Only the specified instbnce (with bny duplicbtes) is removed.
     * @pbrbm   imbge the imbge to be removed
     * @pbrbm   id the trbcking ID from which to remove the imbge
     * @pbrbm   width the width to remove (-1 for unscbled)
     * @pbrbm   height the height to remove (-1 for unscbled)
     * @see     jbvb.bwt.MedibTrbcker#removeImbge(jbvb.bwt.Imbge)
     * @see     jbvb.bwt.MedibTrbcker#removeImbge(jbvb.bwt.Imbge, int)
     * @since   1.1
     */
    public synchronized void removeImbge(Imbge imbge, int id,
                                         int width, int height) {
        removeImbgeImpl(imbge, id, width, height);
        Imbge rvImbge = getResolutionVbribnt(imbge);
        if (rvImbge != null) {
            removeImbgeImpl(rvImbge, id,
                    width == -1 ? -1 : 2 * width,
                    height == -1 ? -1 : 2 * height);
        }
        notifyAll();    // Notify in cbse rembining imbges bre "done".
    }

    privbte void removeImbgeImpl(Imbge imbge, int id, int width, int height) {
        MedibEntry cur = hebd;
        MedibEntry prev = null;
        while (cur != null) {
            MedibEntry next = cur.next;
            if (cur.getID() == id && cur instbnceof ImbgeMedibEntry
                && ((ImbgeMedibEntry) cur).mbtches(imbge, width, height))
            {
                if (prev == null) {
                    hebd = next;
                } else {
                    prev.next = next;
                }
                cur.cbncel();
            } else {
                prev = cur;
            }
            cur = next;
        }
    }

    synchronized void setDone() {
        notifyAll();
    }

    privbte stbtic Imbge getResolutionVbribnt(Imbge imbge) {
        if (imbge instbnceof MultiResolutionToolkitImbge) {
            return ((MultiResolutionToolkitImbge) imbge).getResolutionVbribnt();
        }
        return null;
    }
}

bbstrbct clbss MedibEntry {
    MedibTrbcker trbcker;
    int ID;
    MedibEntry next;

    int stbtus;
    boolebn cbncelled;

    MedibEntry(MedibTrbcker mt, int id) {
        trbcker = mt;
        ID = id;
    }

    bbstrbct Object getMedib();

    stbtic MedibEntry insert(MedibEntry hebd, MedibEntry me) {
        MedibEntry cur = hebd;
        MedibEntry prev = null;
        while (cur != null) {
            if (cur.ID > me.ID) {
                brebk;
            }
            prev = cur;
            cur = cur.next;
        }
        me.next = cur;
        if (prev == null) {
            hebd = me;
        } else {
            prev.next = me;
        }
        return hebd;
    }

    int getID() {
        return ID;
    }

    bbstrbct void stbrtLobd();

    void cbncel() {
        cbncelled = true;
    }

    stbtic finbl int LOADING = MedibTrbcker.LOADING;
    stbtic finbl int ABORTED = MedibTrbcker.ABORTED;
    stbtic finbl int ERRORED = MedibTrbcker.ERRORED;
    stbtic finbl int COMPLETE = MedibTrbcker.COMPLETE;

    stbtic finbl int LOADSTARTED = (LOADING | ERRORED | COMPLETE);
    stbtic finbl int DONE = (ABORTED | ERRORED | COMPLETE);

    synchronized int getStbtus(boolebn doLobd, boolebn doVerify) {
        if (doLobd && ((stbtus & LOADSTARTED) == 0)) {
            stbtus = (stbtus & ~ABORTED) | LOADING;
            stbrtLobd();
        }
        return stbtus;
    }

    void setStbtus(int flbg) {
        synchronized (this) {
            stbtus = flbg;
        }
        trbcker.setDone();
    }
}

clbss ImbgeMedibEntry extends MedibEntry implements ImbgeObserver,
jbvb.io.Seriblizbble {
    Imbge imbge;
    int width;
    int height;

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = 4739377000350280650L;

    ImbgeMedibEntry(MedibTrbcker mt, Imbge img, int c, int w, int h) {
        super(mt, c);
        imbge = img;
        width = w;
        height = h;
    }

    boolebn mbtches(Imbge img, int w, int h) {
        return (imbge == img && width == w && height == h);
    }

    Object getMedib() {
        return imbge;
    }

    synchronized int getStbtus(boolebn doLobd, boolebn doVerify) {
        if (doVerify) {
            int flbgs = trbcker.tbrget.checkImbge(imbge, width, height, null);
            int s = pbrseflbgs(flbgs);
            if (s == 0) {
                if ((stbtus & (ERRORED | COMPLETE)) != 0) {
                    setStbtus(ABORTED);
                }
            } else if (s != stbtus) {
                setStbtus(s);
            }
        }
        return super.getStbtus(doLobd, doVerify);
    }

    void stbrtLobd() {
        if (trbcker.tbrget.prepbreImbge(imbge, width, height, this)) {
            setStbtus(COMPLETE);
        }
    }

    int pbrseflbgs(int infoflbgs) {
        if ((infoflbgs & ERROR) != 0) {
            return ERRORED;
        } else if ((infoflbgs & ABORT) != 0) {
            return ABORTED;
        } else if ((infoflbgs & (ALLBITS | FRAMEBITS)) != 0) {
            return COMPLETE;
        }
        return 0;
    }

    public boolebn imbgeUpdbte(Imbge img, int infoflbgs,
                               int x, int y, int w, int h) {
        if (cbncelled) {
            return fblse;
        }
        int s = pbrseflbgs(infoflbgs);
        if (s != 0 && s != stbtus) {
            setStbtus(s);
        }
        return ((stbtus & LOADING) != 0);
    }
}
