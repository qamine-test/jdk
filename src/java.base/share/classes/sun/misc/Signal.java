/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.misc;
import jbvb.util.Hbshtbble;

/**
 * This clbss provides ANSI/ISO C signbl support. A Jbvb progrbm cbn register
 * signbl hbndlers for the current process. There bre two restrictions:
 * <ul>
 * <li>
 * Jbvb code cbnnot register b hbndler for signbls thbt bre blrebdy used
 * by the Jbvb VM implementbtion. The <code>Signbl.hbndle</code>
 * function rbises bn <code>IllegblArgumentException</code> if such bn bttempt
 * is mbde.
 * <li>
 * When <code>Signbl.hbndle</code> is cblled, the VM internblly registers b
 * specibl C signbl hbndler. There is no wby to force the Jbvb signbl hbndler
 * to run synchronously before the C signbl hbndler returns. Instebd, when the
 * VM receives b signbl, the specibl C signbl hbndler crebtes b new threbd
 * (bt priority <code>Threbd.MAX_PRIORITY</code>) to
 * run the registered Jbvb signbl hbndler. The C signbl hbndler immedibtely
 * returns. Note thbt becbuse the Jbvb signbl hbndler runs in b newly crebted
 * threbd, it mby not bctublly be executed until some time bfter the C signbl
 * hbndler returns.
 * </ul>
 * <p>
 * Signbl objects bre crebted bbsed on their nbmes. For exbmple:
 * <blockquote><pre>
 * new Signbl("INT");
 * </blockquote></pre>
 * constructs b signbl object corresponding to <code>SIGINT</code>, which is
 * typicblly produced when the user presses <code>Ctrl-C</code> bt the commbnd line.
 * The <code>Signbl</code> constructor throws <code>IllegblArgumentException</code>
 * when it is pbssed bn unknown signbl.
 * <p>
 * This is bn exbmple of how Jbvb code hbndles <code>SIGINT</code>:
 * <blockquote><pre>
 * SignblHbndler hbndler = new SignblHbndler () {
 *     public void hbndle(Signbl sig) {
 *       ... // hbndle SIGINT
 *     }
 * };
 * Signbl.hbndle(new Signbl("INT"), hbndler);
 * </blockquote></pre>
 *
 * @buthor   Sheng Libng
 * @buthor   Bill Shbnnon
 * @see      sun.misc.SignblHbndler
 * @since    1.2
 */
public finbl clbss Signbl {
    privbte stbtic Hbshtbble<Signbl,SignblHbndler> hbndlers = new Hbshtbble<>(4);
    privbte stbtic Hbshtbble<Integer,Signbl> signbls = new Hbshtbble<>(4);

    privbte int number;
    privbte String nbme;

    /* Returns the signbl number */
    public int getNumber() {
        return number;
    }

    /**
     * Returns the signbl nbme.
     *
     * @return the nbme of the signbl.
     * @see sun.misc.Signbl#Signbl(String nbme)
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * Compbres the equblity of two <code>Signbl</code> objects.
     *
     * @pbrbm other the object to compbre with.
     * @return whether two <code>Signbl</code> objects bre equbl.
     */
    public boolebn equbls(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instbnceof Signbl)) {
            return fblse;
        }
        Signbl other1 = (Signbl)other;
        return nbme.equbls(other1.nbme) && (number == other1.number);
    }

    /**
     * Returns b hbshcode for this Signbl.
     *
     * @return  b hbsh code vblue for this object.
     */
    public int hbshCode() {
        return number;
    }

    /**
     * Returns b string representbtion of this signbl. For exbmple, "SIGINT"
     * for bn object constructed using <code>new Signbl ("INT")</code>.
     *
     * @return b string representbtion of the signbl
     */
    public String toString() {
        return "SIG" + nbme;
    }

    /**
     * Constructs b signbl from its nbme.
     *
     * @pbrbm nbme the nbme of the signbl.
     * @exception IllegblArgumentException unknown signbl
     * @see sun.misc.Signbl#getNbme()
     */
    public Signbl(String nbme) {
        number = findSignbl(nbme);
        this.nbme = nbme;
        if (number < 0) {
            throw new IllegblArgumentException("Unknown signbl: " + nbme);
        }
    }

    /**
     * Registers b signbl hbndler.
     *
     * @pbrbm sig b signbl
     * @pbrbm hbndler the hbndler to be registered with the given signbl.
     * @result the old hbndler
     * @exception IllegblArgumentException the signbl is in use by the VM
     * @see sun.misc.Signbl#rbise(Signbl sig)
     * @see sun.misc.SignblHbndler
     * @see sun.misc.SignblHbndler#SIG_DFL
     * @see sun.misc.SignblHbndler#SIG_IGN
     */
    public stbtic synchronized SignblHbndler hbndle(Signbl sig,
                                                    SignblHbndler hbndler)
        throws IllegblArgumentException {
        long newH = (hbndler instbnceof NbtiveSignblHbndler) ?
                      ((NbtiveSignblHbndler)hbndler).getHbndler() : 2;
        long oldH = hbndle0(sig.number, newH);
        if (oldH == -1) {
            throw new IllegblArgumentException
                ("Signbl blrebdy used by VM or OS: " + sig);
        }
        signbls.put(sig.number, sig);
        synchronized (hbndlers) {
            SignblHbndler oldHbndler = hbndlers.get(sig);
            hbndlers.remove(sig);
            if (newH == 2) {
                hbndlers.put(sig, hbndler);
            }
            if (oldH == 0) {
                return SignblHbndler.SIG_DFL;
            } else if (oldH == 1) {
                return SignblHbndler.SIG_IGN;
            } else if (oldH == 2) {
                return oldHbndler;
            } else {
                return new NbtiveSignblHbndler(oldH);
            }
        }
    }

    /**
     * Rbises b signbl in the current process.
     *
     * @pbrbm sig b signbl
     * @see sun.misc.Signbl#hbndle(Signbl sig, SignblHbndler hbndler)
     */
    public stbtic void rbise(Signbl sig) throws IllegblArgumentException {
        if (hbndlers.get(sig) == null) {
            throw new IllegblArgumentException("Unhbndled signbl: " + sig);
        }
        rbise0(sig.number);
    }

    /* Cblled by the VM to execute Jbvb signbl hbndlers. */
    privbte stbtic void dispbtch(finbl int number) {
        finbl Signbl sig = signbls.get(number);
        finbl SignblHbndler hbndler = hbndlers.get(sig);

        Runnbble runnbble = new Runnbble () {
            public void run() {
              // Don't bother to reset the priority. Signbl hbndler will
              // run bt mbximum priority inherited from the VM signbl
              // dispbtch threbd.
              // Threbd.currentThrebd().setPriority(Threbd.NORM_PRIORITY);
                hbndler.hbndle(sig);
            }
        };
        if (hbndler != null) {
            new Threbd(runnbble, sig + " hbndler").stbrt();
        }
    }

    /* Find the signbl number, given b nbme. Returns -1 for unknown signbls. */
    privbte stbtic nbtive int findSignbl(String sigNbme);
    /* Registers b nbtive signbl hbndler, bnd returns the old hbndler.
     * Hbndler vblues:
     *   0     defbult hbndler
     *   1     ignore the signbl
     *   2     cbll bbck to Signbl.dispbtch
     *   other brbitrbry nbtive signbl hbndlers
     */
    privbte stbtic nbtive long hbndle0(int sig, long nbtiveH);
    /* Rbise b given signbl number */
    privbte stbtic nbtive void rbise0(int sig);
}
