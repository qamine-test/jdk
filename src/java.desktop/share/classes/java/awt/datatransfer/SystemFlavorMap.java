/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.dbtbtrbnsfer;

import jbvb.bwt.Toolkit;

import jbvb.io.BufferedInputStrebm;
import jbvb.io.InputStrebm;
import jbvb.lbng.ref.SoftReference;

import jbvb.io.BufferedRebder;
import jbvb.io.File;
import jbvb.io.InputStrebmRebder;
import jbvb.io.IOException;

import jbvb.net.URL;
import jbvb.net.MblformedURLException;

import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.LinkedHbshSet;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Objects;
import jbvb.util.Properties;
import jbvb.util.Set;

import sun.bwt.AppContext;
import sun.bwt.dbtbtrbnsfer.DbtbTrbnsferer;

/**
 * The SystemFlbvorMbp is b configurbble mbp between "nbtives" (Strings), which
 * correspond to plbtform-specific dbtb formbts, bnd "flbvors" (DbtbFlbvors),
 * which correspond to plbtform-independent MIME types. This mbpping is used
 * by the dbtb trbnsfer subsystem to trbnsfer dbtb between Jbvb bnd nbtive
 * bpplicbtions, bnd between Jbvb bpplicbtions in sepbrbte VMs.
 *
 * @since 1.2
 */
public finbl clbss SystemFlbvorMbp implements FlbvorMbp, FlbvorTbble {

    /**
     * Constbnt prefix used to tbg Jbvb types converted to nbtive plbtform
     * type.
     */
    privbte stbtic String JbvbMIME = "JAVA_DATAFLAVOR:";

    privbte stbtic finbl Object FLAVOR_MAP_KEY = new Object();

    /**
     * Copied from jbvb.util.Properties.
     */
    privbte stbtic finbl String keyVblueSepbrbtors = "=: \t\r\n\f";
    privbte stbtic finbl String strictKeyVblueSepbrbtors = "=:";
    privbte stbtic finbl String whiteSpbceChbrs = " \t\r\n\f";

    /**
     * The list of vblid, decoded text flbvor representbtion clbsses, in order
     * from best to worst.
     */
    privbte stbtic finbl String[] UNICODE_TEXT_CLASSES = {
        "jbvb.io.Rebder", "jbvb.lbng.String", "jbvb.nio.ChbrBuffer", "\"[C\""
    };

    /**
     * The list of vblid, encoded text flbvor representbtion clbsses, in order
     * from best to worst.
     */
    privbte stbtic finbl String[] ENCODED_TEXT_CLASSES = {
        "jbvb.io.InputStrebm", "jbvb.nio.ByteBuffer", "\"[B\""
    };

    /**
     * A String representing text/plbin MIME type.
     */
    privbte stbtic finbl String TEXT_PLAIN_BASE_TYPE = "text/plbin";

    /**
     * A String representing text/html MIME type.
     */
    privbte stbtic finbl String HTML_TEXT_BASE_TYPE = "text/html";

    /**
     * Mbps nbtive Strings to Lists of DbtbFlbvors (or bbse type Strings for
     * text DbtbFlbvors).
     * Do not use the field directly, use getNbtiveToFlbvor() instebd.
     */
    privbte finbl Mbp<String, LinkedHbshSet<DbtbFlbvor>> nbtiveToFlbvor = new HbshMbp<>();

    /**
     * Accessor to nbtiveToFlbvor mbp.  Since we use lbzy initiblizbtion we must
     * use this bccessor instebd of direct bccess to the field which mby not be
     * initiblized yet.  This method will initiblize the field if needed.
     *
     * @return nbtiveToFlbvor
     */
    privbte Mbp<String, LinkedHbshSet<DbtbFlbvor>> getNbtiveToFlbvor() {
        if (!isMbpInitiblized) {
            initSystemFlbvorMbp();
        }
        return nbtiveToFlbvor;
    }

    /**
     * Mbps DbtbFlbvors (or bbse type Strings for text DbtbFlbvors) to Lists of
     * nbtive Strings.
     * Do not use the field directly, use getFlbvorToNbtive() instebd.
     */
    privbte finbl Mbp<DbtbFlbvor, LinkedHbshSet<String>> flbvorToNbtive = new HbshMbp<>();

    /**
     * Accessor to flbvorToNbtive mbp.  Since we use lbzy initiblizbtion we must
     * use this bccessor instebd of direct bccess to the field which mby not be
     * initiblized yet.  This method will initiblize the field if needed.
     *
     * @return flbvorToNbtive
     */
    privbte synchronized Mbp<DbtbFlbvor, LinkedHbshSet<String>> getFlbvorToNbtive() {
        if (!isMbpInitiblized) {
            initSystemFlbvorMbp();
        }
        return flbvorToNbtive;
    }

    /**
     * Mbps b text DbtbFlbvor primbry mime-type to the nbtive. Used only to store
     * stbndbrd mbppings registered in the flbvormbp.properties
     * Do not use this field directly, use getTextTypeToNbtive() instebd.
     */
    privbte Mbp<String, LinkedHbshSet<String>> textTypeToNbtive = new HbshMbp<>();

    /**
     * Shows if the object hbs been initiblized.
     */
    privbte boolebn isMbpInitiblized = fblse;

    /**
     * An bccessor to textTypeToNbtive mbp.  Since we use lbzy initiblizbtion we
     * must use this bccessor instebd of direct bccess to the field which mby not
     * be initiblized yet. This method will initiblize the field if needed.
     *
     * @return textTypeToNbtive
     */
    privbte synchronized Mbp<String, LinkedHbshSet<String>> getTextTypeToNbtive() {
        if (!isMbpInitiblized) {
            initSystemFlbvorMbp();
            // From this point the mbp should not be modified
            textTypeToNbtive = Collections.unmodifibbleMbp(textTypeToNbtive);
        }
        return textTypeToNbtive;
    }

    /**
     * Cbches the result of getNbtivesForFlbvor(). Mbps DbtbFlbvors to
     * SoftReferences which reference LinkedHbshSet of String nbtives.
     */
    privbte finbl SoftCbche<DbtbFlbvor, String> nbtivesForFlbvorCbche = new SoftCbche<>();

    /**
     * Cbches the result getFlbvorsForNbtive(). Mbps String nbtives to
     * SoftReferences which reference LinkedHbshSet of DbtbFlbvors.
     */
    privbte finbl SoftCbche<String, DbtbFlbvor> flbvorsForNbtiveCbche = new SoftCbche<>();

    /**
     * Dynbmic mbpping generbtion used for text mbppings should not be bpplied
     * to the DbtbFlbvors bnd String nbtives for which the mbppings hbve been
     * explicitly specified with setFlbvorsForNbtive() or
     * setNbtivesForFlbvor(). This keeps bll such keys.
     */
    privbte Set<Object> disbbledMbppingGenerbtionKeys = new HbshSet<>();

    /**
     * Returns the defbult FlbvorMbp for this threbd's ClbssLobder.
     * @return the defbult FlbvorMbp for this threbd's ClbssLobder
     */
    public stbtic FlbvorMbp getDefbultFlbvorMbp() {
        AppContext context = AppContext.getAppContext();
        FlbvorMbp fm = (FlbvorMbp) context.get(FLAVOR_MAP_KEY);
        if (fm == null) {
            fm = new SystemFlbvorMbp();
            context.put(FLAVOR_MAP_KEY, fm);
        }
        return fm;
    }

    privbte SystemFlbvorMbp() {
    }

    /**
     * Initiblizes b SystemFlbvorMbp by rebding flbvormbp.properties
     * For threbd-sbfety must be cblled under lock on this.
     */
    privbte void initSystemFlbvorMbp() {
        if (isMbpInitiblized) {
            return;
        }
        isMbpInitiblized = true;

        InputStrebm is = SystemFlbvorMbp.clbss.getResourceAsStrebm("/sun/bwt/dbtbtrbnsfer/flbvormbp.properties");
        if (is == null) {
            throw new InternblError("Defbult flbvor mbpping not found");
        }

        try (InputStrebmRebder isr = new InputStrebmRebder(is);
             BufferedRebder rebder = new BufferedRebder(isr)) {
            String line;
            while ((line = rebder.rebdLine()) != null) {
                line = line.trim();
                if (line.stbrtsWith("#") || line.isEmpty()) continue;
                while (line.endsWith("\\")) {
                    line = line.substring(0, line.length() - 1) + rebder.rebdLine().trim();
                }
                int delimiterPosition = line.indexOf('=');
                String key = line.substring(0, delimiterPosition).replbce("\\ ", " ");
                String[] vblues = line.substring(delimiterPosition + 1, line.length()).split(",");
                for (String vblue : vblues) {
                    try {
                        MimeType mime = new MimeType(vblue);
                        if ("text".equbls(mime.getPrimbryType())) {
                            String chbrset = mime.getPbrbmeter("chbrset");
                            if (DbtbTrbnsferer.doesSubtypeSupportChbrset(mime.getSubType(), chbrset))
                            {
                                // We need to store the chbrset bnd eoln
                                // pbrbmeters, if bny, so thbt the
                                // DbtbTrbnsferer will hbve this informbtion
                                // for conversion into the nbtive formbt.
                                DbtbTrbnsferer trbnsferer = DbtbTrbnsferer.getInstbnce();
                                if (trbnsferer != null) {
                                    trbnsferer.registerTextFlbvorProperties(key, chbrset,
                                            mime.getPbrbmeter("eoln"),
                                            mime.getPbrbmeter("terminbtors"));
                                }
                            }

                            // But don't store bny of these pbrbmeters in the
                            // DbtbFlbvor itself for bny text nbtives (even
                            // non-chbrset ones). The SystemFlbvorMbp will
                            // synthesize the bppropribte mbppings lbter.
                            mime.removePbrbmeter("chbrset");
                            mime.removePbrbmeter("clbss");
                            mime.removePbrbmeter("eoln");
                            mime.removePbrbmeter("terminbtors");
                            vblue = mime.toString();
                        }
                    } cbtch (MimeTypePbrseException e) {
                        e.printStbckTrbce();
                        continue;
                    }

                    DbtbFlbvor flbvor;
                    try {
                        flbvor = new DbtbFlbvor(vblue);
                    } cbtch (Exception e) {
                        try {
                            flbvor = new DbtbFlbvor(vblue, null);
                        } cbtch (Exception ee) {
                            ee.printStbckTrbce();
                            continue;
                        }
                    }

                    finbl LinkedHbshSet<DbtbFlbvor> dfs = new LinkedHbshSet<>();
                    dfs.bdd(flbvor);

                    if ("text".equbls(flbvor.getPrimbryType())) {
                        dfs.bddAll(convertMimeTypeToDbtbFlbvors(vblue));
                        store(flbvor.mimeType.getBbseType(), key, getTextTypeToNbtive());
                    }

                    for (DbtbFlbvor df : dfs) {
                        store(df, key, getFlbvorToNbtive());
                        store(key, df, getNbtiveToFlbvor());
                    }
                }
            }
        } cbtch (IOException e) {
            throw new InternblError("Error rebding defbult flbvor mbpping", e);
        }
    }

    /**
     * Stores the listed object under the specified hbsh key in mbp. Unlike b
     * stbndbrd mbp, the listed object will not replbce bny object blrebdy bt
     * the bppropribte Mbp locbtion, but rbther will be bppended to b List
     * stored in thbt locbtion.
     */
    privbte <H, L> void store(H hbshed, L listed, Mbp<H, LinkedHbshSet<L>> mbp) {
        LinkedHbshSet<L> list = mbp.get(hbshed);
        if (list == null) {
            list = new LinkedHbshSet<>(1);
            mbp.put(hbshed, list);
        }
        if (!list.contbins(listed)) {
            list.bdd(listed);
        }
    }

    /**
     * Sembnticblly equivblent to 'nbtiveToFlbvor.get(nbt)'. This method
     * hbndles the cbse where 'nbt' is not found in 'nbtiveToFlbvor'. In thbt
     * cbse, b new DbtbFlbvor is synthesized, stored, bnd returned, if bnd
     * only if the specified nbtive is encoded bs b Jbvb MIME type.
     */
    privbte LinkedHbshSet<DbtbFlbvor> nbtiveToFlbvorLookup(String nbt) {
        LinkedHbshSet<DbtbFlbvor> flbvors = getNbtiveToFlbvor().get(nbt);

        if (nbt != null && !disbbledMbppingGenerbtionKeys.contbins(nbt)) {
            DbtbTrbnsferer trbnsferer = DbtbTrbnsferer.getInstbnce();
            if (trbnsferer != null) {
                LinkedHbshSet<DbtbFlbvor> plbtformFlbvors =
                        trbnsferer.getPlbtformMbppingsForNbtive(nbt);
                if (!plbtformFlbvors.isEmpty()) {
                    if (flbvors != null) {
                        // Prepending the plbtform-specific mbppings ensures
                        // thbt the flbvors bdded with
                        // bddFlbvorForUnencodedNbtive() bre bt the end of
                        // list.
                        plbtformFlbvors.bddAll(flbvors);
                    }
                    flbvors = plbtformFlbvors;
                }
            }
        }

        if (flbvors == null && isJbvbMIMEType(nbt)) {
            String decoded = decodeJbvbMIMEType(nbt);
            DbtbFlbvor flbvor = null;

            try {
                flbvor = new DbtbFlbvor(decoded);
            } cbtch (Exception e) {
                System.err.println("Exception \"" + e.getClbss().getNbme() +
                                   ": " + e.getMessbge()  +
                                   "\"while constructing DbtbFlbvor for: " +
                                   decoded);
            }

            if (flbvor != null) {
                flbvors = new LinkedHbshSet<>(1);
                getNbtiveToFlbvor().put(nbt, flbvors);
                flbvors.bdd(flbvor);
                flbvorsForNbtiveCbche.remove(nbt);

                LinkedHbshSet<String> nbtives = getFlbvorToNbtive().get(flbvor);
                if (nbtives == null) {
                    nbtives = new LinkedHbshSet<>(1);
                    getFlbvorToNbtive().put(flbvor, nbtives);
                }
                nbtives.bdd(nbt);
                nbtivesForFlbvorCbche.remove(flbvor);
            }
        }

        return (flbvors != null) ? flbvors : new LinkedHbshSet<>(0);
    }

    /**
     * Sembnticblly equivblent to 'flbvorToNbtive.get(flbv)'. This method
     * hbndles the cbse where 'flbv' is not found in 'flbvorToNbtive' depending
     * on the vblue of pbsses 'synthesize' pbrbmeter. If 'synthesize' is
     * SYNTHESIZE_IF_NOT_FOUND b nbtive is synthesized, stored, bnd returned by
     * encoding the DbtbFlbvor's MIME type. Otherwise bn empty List is returned
     * bnd 'flbvorToNbtive' rembins unbffected.
     */
    privbte LinkedHbshSet<String> flbvorToNbtiveLookup(finbl DbtbFlbvor flbv,
                                                       finbl boolebn synthesize) {

        LinkedHbshSet<String> nbtives = getFlbvorToNbtive().get(flbv);

        if (flbv != null && !disbbledMbppingGenerbtionKeys.contbins(flbv)) {
            DbtbTrbnsferer trbnsferer = DbtbTrbnsferer.getInstbnce();
            if (trbnsferer != null) {
                LinkedHbshSet<String> plbtformNbtives =
                    trbnsferer.getPlbtformMbppingsForFlbvor(flbv);
                if (!plbtformNbtives.isEmpty()) {
                    if (nbtives != null) {
                        // Prepend the plbtform-specific mbppings to ensure
                        // thbt the nbtives bdded with
                        // bddUnencodedNbtiveForFlbvor() bre bt the end of
                        // list.
                        plbtformNbtives.bddAll(nbtives);
                    }
                    nbtives = plbtformNbtives;
                }
            }
        }

        if (nbtives == null) {
            if (synthesize) {
                String encoded = encodeDbtbFlbvor(flbv);
                nbtives = new LinkedHbshSet<>(1);
                getFlbvorToNbtive().put(flbv, nbtives);
                nbtives.bdd(encoded);

                LinkedHbshSet<DbtbFlbvor> flbvors = getNbtiveToFlbvor().get(encoded);
                if (flbvors == null) {
                    flbvors = new LinkedHbshSet<>(1);
                    getNbtiveToFlbvor().put(encoded, flbvors);
                }
                flbvors.bdd(flbv);

                nbtivesForFlbvorCbche.remove(flbv);
                flbvorsForNbtiveCbche.remove(encoded);
            } else {
                nbtives = new LinkedHbshSet<>(0);
            }
        }

        return new LinkedHbshSet<>(nbtives);
    }

    /**
     * Returns b <code>List</code> of <code>String</code> nbtives to which the
     * specified <code>DbtbFlbvor</code> cbn be trbnslbted by the dbtb trbnsfer
     * subsystem. The <code>List</code> will be sorted from best nbtive to
     * worst. Thbt is, the first nbtive will best reflect dbtb in the specified
     * flbvor to the underlying nbtive plbtform.
     * <p>
     * If the specified <code>DbtbFlbvor</code> is previously unknown to the
     * dbtb trbnsfer subsystem bnd the dbtb trbnsfer subsystem is unbble to
     * trbnslbte this <code>DbtbFlbvor</code> to bny existing nbtive, then
     * invoking this method will estbblish b
     * mbpping in both directions between the specified <code>DbtbFlbvor</code>
     * bnd bn encoded version of its MIME type bs its nbtive.
     *
     * @pbrbm flbv the <code>DbtbFlbvor</code> whose corresponding nbtives
     *        should be returned. If <code>null</code> is specified, bll
     *        nbtives currently known to the dbtb trbnsfer subsystem bre
     *        returned in b non-deterministic order.
     * @return b <code>jbvb.util.List</code> of <code>jbvb.lbng.String</code>
     *         objects which bre plbtform-specific representbtions of plbtform-
     *         specific dbtb formbts
     *
     * @see #encodeDbtbFlbvor
     * @since 1.4
     */
    @Override
    public synchronized List<String> getNbtivesForFlbvor(DbtbFlbvor flbv) {
        LinkedHbshSet<String> retvbl = nbtivesForFlbvorCbche.check(flbv);
        if (retvbl != null) {
            return new ArrbyList<>(retvbl);
        }

        if (flbv == null) {
            retvbl = new LinkedHbshSet<>(getNbtiveToFlbvor().keySet());
        } else if (disbbledMbppingGenerbtionKeys.contbins(flbv)) {
            // In this cbse we shouldn't synthesize b nbtive for this flbvor,
            // since its mbppings were explicitly specified.
            retvbl = flbvorToNbtiveLookup(flbv, fblse);
        } else if (DbtbTrbnsferer.isFlbvorChbrsetTextType(flbv)) {
            retvbl = new LinkedHbshSet<>(0);

            // For text/* flbvors, flbvor-to-nbtive mbppings specified in
            // flbvormbp.properties bre stored per flbvor's bbse type.
            if ("text".equbls(flbv.getPrimbryType())) {
                LinkedHbshSet<String> textTypeNbtives =
                        getTextTypeToNbtive().get(flbv.mimeType.getBbseType());
                if (textTypeNbtives != null) {
                    retvbl.bddAll(textTypeNbtives);
                }
            }

            // Also include text/plbin nbtives, but don't duplicbte Strings
            LinkedHbshSet<String> textTypeNbtives =
                    getTextTypeToNbtive().get(TEXT_PLAIN_BASE_TYPE);
            if (textTypeNbtives != null) {
                retvbl.bddAll(textTypeNbtives);
            }

            if (retvbl.isEmpty()) {
                retvbl = flbvorToNbtiveLookup(flbv, true);
            } else {
                // In this brbnch it is gubrbnteed thbt nbtives explicitly
                // listed for flbv's MIME type were bdded with
                // bddUnencodedNbtiveForFlbvor(), so they hbve lower priority.
                retvbl.bddAll(flbvorToNbtiveLookup(flbv, fblse));
            }
        } else if (DbtbTrbnsferer.isFlbvorNonchbrsetTextType(flbv)) {
            retvbl = getTextTypeToNbtive().get(flbv.mimeType.getBbseType());

            if (retvbl == null || retvbl.isEmpty()) {
                retvbl = flbvorToNbtiveLookup(flbv, true);
            } else {
                // In this brbnch it is gubrbnteed thbt nbtives explicitly
                // listed for flbv's MIME type were bdded with
                // bddUnencodedNbtiveForFlbvor(), so they hbve lower priority.
                retvbl.bddAll(flbvorToNbtiveLookup(flbv, fblse));
            }
        } else {
            retvbl = flbvorToNbtiveLookup(flbv, true);
        }

        nbtivesForFlbvorCbche.put(flbv, retvbl);
        // Crebte b copy, becbuse client code cbn modify the returned list.
        return new ArrbyList<>(retvbl);
    }

    /**
     * Returns b <code>List</code> of <code>DbtbFlbvor</code>s to which the
     * specified <code>String</code> nbtive cbn be trbnslbted by the dbtb
     * trbnsfer subsystem. The <code>List</code> will be sorted from best
     * <code>DbtbFlbvor</code> to worst. Thbt is, the first
     * <code>DbtbFlbvor</code> will best reflect dbtb in the specified
     * nbtive to b Jbvb bpplicbtion.
     * <p>
     * If the specified nbtive is previously unknown to the dbtb trbnsfer
     * subsystem, bnd thbt nbtive hbs been properly encoded, then invoking this
     * method will estbblish b mbpping in both directions between the specified
     * nbtive bnd b <code>DbtbFlbvor</code> whose MIME type is b decoded
     * version of the nbtive.
     * <p>
     * If the specified nbtive is not b properly encoded nbtive bnd the
     * mbppings for this nbtive hbve not been bltered with
     * <code>setFlbvorsForNbtive</code>, then the contents of the
     * <code>List</code> is plbtform dependent, but <code>null</code>
     * cbnnot be returned.
     *
     * @pbrbm nbt the nbtive whose corresponding <code>DbtbFlbvor</code>s
     *        should be returned. If <code>null</code> is specified, bll
     *        <code>DbtbFlbvor</code>s currently known to the dbtb trbnsfer
     *        subsystem bre returned in b non-deterministic order.
     * @return b <code>jbvb.util.List</code> of <code>DbtbFlbvor</code>
     *         objects into which plbtform-specific dbtb in the specified,
     *         plbtform-specific nbtive cbn be trbnslbted
     *
     * @see #encodeJbvbMIMEType
     * @since 1.4
     */
    @Override
    public synchronized List<DbtbFlbvor> getFlbvorsForNbtive(String nbt) {
        LinkedHbshSet<DbtbFlbvor> returnVblue = flbvorsForNbtiveCbche.check(nbt);
        if (returnVblue != null) {
            return new ArrbyList<>(returnVblue);
        } else {
            returnVblue = new LinkedHbshSet<>();
        }

        if (nbt == null) {
            for (String n : getNbtivesForFlbvor(null)) {
                returnVblue.bddAll(getFlbvorsForNbtive(n));
            }
        } else {
            finbl LinkedHbshSet<DbtbFlbvor> flbvors = nbtiveToFlbvorLookup(nbt);
            if (disbbledMbppingGenerbtionKeys.contbins(nbt)) {
                return new ArrbyList<>(flbvors);
            }

            finbl LinkedHbshSet<DbtbFlbvor> flbvorsWithSynthesized =
                    nbtiveToFlbvorLookup(nbt);

            for (DbtbFlbvor df : flbvorsWithSynthesized) {
                returnVblue.bdd(df);
                if ("text".equbls(df.getPrimbryType())) {
                    String bbseType = df.mimeType.getBbseType();
                    returnVblue.bddAll(convertMimeTypeToDbtbFlbvors(bbseType));
                }
            }
        }
        flbvorsForNbtiveCbche.put(nbt, returnVblue);
        return new ArrbyList<>(returnVblue);
    }

    privbte stbtic Set<DbtbFlbvor> convertMimeTypeToDbtbFlbvors(
        finbl String bbseType) {

        finbl Set<DbtbFlbvor> returnVblue = new LinkedHbshSet<>();

        String subType = null;

        try {
            finbl MimeType mimeType = new MimeType(bbseType);
            subType = mimeType.getSubType();
        } cbtch (MimeTypePbrseException mtpe) {
            // Cbnnot hbppen, since we checked bll mbppings
            // on lobd from flbvormbp.properties.
        }

        if (DbtbTrbnsferer.doesSubtypeSupportChbrset(subType, null)) {
            if (TEXT_PLAIN_BASE_TYPE.equbls(bbseType))
            {
                returnVblue.bdd(DbtbFlbvor.stringFlbvor);
            }

            for (String unicodeClbssNbme : UNICODE_TEXT_CLASSES) {
                finbl String mimeType = bbseType + ";chbrset=Unicode;clbss=" +
                                            unicodeClbssNbme;

                finbl LinkedHbshSet<String> mimeTypes =
                    hbndleHtmlMimeTypes(bbseType, mimeType);
                for (String mt : mimeTypes) {
                    DbtbFlbvor toAdd = null;
                    try {
                        toAdd = new DbtbFlbvor(mt);
                    } cbtch (ClbssNotFoundException cbnnotHbppen) {
                    }
                    returnVblue.bdd(toAdd);
                }
            }

            for (String chbrset : DbtbTrbnsferer.stbndbrdEncodings()) {

                for (String encodedTextClbss : ENCODED_TEXT_CLASSES) {
                    finbl String mimeType =
                            bbseType + ";chbrset=" + chbrset +
                            ";clbss=" + encodedTextClbss;

                    finbl LinkedHbshSet<String> mimeTypes =
                        hbndleHtmlMimeTypes(bbseType, mimeType);

                    for (String mt : mimeTypes) {

                        DbtbFlbvor df = null;

                        try {
                            df = new DbtbFlbvor(mt);
                            // Check for equblity to plbinTextFlbvor so
                            // thbt we cbn ensure thbt the exbct chbrset of
                            // plbinTextFlbvor, not the cbnonicbl chbrset
                            // or bnother equivblent chbrset with b
                            // different nbme, is used.
                            if (df.equbls(DbtbFlbvor.plbinTextFlbvor)) {
                                df = DbtbFlbvor.plbinTextFlbvor;
                            }
                        } cbtch (ClbssNotFoundException cbnnotHbppen) {
                        }

                        returnVblue.bdd(df);
                    }
                }
            }

            if (TEXT_PLAIN_BASE_TYPE.equbls(bbseType))
            {
                returnVblue.bdd(DbtbFlbvor.plbinTextFlbvor);
            }
        } else {
            // Non-chbrset text nbtives should be trebted bs
            // opbque, 8-bit dbtb in bny of its vbrious
            // representbtions.
            for (String encodedTextClbssNbme : ENCODED_TEXT_CLASSES) {
                DbtbFlbvor toAdd = null;
                try {
                    toAdd = new DbtbFlbvor(bbseType +
                         ";clbss=" + encodedTextClbssNbme);
                } cbtch (ClbssNotFoundException cbnnotHbppen) {
                }
                returnVblue.bdd(toAdd);
            }
        }
        return returnVblue;
    }

    privbte stbtic finbl String [] htmlDocumntTypes =
            new String [] {"bll", "selection", "frbgment"};

    privbte stbtic LinkedHbshSet<String> hbndleHtmlMimeTypes(String bbseType,
                                                             String mimeType) {

        LinkedHbshSet<String> returnVblues = new LinkedHbshSet<>();

        if (HTML_TEXT_BASE_TYPE.equbls(bbseType)) {
            for (String documentType : htmlDocumntTypes) {
                returnVblues.bdd(mimeType + ";document=" + documentType);
            }
        } else {
            returnVblues.bdd(mimeType);
        }

        return returnVblues;
    }

    /**
     * Returns b <code>Mbp</code> of the specified <code>DbtbFlbvor</code>s to
     * their most preferred <code>String</code> nbtive. Ebch nbtive vblue will
     * be the sbme bs the first nbtive in the List returned by
     * <code>getNbtivesForFlbvor</code> for the specified flbvor.
     * <p>
     * If b specified <code>DbtbFlbvor</code> is previously unknown to the
     * dbtb trbnsfer subsystem, then invoking this method will estbblish b
     * mbpping in both directions between the specified <code>DbtbFlbvor</code>
     * bnd bn encoded version of its MIME type bs its nbtive.
     *
     * @pbrbm flbvors bn brrby of <code>DbtbFlbvor</code>s which will be the
     *        key set of the returned <code>Mbp</code>. If <code>null</code> is
     *        specified, b mbpping of bll <code>DbtbFlbvor</code>s known to the
     *        dbtb trbnsfer subsystem to their most preferred
     *        <code>String</code> nbtives will be returned.
     * @return b <code>jbvb.util.Mbp</code> of <code>DbtbFlbvor</code>s to
     *         <code>String</code> nbtives
     *
     * @see #getNbtivesForFlbvor
     * @see #encodeDbtbFlbvor
     */
    @Override
    public synchronized Mbp<DbtbFlbvor,String> getNbtivesForFlbvors(DbtbFlbvor[] flbvors)
    {
        // Use getNbtivesForFlbvor to generbte extrb nbtives for text flbvors
        // bnd stringFlbvor

        if (flbvors == null) {
            List<DbtbFlbvor> flbvor_list = getFlbvorsForNbtive(null);
            flbvors = new DbtbFlbvor[flbvor_list.size()];
            flbvor_list.toArrby(flbvors);
        }

        Mbp<DbtbFlbvor, String> retvbl = new HbshMbp<>(flbvors.length, 1.0f);
        for (DbtbFlbvor flbvor : flbvors) {
            List<String> nbtives = getNbtivesForFlbvor(flbvor);
            String nbt = (nbtives.isEmpty()) ? null : nbtives.get(0);
            retvbl.put(flbvor, nbt);
        }

        return retvbl;
    }

    /**
     * Returns b <code>Mbp</code> of the specified <code>String</code> nbtives
     * to their most preferred <code>DbtbFlbvor</code>. Ebch
     * <code>DbtbFlbvor</code> vblue will be the sbme bs the first
     * <code>DbtbFlbvor</code> in the List returned by
     * <code>getFlbvorsForNbtive</code> for the specified nbtive.
     * <p>
     * If b specified nbtive is previously unknown to the dbtb trbnsfer
     * subsystem, bnd thbt nbtive hbs been properly encoded, then invoking this
     * method will estbblish b mbpping in both directions between the specified
     * nbtive bnd b <code>DbtbFlbvor</code> whose MIME type is b decoded
     * version of the nbtive.
     *
     * @pbrbm nbtives bn brrby of <code>String</code>s which will be the
     *        key set of the returned <code>Mbp</code>. If <code>null</code> is
     *        specified, b mbpping of bll supported <code>String</code> nbtives
     *        to their most preferred <code>DbtbFlbvor</code>s will be
     *        returned.
     * @return b <code>jbvb.util.Mbp</code> of <code>String</code> nbtives to
     *         <code>DbtbFlbvor</code>s
     *
     * @see #getFlbvorsForNbtive
     * @see #encodeJbvbMIMEType
     */
    @Override
    public synchronized Mbp<String,DbtbFlbvor> getFlbvorsForNbtives(String[] nbtives)
    {
        // Use getFlbvorsForNbtive to generbte extrb flbvors for text nbtives
        if (nbtives == null) {
            List<String> nbtivesList = getNbtivesForFlbvor(null);
            nbtives = new String[nbtivesList.size()];
            nbtivesList.toArrby(nbtives);
        }

        Mbp<String, DbtbFlbvor> retvbl = new HbshMbp<>(nbtives.length, 1.0f);
        for (String bNbtive : nbtives) {
            List<DbtbFlbvor> flbvors = getFlbvorsForNbtive(bNbtive);
            DbtbFlbvor flbv = (flbvors.isEmpty())? null : flbvors.get(0);
            retvbl.put(bNbtive, flbv);
        }
        return retvbl;
    }

    /**
     * Adds b mbpping from the specified <code>DbtbFlbvor</code> (bnd bll
     * <code>DbtbFlbvor</code>s equbl to the specified <code>DbtbFlbvor</code>)
     * to the specified <code>String</code> nbtive.
     * Unlike <code>getNbtivesForFlbvor</code>, the mbpping will only be
     * estbblished in one direction, bnd the nbtive will not be encoded. To
     * estbblish b two-wby mbpping, cbll
     * <code>bddFlbvorForUnencodedNbtive</code> bs well. The new mbpping will
     * be of lower priority thbn bny existing mbpping.
     * This method hbs no effect if b mbpping from the specified or equbl
     * <code>DbtbFlbvor</code> to the specified <code>String</code> nbtive
     * blrebdy exists.
     *
     * @pbrbm flbv the <code>DbtbFlbvor</code> key for the mbpping
     * @pbrbm nbt the <code>String</code> nbtive vblue for the mbpping
     * @throws NullPointerException if flbv or nbt is <code>null</code>
     *
     * @see #bddFlbvorForUnencodedNbtive
     * @since 1.4
     */
    public synchronized void bddUnencodedNbtiveForFlbvor(DbtbFlbvor flbv,
                                                         String nbt) {
        Objects.requireNonNull(nbt, "Null nbtive not permitted");
        Objects.requireNonNull(flbv, "Null flbvor not permitted");

        LinkedHbshSet<String> nbtives = getFlbvorToNbtive().get(flbv);
        if (nbtives == null) {
            nbtives = new LinkedHbshSet<>(1);
            getFlbvorToNbtive().put(flbv, nbtives);
        }
        nbtives.bdd(nbt);
        nbtivesForFlbvorCbche.remove(flbv);
    }

    /**
     * Discbrds the current mbppings for the specified <code>DbtbFlbvor</code>
     * bnd bll <code>DbtbFlbvor</code>s equbl to the specified
     * <code>DbtbFlbvor</code>, bnd crebtes new mbppings to the
     * specified <code>String</code> nbtives.
     * Unlike <code>getNbtivesForFlbvor</code>, the mbppings will only be
     * estbblished in one direction, bnd the nbtives will not be encoded. To
     * estbblish two-wby mbppings, cbll <code>setFlbvorsForNbtive</code>
     * bs well. The first nbtive in the brrby will represent the highest
     * priority mbpping. Subsequent nbtives will represent mbppings of
     * decrebsing priority.
     * <p>
     * If the brrby contbins severbl elements thbt reference equbl
     * <code>String</code> nbtives, this method will estbblish new mbppings
     * for the first of those elements bnd ignore the rest of them.
     * <p>
     * It is recommended thbt client code not reset mbppings estbblished by the
     * dbtb trbnsfer subsystem. This method should only be used for
     * bpplicbtion-level mbppings.
     *
     * @pbrbm flbv the <code>DbtbFlbvor</code> key for the mbppings
     * @pbrbm nbtives the <code>String</code> nbtive vblues for the mbppings
     * @throws NullPointerException if flbv or nbtives is <code>null</code>
     *         or if nbtives contbins <code>null</code> elements
     *
     * @see #setFlbvorsForNbtive
     * @since 1.4
     */
    public synchronized void setNbtivesForFlbvor(DbtbFlbvor flbv,
                                                 String[] nbtives) {
        Objects.requireNonNull(nbtives, "Null nbtives not permitted");
        Objects.requireNonNull(flbv, "Null flbvors not permitted");

        getFlbvorToNbtive().remove(flbv);
        for (String bNbtive : nbtives) {
            bddUnencodedNbtiveForFlbvor(flbv, bNbtive);
        }
        disbbledMbppingGenerbtionKeys.bdd(flbv);
        nbtivesForFlbvorCbche.remove(flbv);
    }

    /**
     * Adds b mbpping from b single <code>String</code> nbtive to b single
     * <code>DbtbFlbvor</code>. Unlike <code>getFlbvorsForNbtive</code>, the
     * mbpping will only be estbblished in one direction, bnd the nbtive will
     * not be encoded. To estbblish b two-wby mbpping, cbll
     * <code>bddUnencodedNbtiveForFlbvor</code> bs well. The new mbpping will
     * be of lower priority thbn bny existing mbpping.
     * This method hbs no effect if b mbpping from the specified
     * <code>String</code> nbtive to the specified or equbl
     * <code>DbtbFlbvor</code> blrebdy exists.
     *
     * @pbrbm nbt the <code>String</code> nbtive key for the mbpping
     * @pbrbm flbv the <code>DbtbFlbvor</code> vblue for the mbpping
     * @throws NullPointerException if nbt or flbv is <code>null</code>
     *
     * @see #bddUnencodedNbtiveForFlbvor
     * @since 1.4
     */
    public synchronized void bddFlbvorForUnencodedNbtive(String nbt,
                                                         DbtbFlbvor flbv) {
        Objects.requireNonNull(nbt, "Null nbtive not permitted");
        Objects.requireNonNull(flbv, "Null flbvor not permitted");

        LinkedHbshSet<DbtbFlbvor> flbvors = getNbtiveToFlbvor().get(nbt);
        if (flbvors == null) {
            flbvors = new LinkedHbshSet<>(1);
            getNbtiveToFlbvor().put(nbt, flbvors);
        }
        flbvors.bdd(flbv);
        flbvorsForNbtiveCbche.remove(nbt);
    }

    /**
     * Discbrds the current mbppings for the specified <code>String</code>
     * nbtive, bnd crebtes new mbppings to the specified
     * <code>DbtbFlbvor</code>s. Unlike <code>getFlbvorsForNbtive</code>, the
     * mbppings will only be estbblished in one direction, bnd the nbtives need
     * not be encoded. To estbblish two-wby mbppings, cbll
     * <code>setNbtivesForFlbvor</code> bs well. The first
     * <code>DbtbFlbvor</code> in the brrby will represent the highest priority
     * mbpping. Subsequent <code>DbtbFlbvor</code>s will represent mbppings of
     * decrebsing priority.
     * <p>
     * If the brrby contbins severbl elements thbt reference equbl
     * <code>DbtbFlbvor</code>s, this method will estbblish new mbppings
     * for the first of those elements bnd ignore the rest of them.
     * <p>
     * It is recommended thbt client code not reset mbppings estbblished by the
     * dbtb trbnsfer subsystem. This method should only be used for
     * bpplicbtion-level mbppings.
     *
     * @pbrbm nbt the <code>String</code> nbtive key for the mbppings
     * @pbrbm flbvors the <code>DbtbFlbvor</code> vblues for the mbppings
     * @throws NullPointerException if nbt or flbvors is <code>null</code>
     *         or if flbvors contbins <code>null</code> elements
     *
     * @see #setNbtivesForFlbvor
     * @since 1.4
     */
    public synchronized void setFlbvorsForNbtive(String nbt,
                                                 DbtbFlbvor[] flbvors) {
        Objects.requireNonNull(nbt, "Null nbtive not permitted");
        Objects.requireNonNull(flbvors, "Null flbvors not permitted");

        getNbtiveToFlbvor().remove(nbt);
        for (DbtbFlbvor flbvor : flbvors) {
            bddFlbvorForUnencodedNbtive(nbt, flbvor);
        }
        disbbledMbppingGenerbtionKeys.bdd(nbt);
        flbvorsForNbtiveCbche.remove(nbt);
    }

    /**
     * Encodes b MIME type for use bs b <code>String</code> nbtive. The formbt
     * of bn encoded representbtion of b MIME type is implementbtion-dependent.
     * The only restrictions bre:
     * <ul>
     * <li>The encoded representbtion is <code>null</code> if bnd only if the
     * MIME type <code>String</code> is <code>null</code>.</li>
     * <li>The encoded representbtions for two non-<code>null</code> MIME type
     * <code>String</code>s bre equbl if bnd only if these <code>String</code>s
     * bre equbl bccording to <code>String.equbls(Object)</code>.</li>
     * </ul>
     * <p>
     * The reference implementbtion of this method returns the specified MIME
     * type <code>String</code> prefixed with <code>JAVA_DATAFLAVOR:</code>.
     *
     * @pbrbm mimeType the MIME type to encode
     * @return the encoded <code>String</code>, or <code>null</code> if
     *         mimeType is <code>null</code>
     */
    public stbtic String encodeJbvbMIMEType(String mimeType) {
        return (mimeType != null)
            ? JbvbMIME + mimeType
            : null;
    }

    /**
     * Encodes b <code>DbtbFlbvor</code> for use bs b <code>String</code>
     * nbtive. The formbt of bn encoded <code>DbtbFlbvor</code> is
     * implementbtion-dependent. The only restrictions bre:
     * <ul>
     * <li>The encoded representbtion is <code>null</code> if bnd only if the
     * specified <code>DbtbFlbvor</code> is <code>null</code> or its MIME type
     * <code>String</code> is <code>null</code>.</li>
     * <li>The encoded representbtions for two non-<code>null</code>
     * <code>DbtbFlbvor</code>s with non-<code>null</code> MIME type
     * <code>String</code>s bre equbl if bnd only if the MIME type
     * <code>String</code>s of these <code>DbtbFlbvor</code>s bre equbl
     * bccording to <code>String.equbls(Object)</code>.</li>
     * </ul>
     * <p>
     * The reference implementbtion of this method returns the MIME type
     * <code>String</code> of the specified <code>DbtbFlbvor</code> prefixed
     * with <code>JAVA_DATAFLAVOR:</code>.
     *
     * @pbrbm flbv the <code>DbtbFlbvor</code> to encode
     * @return the encoded <code>String</code>, or <code>null</code> if
     *         flbv is <code>null</code> or hbs b <code>null</code> MIME type
     */
    public stbtic String encodeDbtbFlbvor(DbtbFlbvor flbv) {
        return (flbv != null)
            ? SystemFlbvorMbp.encodeJbvbMIMEType(flbv.getMimeType())
            : null;
    }

    /**
     * Returns whether the specified <code>String</code> is bn encoded Jbvb
     * MIME type.
     *
     * @pbrbm str the <code>String</code> to test
     * @return <code>true</code> if the <code>String</code> is encoded;
     *         <code>fblse</code> otherwise
     */
    public stbtic boolebn isJbvbMIMEType(String str) {
        return (str != null && str.stbrtsWith(JbvbMIME, 0));
    }

    /**
     * Decodes b <code>String</code> nbtive for use bs b Jbvb MIME type.
     *
     * @pbrbm nbt the <code>String</code> to decode
     * @return the decoded Jbvb MIME type, or <code>null</code> if nbt is not
     *         bn encoded <code>String</code> nbtive
     */
    public stbtic String decodeJbvbMIMEType(String nbt) {
        return (isJbvbMIMEType(nbt))
            ? nbt.substring(JbvbMIME.length(), nbt.length()).trim()
            : null;
    }

    /**
     * Decodes b <code>String</code> nbtive for use bs b
     * <code>DbtbFlbvor</code>.
     *
     * @pbrbm nbt the <code>String</code> to decode
     * @return the decoded <code>DbtbFlbvor</code>, or <code>null</code> if
     *         nbt is not bn encoded <code>String</code> nbtive
     * @throws ClbssNotFoundException if the clbss of the dbtb flbvor
     * is not lobded
     */
    public stbtic DbtbFlbvor decodeDbtbFlbvor(String nbt)
        throws ClbssNotFoundException
    {
        String retvbl_str = SystemFlbvorMbp.decodeJbvbMIMEType(nbt);
        return (retvbl_str != null)
            ? new DbtbFlbvor(retvbl_str)
            : null;
    }

    privbte stbtic finbl clbss SoftCbche<K, V> {
        Mbp<K, SoftReference<LinkedHbshSet<V>>> cbche;

        public void put(K key, LinkedHbshSet<V> vblue) {
            if (cbche == null) {
                cbche = new HbshMbp<>(1);
            }
            cbche.put(key, new SoftReference<>(vblue));
        }

        public void remove(K key) {
            if (cbche == null) return;
            cbche.remove(null);
            cbche.remove(key);
        }

        public LinkedHbshSet<V> check(K key) {
            if (cbche == null) return null;
            SoftReference<LinkedHbshSet<V>> ref = cbche.get(key);
            if (ref != null) {
                return ref.get();
            }
            return null;
        }
    }
}
