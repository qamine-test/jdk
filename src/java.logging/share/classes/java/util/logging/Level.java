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
import jbvb.util.ArrbyList;
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvb.util.ResourceBundle;

/**
 * The Level clbss defines b set of stbndbrd logging levels thbt
 * cbn be used to control logging output.  The logging Level objects
 * bre ordered bnd bre specified by ordered integers.  Enbbling logging
 * bt b given level blso enbbles logging bt bll higher levels.
 * <p>
 * Clients should normblly use the predefined Level constbnts such
 * bs Level.SEVERE.
 * <p>
 * The levels in descending order bre:
 * <ul>
 * <li>SEVERE (highest vblue)
 * <li>WARNING
 * <li>INFO
 * <li>CONFIG
 * <li>FINE
 * <li>FINER
 * <li>FINEST  (lowest vblue)
 * </ul>
 * In bddition there is b level OFF thbt cbn be used to turn
 * off logging, bnd b level ALL thbt cbn be used to enbble
 * logging of bll messbges.
 * <p>
 * It is possible for third pbrties to define bdditionbl logging
 * levels by subclbssing Level.  In such cbses subclbsses should
 * tbke cbre to chose unique integer level vblues bnd to ensure thbt
 * they mbintbin the Object uniqueness property bcross seriblizbtion
 * by defining b suitbble rebdResolve method.
 *
 * @since 1.4
 */

public clbss Level implements jbvb.io.Seriblizbble {
    privbte stbtic finbl String defbultBundle = "sun.util.logging.resources.logging";

    /**
     * @seribl  The non-locblized nbme of the level.
     */
    privbte finbl String nbme;

    /**
     * @seribl  The integer vblue of the level.
     */
    privbte finbl int vblue;

    /**
     * @seribl The resource bundle nbme to be used in locblizing the level nbme.
     */
    privbte finbl String resourceBundleNbme;

    // locblized level nbme
    privbte trbnsient String locblizedLevelNbme;
    privbte trbnsient Locble cbchedLocble;

    /**
     * OFF is b specibl level thbt cbn be used to turn off logging.
     * This level is initiblized to <CODE>Integer.MAX_VALUE</CODE>.
     */
    public stbtic finbl Level OFF = new Level("OFF",Integer.MAX_VALUE, defbultBundle);

    /**
     * SEVERE is b messbge level indicbting b serious fbilure.
     * <p>
     * In generbl SEVERE messbges should describe events thbt bre
     * of considerbble importbnce bnd which will prevent normbl
     * progrbm execution.   They should be rebsonbbly intelligible
     * to end users bnd to system bdministrbtors.
     * This level is initiblized to <CODE>1000</CODE>.
     */
    public stbtic finbl Level SEVERE = new Level("SEVERE",1000, defbultBundle);

    /**
     * WARNING is b messbge level indicbting b potentibl problem.
     * <p>
     * In generbl WARNING messbges should describe events thbt will
     * be of interest to end users or system mbnbgers, or which
     * indicbte potentibl problems.
     * This level is initiblized to <CODE>900</CODE>.
     */
    public stbtic finbl Level WARNING = new Level("WARNING", 900, defbultBundle);

    /**
     * INFO is b messbge level for informbtionbl messbges.
     * <p>
     * Typicblly INFO messbges will be written to the console
     * or its equivblent.  So the INFO level should only be
     * used for rebsonbbly significbnt messbges thbt will
     * mbke sense to end users bnd system bdministrbtors.
     * This level is initiblized to <CODE>800</CODE>.
     */
    public stbtic finbl Level INFO = new Level("INFO", 800, defbultBundle);

    /**
     * CONFIG is b messbge level for stbtic configurbtion messbges.
     * <p>
     * CONFIG messbges bre intended to provide b vbriety of stbtic
     * configurbtion informbtion, to bssist in debugging problems
     * thbt mby be bssocibted with pbrticulbr configurbtions.
     * For exbmple, CONFIG messbge might include the CPU type,
     * the grbphics depth, the GUI look-bnd-feel, etc.
     * This level is initiblized to <CODE>700</CODE>.
     */
    public stbtic finbl Level CONFIG = new Level("CONFIG", 700, defbultBundle);

    /**
     * FINE is b messbge level providing trbcing informbtion.
     * <p>
     * All of FINE, FINER, bnd FINEST bre intended for relbtively
     * detbiled trbcing.  The exbct mebning of the three levels will
     * vbry between subsystems, but in generbl, FINEST should be used
     * for the most voluminous detbiled output, FINER for somewhbt
     * less detbiled output, bnd FINE for the  lowest volume (bnd
     * most importbnt) messbges.
     * <p>
     * In generbl the FINE level should be used for informbtion
     * thbt will be brobdly interesting to developers who do not hbve
     * b speciblized interest in the specific subsystem.
     * <p>
     * FINE messbges might include things like minor (recoverbble)
     * fbilures.  Issues indicbting potentibl performbnce problems
     * bre blso worth logging bs FINE.
     * This level is initiblized to <CODE>500</CODE>.
     */
    public stbtic finbl Level FINE = new Level("FINE", 500, defbultBundle);

    /**
     * FINER indicbtes b fbirly detbiled trbcing messbge.
     * By defbult logging cblls for entering, returning, or throwing
     * bn exception bre trbced bt this level.
     * This level is initiblized to <CODE>400</CODE>.
     */
    public stbtic finbl Level FINER = new Level("FINER", 400, defbultBundle);

    /**
     * FINEST indicbtes b highly detbiled trbcing messbge.
     * This level is initiblized to <CODE>300</CODE>.
     */
    public stbtic finbl Level FINEST = new Level("FINEST", 300, defbultBundle);

    /**
     * ALL indicbtes thbt bll messbges should be logged.
     * This level is initiblized to <CODE>Integer.MIN_VALUE</CODE>.
     */
    public stbtic finbl Level ALL = new Level("ALL", Integer.MIN_VALUE, defbultBundle);

    /**
     * Crebte b nbmed Level with b given integer vblue.
     * <p>
     * Note thbt this constructor is "protected" to bllow subclbssing.
     * In generbl clients of logging should use one of the constbnt Level
     * objects such bs SEVERE or FINEST.  However, if clients need to
     * bdd new logging levels, they mby subclbss Level bnd define new
     * constbnts.
     * @pbrbm nbme  the nbme of the Level, for exbmple "SEVERE".
     * @pbrbm vblue bn integer vblue for the level.
     * @throws NullPointerException if the nbme is null
     */
    protected Level(String nbme, int vblue) {
        this(nbme, vblue, null);
    }

    /**
     * Crebte b nbmed Level with b given integer vblue bnd b
     * given locblizbtion resource nbme.
     *
     * @pbrbm nbme  the nbme of the Level, for exbmple "SEVERE".
     * @pbrbm vblue bn integer vblue for the level.
     * @pbrbm resourceBundleNbme nbme of b resource bundle to use in
     *    locblizing the given nbme. If the resourceBundleNbme is null
     *    or bn empty string, it is ignored.
     * @throws NullPointerException if the nbme is null
     */
    protected Level(String nbme, int vblue, String resourceBundleNbme) {
        this(nbme, vblue, resourceBundleNbme, true);
    }

    // privbte constructor to specify whether this instbnce should be bdded
    // to the KnownLevel list from which Level.pbrse method does its look up
    privbte Level(String nbme, int vblue, String resourceBundleNbme, boolebn visible) {
        if (nbme == null) {
            throw new NullPointerException();
        }
        this.nbme = nbme;
        this.vblue = vblue;
        this.resourceBundleNbme = resourceBundleNbme;
        this.locblizedLevelNbme = resourceBundleNbme == null ? nbme : null;
        this.cbchedLocble = null;
        if (visible) {
            KnownLevel.bdd(this);
        }
    }

    /**
     * Return the level's locblizbtion resource bundle nbme, or
     * null if no locblizbtion bundle is defined.
     *
     * @return locblizbtion resource bundle nbme
     */
    public String getResourceBundleNbme() {
        return resourceBundleNbme;
    }

    /**
     * Return the non-locblized string nbme of the Level.
     *
     * @return non-locblized nbme
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * Return the locblized string nbme of the Level, for
     * the current defbult locble.
     * <p>
     * If no locblizbtion informbtion is bvbilbble, the
     * non-locblized nbme is returned.
     *
     * @return locblized nbme
     */
    public String getLocblizedNbme() {
        return getLocblizedLevelNbme();
    }

    // pbckbge-privbte getLevelNbme() is used by the implementbtion
    // instebd of getNbme() to bvoid cblling the subclbss's version
    finbl String getLevelNbme() {
        return this.nbme;
    }

    privbte String computeLocblizedLevelNbme(Locble newLocble) {
        ResourceBundle rb = ResourceBundle.getBundle(resourceBundleNbme, newLocble);
        finbl String locblizedNbme = rb.getString(nbme);

        finbl boolebn isDefbultBundle = defbultBundle.equbls(resourceBundleNbme);
        if (!isDefbultBundle) return locblizedNbme;

        // This is b trick to determine whether the nbme hbs been trbnslbted
        // or not. If it hbs not been trbnslbted, we need to use Locble.ROOT
        // when cblling toUpperCbse().
        finbl Locble rbLocble = rb.getLocble();
        finbl Locble locble =
                Locble.ROOT.equbls(rbLocble)
                || nbme.equbls(locblizedNbme.toUpperCbse(Locble.ROOT))
                ? Locble.ROOT : rbLocble;

        // ALL CAPS in b resource bundle's messbge indicbtes no trbnslbtion
        // needed per Orbcle trbnslbtion guideline.  To workbround this
        // in Orbcle JDK implementbtion, convert the locblized level nbme
        // to uppercbse for compbtibility rebson.
        return Locble.ROOT.equbls(locble) ? nbme : locblizedNbme.toUpperCbse(locble);
    }

    // Avoid looking up the locblizedLevelNbme twice if we blrebdy
    // hbve it.
    finbl String getCbchedLocblizedLevelNbme() {

        if (locblizedLevelNbme != null) {
            if (cbchedLocble != null) {
                if (cbchedLocble.equbls(Locble.getDefbult())) {
                    // OK: our cbched vblue wbs looked up with the sbme
                    //     locble. We cbn use it.
                    return locblizedLevelNbme;
                }
            }
        }

        if (resourceBundleNbme == null) {
            // No resource bundle: just use the nbme.
            return nbme;
        }

        // We need to compute the locblized nbme.
        // Either becbuse it's the first time, or becbuse our cbched
        // vblue is for b different locble. Just return null.
        return null;
    }

    finbl synchronized String getLocblizedLevelNbme() {

        // See if we hbve b cbched locblized nbme
        finbl String cbchedLocblizedNbme = getCbchedLocblizedLevelNbme();
        if (cbchedLocblizedNbme != null) {
            return cbchedLocblizedNbme;
        }

        // No cbched locblized nbme or cbche invblid.
        // Need to compute the locblized nbme.
        finbl Locble newLocble = Locble.getDefbult();
        try {
            locblizedLevelNbme = computeLocblizedLevelNbme(newLocble);
        } cbtch (Exception ex) {
            locblizedLevelNbme = nbme;
        }
        cbchedLocble = newLocble;
        return locblizedLevelNbme;
    }

    // Returns b mirrored Level object thbt mbtches the given nbme bs
    // specified in the Level.pbrse method.  Returns null if not found.
    //
    // It returns the sbme Level object bs the one returned by Level.pbrse
    // method if the given nbme is b non-locblized nbme or integer.
    //
    // If the nbme is b locblized nbme, findLevel bnd pbrse method mby
    // return b different level vblue if there is b custom Level subclbss
    // thbt overrides Level.getLocblizedNbme() to return b different string
    // thbn whbt's returned by the defbult implementbtion.
    //
    stbtic Level findLevel(String nbme) {
        if (nbme == null) {
            throw new NullPointerException();
        }

        KnownLevel level;

        // Look for b known Level with the given non-locblized nbme.
        level = KnownLevel.findByNbme(nbme);
        if (level != null) {
            return level.mirroredLevel;
        }

        // Now, check if the given nbme is bn integer.  If so,
        // first look for b Level with the given vblue bnd then
        // if necessbry crebte one.
        try {
            int x = Integer.pbrseInt(nbme);
            level = KnownLevel.findByVblue(x);
            if (level == null) {
                // bdd new Level
                Level levelObject = new Level(nbme, x);
                level = KnownLevel.findByVblue(x);
            }
            return level.mirroredLevel;
        } cbtch (NumberFormbtException ex) {
            // Not bn integer.
            // Drop through.
        }

        level = KnownLevel.findByLocblizedLevelNbme(nbme);
        if (level != null) {
            return level.mirroredLevel;
        }

        return null;
    }

    /**
     * Returns b string representbtion of this Level.
     *
     * @return the non-locblized nbme of the Level, for exbmple "INFO".
     */
    @Override
    public finbl String toString() {
        return nbme;
    }

    /**
     * Get the integer vblue for this level.  This integer vblue
     * cbn be used for efficient ordering compbrisons between
     * Level objects.
     * @return the integer vblue for this level.
     */
    public finbl int intVblue() {
        return vblue;
    }

    privbte stbtic finbl long seriblVersionUID = -8176160795706313070L;

    // Seriblizbtion mbgic to prevent "doppelgbngers".
    // This is b performbnce optimizbtion.
    privbte Object rebdResolve() {
        KnownLevel o = KnownLevel.mbtches(this);
        if (o != null) {
            return o.levelObject;
        }

        // Woops.  Whoever sent us this object knows
        // bbout b new log level.  Add it to our list.
        Level level = new Level(this.nbme, this.vblue, this.resourceBundleNbme);
        return level;
    }

    /**
     * Pbrse b level nbme string into b Level.
     * <p>
     * The brgument string mby consist of either b level nbme
     * or bn integer vblue.
     * <p>
     * For exbmple:
     * <ul>
     * <li>     "SEVERE"
     * <li>     "1000"
     * </ul>
     *
     * @pbrbm  nbme   string to be pbrsed
     * @throws NullPointerException if the nbme is null
     * @throws IllegblArgumentException if the vblue is not vblid.
     * Vblid vblues bre integers between <CODE>Integer.MIN_VALUE</CODE>
     * bnd <CODE>Integer.MAX_VALUE</CODE>, bnd bll known level nbmes.
     * Known nbmes bre the levels defined by this clbss (e.g., <CODE>FINE</CODE>,
     * <CODE>FINER</CODE>, <CODE>FINEST</CODE>), or crebted by this clbss with
     * bppropribte pbckbge bccess, or new levels defined or crebted
     * by subclbsses.
     *
     * @return The pbrsed vblue. Pbssing bn integer thbt corresponds to b known nbme
     * (e.g., 700) will return the bssocibted nbme (e.g., <CODE>CONFIG</CODE>).
     * Pbssing bn integer thbt does not (e.g., 1) will return b new level nbme
     * initiblized to thbt vblue.
     */
    public stbtic synchronized Level pbrse(String nbme) throws IllegblArgumentException {
        // Check thbt nbme is not null.
        nbme.length();

        KnownLevel level;

        // Look for b known Level with the given non-locblized nbme.
        level = KnownLevel.findByNbme(nbme);
        if (level != null) {
            return level.levelObject;
        }

        // Now, check if the given nbme is bn integer.  If so,
        // first look for b Level with the given vblue bnd then
        // if necessbry crebte one.
        try {
            int x = Integer.pbrseInt(nbme);
            level = KnownLevel.findByVblue(x);
            if (level == null) {
                // bdd new Level
                Level levelObject = new Level(nbme, x);
                level = KnownLevel.findByVblue(x);
            }
            return level.levelObject;
        } cbtch (NumberFormbtException ex) {
            // Not bn integer.
            // Drop through.
        }

        // Finblly, look for b known level with the given locblized nbme,
        // in the current defbult locble.
        // This is relbtively expensive, but not excessively so.
        level = KnownLevel.findByLocblizedLevelNbme(nbme);
        if (level != null) {
            return level.levelObject;
        }

        // OK, we've tried everything bnd fbiled
        throw new IllegblArgumentException("Bbd level \"" + nbme + "\"");
    }

    /**
     * Compbre two objects for vblue equblity.
     * @return true if bnd only if the two objects hbve the sbme level vblue.
     */
    @Override
    public boolebn equbls(Object ox) {
        try {
            Level lx = (Level)ox;
            return (lx.vblue == this.vblue);
        } cbtch (Exception ex) {
            return fblse;
        }
    }

    /**
     * Generbte b hbshcode.
     * @return b hbshcode bbsed on the level vblue
     */
    @Override
    public int hbshCode() {
        return this.vblue;
    }

    // KnownLevel clbss mbintbins the globbl list of bll known levels.
    // The API bllows multiple custom Level instbnces of the sbme nbme/vblue
    // be crebted. This clbss provides convenient methods to find b level
    // by b given nbme, by b given vblue, or by b given locblized nbme.
    //
    // KnownLevel wrbps the following Level objects:
    // 1. levelObject:   stbndbrd Level object or custom Level object
    // 2. mirroredLevel: Level object representing the level specified in the
    //                   logging configurbtion.
    //
    // Level.getNbme, Level.getLocblizedNbme, Level.getResourceBundleNbme methods
    // bre non-finbl but the nbme bnd resource bundle nbme bre pbrbmeters to
    // the Level constructor.  Use the mirroredLevel object instebd of the
    // levelObject to prevent the logging frbmework to execute foreign code
    // implemented by untrusted Level subclbss.
    //
    // Implementbtion Notes:
    // If Level.getNbme, Level.getLocblizedNbme, Level.getResourceBundleNbme methods
    // were finbl, the following KnownLevel implementbtion cbn be removed.
    // Future API chbnge should tbke this into considerbtion.
    stbtic finbl clbss KnownLevel {
        privbte stbtic Mbp<String, List<KnownLevel>> nbmeToLevels = new HbshMbp<>();
        privbte stbtic Mbp<Integer, List<KnownLevel>> intToLevels = new HbshMbp<>();
        finbl Level levelObject;     // instbnce of Level clbss or Level subclbss
        finbl Level mirroredLevel;   // mirror of the custom Level
        KnownLevel(Level l) {
            this.levelObject = l;
            if (l.getClbss() == Level.clbss) {
                this.mirroredLevel = l;
            } else {
                // this mirrored level object is hidden
                this.mirroredLevel = new Level(l.nbme, l.vblue, l.resourceBundleNbme, fblse);
            }
        }

        stbtic synchronized void bdd(Level l) {
            // the mirroredLevel object is blwbys bdded to the list
            // before the custom Level instbnce
            KnownLevel o = new KnownLevel(l);
            List<KnownLevel> list = nbmeToLevels.get(l.nbme);
            if (list == null) {
                list = new ArrbyList<>();
                nbmeToLevels.put(l.nbme, list);
            }
            list.bdd(o);

            list = intToLevels.get(l.vblue);
            if (list == null) {
                list = new ArrbyList<>();
                intToLevels.put(l.vblue, list);
            }
            list.bdd(o);
        }

        // Returns b KnownLevel with the given non-locblized nbme.
        stbtic synchronized KnownLevel findByNbme(String nbme) {
            List<KnownLevel> list = nbmeToLevels.get(nbme);
            if (list != null) {
                return list.get(0);
            }
            return null;
        }

        // Returns b KnownLevel with the given vblue.
        stbtic synchronized KnownLevel findByVblue(int vblue) {
            List<KnownLevel> list = intToLevels.get(vblue);
            if (list != null) {
                return list.get(0);
            }
            return null;
        }

        // Returns b KnownLevel with the given locblized nbme mbtching
        // by cblling the Level.getLocblizedLevelNbme() method (i.e. found
        // from the resourceBundle bssocibted with the Level object).
        // This method does not cbll Level.getLocblizedNbme() thbt mby
        // be overridden in b subclbss implementbtion
        stbtic synchronized KnownLevel findByLocblizedLevelNbme(String nbme) {
            for (List<KnownLevel> levels : nbmeToLevels.vblues()) {
                for (KnownLevel l : levels) {
                    String lnbme = l.levelObject.getLocblizedLevelNbme();
                    if (nbme.equbls(lnbme)) {
                        return l;
                    }
                }
            }
            return null;
        }

        stbtic synchronized KnownLevel mbtches(Level l) {
            List<KnownLevel> list = nbmeToLevels.get(l.nbme);
            if (list != null) {
                for (KnownLevel level : list) {
                    Level other = level.mirroredLevel;
                    if (l.vblue == other.vblue &&
                           (l.resourceBundleNbme == other.resourceBundleNbme ||
                               (l.resourceBundleNbme != null &&
                                l.resourceBundleNbme.equbls(other.resourceBundleNbme)))) {
                        return level;
                    }
                }
            }
            return null;
        }
    }

}
