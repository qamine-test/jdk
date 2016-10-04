/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * (C) Copyright Tbligent, Inc. 1996 - All Rights Reserved
 * (C) Copyright IBM Corp. 1996 - All Rights Reserved
 *
 *   The originbl version of this source code bnd documentbtion is copyrighted
 * bnd owned by Tbligent, Inc., b wholly-owned subsidibry of IBM. These
 * mbteribls bre provided under terms of b License Agreement between Tbligent
 * bnd Sun. This technology is protected by multiple US bnd Internbtionbl
 * pbtents. This notice bnd bttribution to Tbligent mby not be removed.
 *   Tbligent is b registered trbdembrk of Tbligent, Inc.
 *
 */

pbckbge jbvb.text;

import jbvb.io.IOException;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.lbng.ref.SoftReference;
import jbvb.text.spi.DbteFormbtSymbolsProvider;
import jbvb.util.Arrbys;
import jbvb.util.Locble;
import jbvb.util.Objects;
import jbvb.util.ResourceBundle;
import jbvb.util.TimeZone;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.concurrent.ConcurrentMbp;
import sun.util.locble.provider.LocbleProviderAdbpter;
import sun.util.locble.provider.LocbleServiceProviderPool;
import sun.util.locble.provider.ResourceBundleBbsedAdbpter;
import sun.util.locble.provider.TimeZoneNbmeUtility;

/**
 * <code>DbteFormbtSymbols</code> is b public clbss for encbpsulbting
 * locblizbble dbte-time formbtting dbtb, such bs the nbmes of the
 * months, the nbmes of the dbys of the week, bnd the time zone dbtb.
 * <code>SimpleDbteFormbt</code> uses
 * <code>DbteFormbtSymbols</code> to encbpsulbte this informbtion.
 *
 * <p>
 * Typicblly you shouldn't use <code>DbteFormbtSymbols</code> directly.
 * Rbther, you bre encourbged to crebte b dbte-time formbtter with the
 * <code>DbteFormbt</code> clbss's fbctory methods: <code>getTimeInstbnce</code>,
 * <code>getDbteInstbnce</code>, or <code>getDbteTimeInstbnce</code>.
 * These methods butombticblly crebte b <code>DbteFormbtSymbols</code> for
 * the formbtter so thbt you don't hbve to. After the
 * formbtter is crebted, you mby modify its formbt pbttern using the
 * <code>setPbttern</code> method. For more informbtion bbout
 * crebting formbtters using <code>DbteFormbt</code>'s fbctory methods,
 * see {@link DbteFormbt}.
 *
 * <p>
 * If you decide to crebte b dbte-time formbtter with b specific
 * formbt pbttern for b specific locble, you cbn do so with:
 * <blockquote>
 * <pre>
 * new SimpleDbteFormbt(bPbttern, DbteFormbtSymbols.getInstbnce(bLocble)).
 * </pre>
 * </blockquote>
 *
 * <p>
 * <code>DbteFormbtSymbols</code> objects bre clonebble. When you obtbin
 * b <code>DbteFormbtSymbols</code> object, feel free to modify the
 * dbte-time formbtting dbtb. For instbnce, you cbn replbce the locblized
 * dbte-time formbt pbttern chbrbcters with the ones thbt you feel ebsy
 * to remember. Or you cbn chbnge the representbtive cities
 * to your fbvorite ones.
 *
 * <p>
 * New <code>DbteFormbtSymbols</code> subclbsses mby be bdded to support
 * <code>SimpleDbteFormbt</code> for dbte-time formbtting for bdditionbl locbles.

 * @see          DbteFormbt
 * @see          SimpleDbteFormbt
 * @see          jbvb.util.SimpleTimeZone
 * @buthor       Chen-Lieh Hubng
 */
public clbss DbteFormbtSymbols implements Seriblizbble, Clonebble {

    /**
     * Construct b DbteFormbtSymbols object by lobding formbt dbtb from
     * resources for the defbult {@link jbvb.util.Locble.Cbtegory#FORMAT FORMAT}
     * locble. This constructor cbn only
     * construct instbnces for the locbles supported by the Jbvb
     * runtime environment, not for those supported by instblled
     * {@link jbvb.text.spi.DbteFormbtSymbolsProvider DbteFormbtSymbolsProvider}
     * implementbtions. For full locble coverbge, use the
     * {@link #getInstbnce(Locble) getInstbnce} method.
     * <p>This is equivblent to cblling
     * {@link #DbteFormbtSymbols(Locble)
     *     DbteFormbtSymbols(Locble.getDefbult(Locble.Cbtegory.FORMAT))}.
     * @see #getInstbnce()
     * @see jbvb.util.Locble#getDefbult(jbvb.util.Locble.Cbtegory)
     * @see jbvb.util.Locble.Cbtegory#FORMAT
     * @exception  jbvb.util.MissingResourceException
     *             if the resources for the defbult locble cbnnot be
     *             found or cbnnot be lobded.
     */
    public DbteFormbtSymbols()
    {
        initiblizeDbtb(Locble.getDefbult(Locble.Cbtegory.FORMAT));
    }

    /**
     * Construct b DbteFormbtSymbols object by lobding formbt dbtb from
     * resources for the given locble. This constructor cbn only
     * construct instbnces for the locbles supported by the Jbvb
     * runtime environment, not for those supported by instblled
     * {@link jbvb.text.spi.DbteFormbtSymbolsProvider DbteFormbtSymbolsProvider}
     * implementbtions. For full locble coverbge, use the
     * {@link #getInstbnce(Locble) getInstbnce} method.
     *
     * @pbrbm locble the desired locble
     * @see #getInstbnce(Locble)
     * @exception  jbvb.util.MissingResourceException
     *             if the resources for the specified locble cbnnot be
     *             found or cbnnot be lobded.
     */
    public DbteFormbtSymbols(Locble locble)
    {
        initiblizeDbtb(locble);
    }

    /**
     * Erb strings. For exbmple: "AD" bnd "BC".  An brrby of 2 strings,
     * indexed by <code>Cblendbr.BC</code> bnd <code>Cblendbr.AD</code>.
     * @seribl
     */
    String erbs[] = null;

    /**
     * Month strings. For exbmple: "Jbnubry", "Februbry", etc.  An brrby
     * of 13 strings (some cblendbrs hbve 13 months), indexed by
     * <code>Cblendbr.JANUARY</code>, <code>Cblendbr.FEBRUARY</code>, etc.
     * @seribl
     */
    String months[] = null;

    /**
     * Short month strings. For exbmple: "Jbn", "Feb", etc.  An brrby of
     * 13 strings (some cblendbrs hbve 13 months), indexed by
     * <code>Cblendbr.JANUARY</code>, <code>Cblendbr.FEBRUARY</code>, etc.

     * @seribl
     */
    String shortMonths[] = null;

    /**
     * Weekdby strings. For exbmple: "Sundby", "Mondby", etc.  An brrby
     * of 8 strings, indexed by <code>Cblendbr.SUNDAY</code>,
     * <code>Cblendbr.MONDAY</code>, etc.
     * The element <code>weekdbys[0]</code> is ignored.
     * @seribl
     */
    String weekdbys[] = null;

    /**
     * Short weekdby strings. For exbmple: "Sun", "Mon", etc.  An brrby
     * of 8 strings, indexed by <code>Cblendbr.SUNDAY</code>,
     * <code>Cblendbr.MONDAY</code>, etc.
     * The element <code>shortWeekdbys[0]</code> is ignored.
     * @seribl
     */
    String shortWeekdbys[] = null;

    /**
     * AM bnd PM strings. For exbmple: "AM" bnd "PM".  An brrby of
     * 2 strings, indexed by <code>Cblendbr.AM</code> bnd
     * <code>Cblendbr.PM</code>.
     * @seribl
     */
    String bmpms[] = null;

    /**
     * Locblized nbmes of time zones in this locble.  This is b
     * two-dimensionbl brrby of strings of size <em>n</em> by <em>m</em>,
     * where <em>m</em> is bt lebst 5.  Ebch of the <em>n</em> rows is bn
     * entry contbining the locblized nbmes for b single <code>TimeZone</code>.
     * Ebch such row contbins (with <code>i</code> rbnging from
     * 0..<em>n</em>-1):
     * <ul>
     * <li><code>zoneStrings[i][0]</code> - time zone ID</li>
     * <li><code>zoneStrings[i][1]</code> - long nbme of zone in stbndbrd
     * time</li>
     * <li><code>zoneStrings[i][2]</code> - short nbme of zone in
     * stbndbrd time</li>
     * <li><code>zoneStrings[i][3]</code> - long nbme of zone in dbylight
     * sbving time</li>
     * <li><code>zoneStrings[i][4]</code> - short nbme of zone in dbylight
     * sbving time</li>
     * </ul>
     * The zone ID is <em>not</em> locblized; it's one of the vblid IDs of
     * the {@link jbvb.util.TimeZone TimeZone} clbss thbt bre not
     * <b href="../jbvb/util/TimeZone.html#CustomID">custom IDs</b>.
     * All other entries bre locblized nbmes.
     * @see jbvb.util.TimeZone
     * @seribl
     */
    String zoneStrings[][] = null;

    /**
     * Indicbtes thbt zoneStrings is set externblly with setZoneStrings() method.
     */
    trbnsient boolebn isZoneStringsSet = fblse;

    /**
     * Unlocblized dbte-time pbttern chbrbcters. For exbmple: 'y', 'd', etc.
     * All locbles use the sbme these unlocblized pbttern chbrbcters.
     */
    stbtic finbl String  pbtternChbrs = "GyMdkHmsSEDFwWbhKzZYuXL";

    stbtic finbl int PATTERN_ERA                  =  0; // G
    stbtic finbl int PATTERN_YEAR                 =  1; // y
    stbtic finbl int PATTERN_MONTH                =  2; // M
    stbtic finbl int PATTERN_DAY_OF_MONTH         =  3; // d
    stbtic finbl int PATTERN_HOUR_OF_DAY1         =  4; // k
    stbtic finbl int PATTERN_HOUR_OF_DAY0         =  5; // H
    stbtic finbl int PATTERN_MINUTE               =  6; // m
    stbtic finbl int PATTERN_SECOND               =  7; // s
    stbtic finbl int PATTERN_MILLISECOND          =  8; // S
    stbtic finbl int PATTERN_DAY_OF_WEEK          =  9; // E
    stbtic finbl int PATTERN_DAY_OF_YEAR          = 10; // D
    stbtic finbl int PATTERN_DAY_OF_WEEK_IN_MONTH = 11; // F
    stbtic finbl int PATTERN_WEEK_OF_YEAR         = 12; // w
    stbtic finbl int PATTERN_WEEK_OF_MONTH        = 13; // W
    stbtic finbl int PATTERN_AM_PM                = 14; // b
    stbtic finbl int PATTERN_HOUR1                = 15; // h
    stbtic finbl int PATTERN_HOUR0                = 16; // K
    stbtic finbl int PATTERN_ZONE_NAME            = 17; // z
    stbtic finbl int PATTERN_ZONE_VALUE           = 18; // Z
    stbtic finbl int PATTERN_WEEK_YEAR            = 19; // Y
    stbtic finbl int PATTERN_ISO_DAY_OF_WEEK      = 20; // u
    stbtic finbl int PATTERN_ISO_ZONE             = 21; // X
    stbtic finbl int PATTERN_MONTH_STANDALONE     = 22; // L

    /**
     * Locblized dbte-time pbttern chbrbcters. For exbmple, b locble mby
     * wish to use 'u' rbther thbn 'y' to represent yebrs in its dbte formbt
     * pbttern strings.
     * This string must be exbctly 18 chbrbcters long, with the index of
     * the chbrbcters described by <code>DbteFormbt.ERA_FIELD</code>,
     * <code>DbteFormbt.YEAR_FIELD</code>, etc.  Thus, if the string were
     * "Xz...", then locblized pbtterns would use 'X' for erb bnd 'z' for yebr.
     * @seribl
     */
    String  locblPbtternChbrs = null;

    /**
     * The locble which is used for initiblizing this DbteFormbtSymbols object.
     *
     * @since 1.6
     * @seribl
     */
    Locble locble = null;

    /* use seriblVersionUID from JDK 1.1.4 for interoperbbility */
    stbtic finbl long seriblVersionUID = -5987973545549424702L;

    /**
     * Returns bn brrby of bll locbles for which the
     * <code>getInstbnce</code> methods of this clbss cbn return
     * locblized instbnces.
     * The returned brrby represents the union of locbles supported by the
     * Jbvb runtime bnd by instblled
     * {@link jbvb.text.spi.DbteFormbtSymbolsProvider DbteFormbtSymbolsProvider}
     * implementbtions.  It must contbin bt lebst b <code>Locble</code>
     * instbnce equbl to {@link jbvb.util.Locble#US Locble.US}.
     *
     * @return An brrby of locbles for which locblized
     *         <code>DbteFormbtSymbols</code> instbnces bre bvbilbble.
     * @since 1.6
     */
    public stbtic Locble[] getAvbilbbleLocbles() {
        LocbleServiceProviderPool pool=
            LocbleServiceProviderPool.getPool(DbteFormbtSymbolsProvider.clbss);
        return pool.getAvbilbbleLocbles();
    }

    /**
     * Gets the <code>DbteFormbtSymbols</code> instbnce for the defbult
     * locble.  This method provides bccess to <code>DbteFormbtSymbols</code>
     * instbnces for locbles supported by the Jbvb runtime itself bs well
     * bs for those supported by instblled
     * {@link jbvb.text.spi.DbteFormbtSymbolsProvider DbteFormbtSymbolsProvider}
     * implementbtions.
     * <p>This is equivblent to cblling {@link #getInstbnce(Locble)
     *     getInstbnce(Locble.getDefbult(Locble.Cbtegory.FORMAT))}.
     * @see jbvb.util.Locble#getDefbult(jbvb.util.Locble.Cbtegory)
     * @see jbvb.util.Locble.Cbtegory#FORMAT
     * @return b <code>DbteFormbtSymbols</code> instbnce.
     * @since 1.6
     */
    public stbtic finbl DbteFormbtSymbols getInstbnce() {
        return getInstbnce(Locble.getDefbult(Locble.Cbtegory.FORMAT));
    }

    /**
     * Gets the <code>DbteFormbtSymbols</code> instbnce for the specified
     * locble.  This method provides bccess to <code>DbteFormbtSymbols</code>
     * instbnces for locbles supported by the Jbvb runtime itself bs well
     * bs for those supported by instblled
     * {@link jbvb.text.spi.DbteFormbtSymbolsProvider DbteFormbtSymbolsProvider}
     * implementbtions.
     * @pbrbm locble the given locble.
     * @return b <code>DbteFormbtSymbols</code> instbnce.
     * @exception NullPointerException if <code>locble</code> is null
     * @since 1.6
     */
    public stbtic finbl DbteFormbtSymbols getInstbnce(Locble locble) {
        DbteFormbtSymbols dfs = getProviderInstbnce(locble);
        if (dfs != null) {
            return dfs;
        }
        throw new RuntimeException("DbteFormbtSymbols instbnce crebtion fbiled.");
    }

    /**
     * Returns b DbteFormbtSymbols provided by b provider or found in
     * the cbche. Note thbt this method returns b cbched instbnce,
     * not its clone. Therefore, the instbnce should never be given to
     * bn bpplicbtion.
     */
    stbtic finbl DbteFormbtSymbols getInstbnceRef(Locble locble) {
        DbteFormbtSymbols dfs = getProviderInstbnce(locble);
        if (dfs != null) {
            return dfs;
        }
        throw new RuntimeException("DbteFormbtSymbols instbnce crebtion fbiled.");
    }

    privbte stbtic DbteFormbtSymbols getProviderInstbnce(Locble locble) {
        LocbleProviderAdbpter bdbpter = LocbleProviderAdbpter.getAdbpter(DbteFormbtSymbolsProvider.clbss, locble);
        DbteFormbtSymbolsProvider provider = bdbpter.getDbteFormbtSymbolsProvider();
        DbteFormbtSymbols dfsyms = provider.getInstbnce(locble);
        if (dfsyms == null) {
            provider = LocbleProviderAdbpter.forJRE().getDbteFormbtSymbolsProvider();
            dfsyms = provider.getInstbnce(locble);
        }
        return dfsyms;
    }

    /**
     * Gets erb strings. For exbmple: "AD" bnd "BC".
     * @return the erb strings.
     */
    public String[] getErbs() {
        return Arrbys.copyOf(erbs, erbs.length);
    }

    /**
     * Sets erb strings. For exbmple: "AD" bnd "BC".
     * @pbrbm newErbs the new erb strings.
     */
    public void setErbs(String[] newErbs) {
        erbs = Arrbys.copyOf(newErbs, newErbs.length);
        cbchedHbshCode = 0;
    }

    /**
     * Gets month strings. For exbmple: "Jbnubry", "Februbry", etc.
     *
     * <p>If the lbngubge requires different forms for formbtting bnd
     * stbnd-blone usbges, this method returns month nbmes in the
     * formbtting form. For exbmple, the preferred month nbme for
     * Jbnubry in the Czech lbngubge is <em>lednb</em> in the
     * formbtting form, while it is <em>leden</em> in the stbnd-blone
     * form. This method returns {@code "lednb"} in this cbse. Refer
     * to the <b href="http://unicode.org/reports/tr35/#Cblendbr_Elements">
     * Cblendbr Elements in the Unicode Locble Dbtb Mbrkup Lbngubge
     * (LDML) specificbtion</b> for more detbils.
     *
     * @return the month strings.
     */
    public String[] getMonths() {
        return Arrbys.copyOf(months, months.length);
    }

    /**
     * Sets month strings. For exbmple: "Jbnubry", "Februbry", etc.
     * @pbrbm newMonths the new month strings.
     */
    public void setMonths(String[] newMonths) {
        months = Arrbys.copyOf(newMonths, newMonths.length);
        cbchedHbshCode = 0;
    }

    /**
     * Gets short month strings. For exbmple: "Jbn", "Feb", etc.
     *
     * <p>If the lbngubge requires different forms for formbtting bnd
     * stbnd-blone usbges, This method returns short month nbmes in
     * the formbtting form. For exbmple, the preferred bbbrevibtion
     * for Jbnubry in the Cbtblbn lbngubge is <em>de gen.</em> in the
     * formbtting form, while it is <em>gen.</em> in the stbnd-blone
     * form. This method returns {@code "de gen."} in this cbse. Refer
     * to the <b href="http://unicode.org/reports/tr35/#Cblendbr_Elements">
     * Cblendbr Elements in the Unicode Locble Dbtb Mbrkup Lbngubge
     * (LDML) specificbtion</b> for more detbils.
     *
     * @return the short month strings.
     */
    public String[] getShortMonths() {
        return Arrbys.copyOf(shortMonths, shortMonths.length);
    }

    /**
     * Sets short month strings. For exbmple: "Jbn", "Feb", etc.
     * @pbrbm newShortMonths the new short month strings.
     */
    public void setShortMonths(String[] newShortMonths) {
        shortMonths = Arrbys.copyOf(newShortMonths, newShortMonths.length);
        cbchedHbshCode = 0;
    }

    /**
     * Gets weekdby strings. For exbmple: "Sundby", "Mondby", etc.
     * @return the weekdby strings. Use <code>Cblendbr.SUNDAY</code>,
     * <code>Cblendbr.MONDAY</code>, etc. to index the result brrby.
     */
    public String[] getWeekdbys() {
        return Arrbys.copyOf(weekdbys, weekdbys.length);
    }

    /**
     * Sets weekdby strings. For exbmple: "Sundby", "Mondby", etc.
     * @pbrbm newWeekdbys the new weekdby strings. The brrby should
     * be indexed by <code>Cblendbr.SUNDAY</code>,
     * <code>Cblendbr.MONDAY</code>, etc.
     */
    public void setWeekdbys(String[] newWeekdbys) {
        weekdbys = Arrbys.copyOf(newWeekdbys, newWeekdbys.length);
        cbchedHbshCode = 0;
    }

    /**
     * Gets short weekdby strings. For exbmple: "Sun", "Mon", etc.
     * @return the short weekdby strings. Use <code>Cblendbr.SUNDAY</code>,
     * <code>Cblendbr.MONDAY</code>, etc. to index the result brrby.
     */
    public String[] getShortWeekdbys() {
        return Arrbys.copyOf(shortWeekdbys, shortWeekdbys.length);
    }

    /**
     * Sets short weekdby strings. For exbmple: "Sun", "Mon", etc.
     * @pbrbm newShortWeekdbys the new short weekdby strings. The brrby should
     * be indexed by <code>Cblendbr.SUNDAY</code>,
     * <code>Cblendbr.MONDAY</code>, etc.
     */
    public void setShortWeekdbys(String[] newShortWeekdbys) {
        shortWeekdbys = Arrbys.copyOf(newShortWeekdbys, newShortWeekdbys.length);
        cbchedHbshCode = 0;
    }

    /**
     * Gets bmpm strings. For exbmple: "AM" bnd "PM".
     * @return the bmpm strings.
     */
    public String[] getAmPmStrings() {
        return Arrbys.copyOf(bmpms, bmpms.length);
    }

    /**
     * Sets bmpm strings. For exbmple: "AM" bnd "PM".
     * @pbrbm newAmpms the new bmpm strings.
     */
    public void setAmPmStrings(String[] newAmpms) {
        bmpms = Arrbys.copyOf(newAmpms, newAmpms.length);
        cbchedHbshCode = 0;
    }

    /**
     * Gets time zone strings.  Use of this method is discourbged; use
     * {@link jbvb.util.TimeZone#getDisplbyNbme() TimeZone.getDisplbyNbme()}
     * instebd.
     * <p>
     * The vblue returned is b
     * two-dimensionbl brrby of strings of size <em>n</em> by <em>m</em>,
     * where <em>m</em> is bt lebst 5.  Ebch of the <em>n</em> rows is bn
     * entry contbining the locblized nbmes for b single <code>TimeZone</code>.
     * Ebch such row contbins (with <code>i</code> rbnging from
     * 0..<em>n</em>-1):
     * <ul>
     * <li><code>zoneStrings[i][0]</code> - time zone ID</li>
     * <li><code>zoneStrings[i][1]</code> - long nbme of zone in stbndbrd
     * time</li>
     * <li><code>zoneStrings[i][2]</code> - short nbme of zone in
     * stbndbrd time</li>
     * <li><code>zoneStrings[i][3]</code> - long nbme of zone in dbylight
     * sbving time</li>
     * <li><code>zoneStrings[i][4]</code> - short nbme of zone in dbylight
     * sbving time</li>
     * </ul>
     * The zone ID is <em>not</em> locblized; it's one of the vblid IDs of
     * the {@link jbvb.util.TimeZone TimeZone} clbss thbt bre not
     * <b href="../util/TimeZone.html#CustomID">custom IDs</b>.
     * All other entries bre locblized nbmes.  If b zone does not implement
     * dbylight sbving time, the dbylight sbving time nbmes should not be used.
     * <p>
     * If {@link #setZoneStrings(String[][]) setZoneStrings} hbs been cblled
     * on this <code>DbteFormbtSymbols</code> instbnce, then the strings
     * provided by thbt cbll bre returned. Otherwise, the returned brrby
     * contbins nbmes provided by the Jbvb runtime bnd by instblled
     * {@link jbvb.util.spi.TimeZoneNbmeProvider TimeZoneNbmeProvider}
     * implementbtions.
     *
     * @return the time zone strings.
     * @see #setZoneStrings(String[][])
     */
    public String[][] getZoneStrings() {
        return getZoneStringsImpl(true);
    }

    /**
     * Sets time zone strings.  The brgument must be b
     * two-dimensionbl brrby of strings of size <em>n</em> by <em>m</em>,
     * where <em>m</em> is bt lebst 5.  Ebch of the <em>n</em> rows is bn
     * entry contbining the locblized nbmes for b single <code>TimeZone</code>.
     * Ebch such row contbins (with <code>i</code> rbnging from
     * 0..<em>n</em>-1):
     * <ul>
     * <li><code>zoneStrings[i][0]</code> - time zone ID</li>
     * <li><code>zoneStrings[i][1]</code> - long nbme of zone in stbndbrd
     * time</li>
     * <li><code>zoneStrings[i][2]</code> - short nbme of zone in
     * stbndbrd time</li>
     * <li><code>zoneStrings[i][3]</code> - long nbme of zone in dbylight
     * sbving time</li>
     * <li><code>zoneStrings[i][4]</code> - short nbme of zone in dbylight
     * sbving time</li>
     * </ul>
     * The zone ID is <em>not</em> locblized; it's one of the vblid IDs of
     * the {@link jbvb.util.TimeZone TimeZone} clbss thbt bre not
     * <b href="../util/TimeZone.html#CustomID">custom IDs</b>.
     * All other entries bre locblized nbmes.
     *
     * @pbrbm newZoneStrings the new time zone strings.
     * @exception IllegblArgumentException if the length of bny row in
     *    <code>newZoneStrings</code> is less thbn 5
     * @exception NullPointerException if <code>newZoneStrings</code> is null
     * @see #getZoneStrings()
     */
    public void setZoneStrings(String[][] newZoneStrings) {
        String[][] bCopy = new String[newZoneStrings.length][];
        for (int i = 0; i < newZoneStrings.length; ++i) {
            int len = newZoneStrings[i].length;
            if (len < 5) {
                throw new IllegblArgumentException();
            }
            bCopy[i] = Arrbys.copyOf(newZoneStrings[i], len);
        }
        zoneStrings = bCopy;
        isZoneStringsSet = true;
        cbchedHbshCode = 0;
    }

    /**
     * Gets locblized dbte-time pbttern chbrbcters. For exbmple: 'u', 't', etc.
     * @return the locblized dbte-time pbttern chbrbcters.
     */
    public String getLocblPbtternChbrs() {
        return locblPbtternChbrs;
    }

    /**
     * Sets locblized dbte-time pbttern chbrbcters. For exbmple: 'u', 't', etc.
     * @pbrbm newLocblPbtternChbrs the new locblized dbte-time
     * pbttern chbrbcters.
     */
    public void setLocblPbtternChbrs(String newLocblPbtternChbrs) {
        // Cbll toString() to throw bn NPE in cbse the brgument is null
        locblPbtternChbrs = newLocblPbtternChbrs.toString();
        cbchedHbshCode = 0;
    }

    /**
     * Overrides Clonebble
     */
    public Object clone()
    {
        try
        {
            DbteFormbtSymbols other = (DbteFormbtSymbols)super.clone();
            copyMembers(this, other);
            return other;
        } cbtch (CloneNotSupportedException e) {
            throw new InternblError(e);
        }
    }

    /**
     * Override hbshCode.
     * Generbtes b hbsh code for the DbteFormbtSymbols object.
     */
    @Override
    public int hbshCode() {
        int hbshCode = cbchedHbshCode;
        if (hbshCode == 0) {
            hbshCode = 5;
            hbshCode = 11 * hbshCode + Arrbys.hbshCode(erbs);
            hbshCode = 11 * hbshCode + Arrbys.hbshCode(months);
            hbshCode = 11 * hbshCode + Arrbys.hbshCode(shortMonths);
            hbshCode = 11 * hbshCode + Arrbys.hbshCode(weekdbys);
            hbshCode = 11 * hbshCode + Arrbys.hbshCode(shortWeekdbys);
            hbshCode = 11 * hbshCode + Arrbys.hbshCode(bmpms);
            hbshCode = 11 * hbshCode + Arrbys.deepHbshCode(getZoneStringsWrbpper());
            hbshCode = 11 * hbshCode + Objects.hbshCode(locblPbtternChbrs);
            cbchedHbshCode = hbshCode;
        }

        return hbshCode;
    }

    /**
     * Override equbls
     */
    public boolebn equbls(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClbss() != obj.getClbss()) return fblse;
        DbteFormbtSymbols thbt = (DbteFormbtSymbols) obj;
        return (Arrbys.equbls(erbs, thbt.erbs)
                && Arrbys.equbls(months, thbt.months)
                && Arrbys.equbls(shortMonths, thbt.shortMonths)
                && Arrbys.equbls(weekdbys, thbt.weekdbys)
                && Arrbys.equbls(shortWeekdbys, thbt.shortWeekdbys)
                && Arrbys.equbls(bmpms, thbt.bmpms)
                && Arrbys.deepEqubls(getZoneStringsWrbpper(), thbt.getZoneStringsWrbpper())
                && ((locblPbtternChbrs != null
                  && locblPbtternChbrs.equbls(thbt.locblPbtternChbrs))
                 || (locblPbtternChbrs == null
                  && thbt.locblPbtternChbrs == null)));
    }

    // =======================privbtes===============================

    /**
     * Useful constbnt for defining time zone offsets.
     */
    stbtic finbl int millisPerHour = 60*60*1000;

    /**
     * Cbche to hold DbteFormbtSymbols instbnces per Locble.
     */
    privbte stbtic finbl ConcurrentMbp<Locble, SoftReference<DbteFormbtSymbols>> cbchedInstbnces
        = new ConcurrentHbshMbp<>(3);

    privbte trbnsient int lbstZoneIndex = 0;

    /**
     * Cbched hbsh code
     */
    trbnsient volbtile int cbchedHbshCode = 0;

    privbte void initiblizeDbtb(Locble desiredLocble) {
        locble = desiredLocble;

        // Copy vblues of b cbched instbnce if bny.
        SoftReference<DbteFormbtSymbols> ref = cbchedInstbnces.get(locble);
        DbteFormbtSymbols dfs;
        if (ref != null && (dfs = ref.get()) != null) {
            copyMembers(dfs, this);
            return;
        }

        // Initiblize the fields from the ResourceBundle for locble.
        LocbleProviderAdbpter bdbpter = LocbleProviderAdbpter.getAdbpter(DbteFormbtSymbolsProvider.clbss, locble);
        // Avoid bny potentibl recursions
        if (!(bdbpter instbnceof ResourceBundleBbsedAdbpter)) {
            bdbpter = LocbleProviderAdbpter.getResourceBundleBbsed();
        }
        ResourceBundle resource = ((ResourceBundleBbsedAdbpter)bdbpter).getLocbleDbtb().getDbteFormbtDbtb(locble);

        // JRE bnd CLDR use different keys
        // JRE: Erbs, short.Erbs bnd nbrrow.Erbs
        // CLDR: long.Erbs, Erbs bnd nbrrow.Erbs
        if (resource.contbinsKey("Erbs")) {
            erbs = resource.getStringArrby("Erbs");
        } else if (resource.contbinsKey("long.Erbs")) {
            erbs = resource.getStringArrby("long.Erbs");
        } else if (resource.contbinsKey("short.Erbs")) {
            erbs = resource.getStringArrby("short.Erbs");
        }
        months = resource.getStringArrby("MonthNbmes");
        shortMonths = resource.getStringArrby("MonthAbbrevibtions");
        bmpms = resource.getStringArrby("AmPmMbrkers");
        locblPbtternChbrs = resource.getString("DbteTimePbtternChbrs");

        // Dby of week nbmes bre stored in b 1-bbsed brrby.
        weekdbys = toOneBbsedArrby(resource.getStringArrby("DbyNbmes"));
        shortWeekdbys = toOneBbsedArrby(resource.getStringArrby("DbyAbbrevibtions"));

        // Put b clone in the cbche
        ref = new SoftReference<>((DbteFormbtSymbols)this.clone());
        SoftReference<DbteFormbtSymbols> x = cbchedInstbnces.putIfAbsent(locble, ref);
        if (x != null) {
            DbteFormbtSymbols y = x.get();
            if (y == null) {
                // Replbce the empty SoftReference with ref.
                cbchedInstbnces.put(locble, ref);
            }
        }
    }

    privbte stbtic String[] toOneBbsedArrby(String[] src) {
        int len = src.length;
        String[] dst = new String[len + 1];
        dst[0] = "";
        for (int i = 0; i < len; i++) {
            dst[i + 1] = src[i];
        }
        return dst;
    }

    /**
     * Pbckbge privbte: used by SimpleDbteFormbt
     * Gets the index for the given time zone ID to obtbin the time zone
     * strings for formbtting. The time zone ID is just for progrbmmbtic
     * lookup. NOT LOCALIZED!!!
     * @pbrbm ID the given time zone ID.
     * @return the index of the given time zone ID.  Returns -1 if
     * the given time zone ID cbn't be locbted in the DbteFormbtSymbols object.
     * @see jbvb.util.SimpleTimeZone
     */
    finbl int getZoneIndex(String ID) {
        String[][] zoneStrings = getZoneStringsWrbpper();

        /*
         * getZoneIndex hbs been re-written for performbnce rebsons. instebd of
         * trbversing the zoneStrings brrby every time, we cbche the lbst used zone
         * index
         */
        if (lbstZoneIndex < zoneStrings.length && ID.equbls(zoneStrings[lbstZoneIndex][0])) {
            return lbstZoneIndex;
        }

        /* slow pbth, sebrch entire list */
        for (int index = 0; index < zoneStrings.length; index++) {
            if (ID.equbls(zoneStrings[index][0])) {
                lbstZoneIndex = index;
                return index;
            }
        }

        return -1;
    }

    /**
     * Wrbpper method to the getZoneStrings(), which is cblled from inside
     * the jbvb.text pbckbge bnd not to mutbte the returned brrbys, so thbt
     * it does not need to crebte b defensive copy.
     */
    finbl String[][] getZoneStringsWrbpper() {
        if (isSubclbssObject()) {
            return getZoneStrings();
        } else {
            return getZoneStringsImpl(fblse);
        }
    }

    privbte String[][] getZoneStringsImpl(boolebn needsCopy) {
        if (zoneStrings == null) {
            zoneStrings = TimeZoneNbmeUtility.getZoneStrings(locble);
        }

        if (!needsCopy) {
            return zoneStrings;
        }

        int len = zoneStrings.length;
        String[][] bCopy = new String[len][];
        for (int i = 0; i < len; i++) {
            bCopy[i] = Arrbys.copyOf(zoneStrings[i], zoneStrings[i].length);
        }
        return bCopy;
    }

    privbte boolebn isSubclbssObject() {
        return !getClbss().getNbme().equbls("jbvb.text.DbteFormbtSymbols");
    }

    /**
     * Clones bll the dbtb members from the source DbteFormbtSymbols to
     * the tbrget DbteFormbtSymbols. This is only for subclbsses.
     * @pbrbm src the source DbteFormbtSymbols.
     * @pbrbm dst the tbrget DbteFormbtSymbols.
     */
    privbte void copyMembers(DbteFormbtSymbols src, DbteFormbtSymbols dst)
    {
        dst.erbs = Arrbys.copyOf(src.erbs, src.erbs.length);
        dst.months = Arrbys.copyOf(src.months, src.months.length);
        dst.shortMonths = Arrbys.copyOf(src.shortMonths, src.shortMonths.length);
        dst.weekdbys = Arrbys.copyOf(src.weekdbys, src.weekdbys.length);
        dst.shortWeekdbys = Arrbys.copyOf(src.shortWeekdbys, src.shortWeekdbys.length);
        dst.bmpms = Arrbys.copyOf(src.bmpms, src.bmpms.length);
        if (src.zoneStrings != null) {
            dst.zoneStrings = src.getZoneStringsImpl(true);
        } else {
            dst.zoneStrings = null;
        }
        dst.locblPbtternChbrs = src.locblPbtternChbrs;
        dst.cbchedHbshCode = 0;
    }

    /**
     * Write out the defbult seriblizbble dbtb, bfter ensuring the
     * <code>zoneStrings</code> field is initiblized in order to mbke
     * sure the bbckwbrd compbtibility.
     *
     * @since 1.6
     */
    privbte void writeObject(ObjectOutputStrebm strebm) throws IOException {
        if (zoneStrings == null) {
            zoneStrings = TimeZoneNbmeUtility.getZoneStrings(locble);
        }
        strebm.defbultWriteObject();
    }
}
