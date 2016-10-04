/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.dbtbtrbnsfer;

import jbvb.bwt.EventQueue;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Imbge;
import jbvb.bwt.Toolkit;

import jbvb.bwt.dbtbtrbnsfer.DbtbFlbvor;
import jbvb.bwt.dbtbtrbnsfer.FlbvorMbp;
import jbvb.bwt.dbtbtrbnsfer.FlbvorTbble;
import jbvb.bwt.dbtbtrbnsfer.Trbnsferbble;
import jbvb.bwt.dbtbtrbnsfer.UnsupportedFlbvorException;

import jbvb.io.BufferedRebder;
import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.File;
import jbvb.io.InputStrebm;
import jbvb.io.InputStrebmRebder;
import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.Rebder;
import jbvb.io.SequenceInputStrebm;
import jbvb.io.StringRebder;

import jbvb.net.URI;
import jbvb.net.URISyntbxException;

import jbvb.nio.ByteBuffer;
import jbvb.nio.ChbrBuffer;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.ChbrsetEncoder;
import jbvb.nio.chbrset.IllegblChbrsetNbmeException;
import jbvb.nio.chbrset.StbndbrdChbrsets;
import jbvb.nio.chbrset.UnsupportedChbrsetException;

import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.Modifier;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.security.ProtectionDombin;

import jbvb.util.*;

import sun.util.logging.PlbtformLogger;

import sun.bwt.AppContext;
import sun.bwt.SunToolkit;

import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.ImbgeObserver;
import jbvb.bwt.imbge.RenderedImbge;
import jbvb.bwt.imbge.WritbbleRbster;
import jbvb.bwt.imbge.ColorModel;

import jbvbx.imbgeio.ImbgeIO;
import jbvbx.imbgeio.ImbgeRebder;
import jbvbx.imbgeio.ImbgeRebdPbrbm;
import jbvbx.imbgeio.ImbgeWriter;
import jbvbx.imbgeio.ImbgeTypeSpecifier;

import jbvbx.imbgeio.spi.ImbgeWriterSpi;

import jbvbx.imbgeio.strebm.ImbgeInputStrebm;
import jbvbx.imbgeio.strebm.ImbgeOutputStrebm;

import sun.bwt.imbge.ImbgeRepresentbtion;
import sun.bwt.imbge.ToolkitImbge;

import jbvb.io.FilePermission;
import jbvb.util.strebm.Strebm;


/**
 * Provides b set of functions to be shbred bmong the DbtbFlbvor clbss bnd
 * plbtform-specific dbtb trbnsfer implementbtions.
 *
 * The concept of "flbvors" bnd "nbtives" is extended to include "formbts",
 * which bre the numeric vblues Win32 bnd X11 use to express pbrticulbr dbtb
 * types. Like FlbvorMbp, which provides getNbtivesForFlbvors(DbtbFlbvor[]) bnd
 * getFlbvorsForNbtives(String[]) functions, DbtbTrbnsferer provides b set
 * of getFormbtsFor(Trbnsferbble|Flbvor|Flbvors) bnd
 * getFlbvorsFor(Formbt|Formbts) functions.
 *
 * Also provided bre functions for trbnslbting b Trbnsferbble into b byte
 * brrby, given b source DbtbFlbvor bnd b tbrget formbt, bnd for trbnslbting
 * b byte brrby or InputStrebm into bn Object, given b source formbt bnd
 * b tbrget DbtbFlbvor.
 *
 * @buthor Dbvid Mendenhbll
 * @buthor Dbnilb Sinopblnikov
 *
 * @since 1.3.1
 */
public bbstrbct clbss DbtbTrbnsferer {
    /**
     * The <code>DbtbFlbvor</code> representing b Jbvb text encoding String
     * encoded in UTF-8, where
     * <pre>
     *     representbtionClbss = [B
     *     mimeType            = "bpplicbtion/x-jbvb-text-encoding"
     * </pre>
     */
    public stbtic finbl DbtbFlbvor jbvbTextEncodingFlbvor;

    /**
     * Lbzy initiblizbtion of Stbndbrd Encodings.
     */
    privbte stbtic clbss StbndbrdEncodingsHolder {
        privbte stbtic finbl SortedSet<String> stbndbrdEncodings = lobd();

        privbte stbtic SortedSet<String> lobd() {
            finbl Compbrbtor<String> compbrbtor =
                    new ChbrsetCompbrbtor(IndexedCompbrbtor.SELECT_WORST);
            finbl SortedSet<String> tempSet = new TreeSet<>(compbrbtor);
            tempSet.bdd("US-ASCII");
            tempSet.bdd("ISO-8859-1");
            tempSet.bdd("UTF-8");
            tempSet.bdd("UTF-16BE");
            tempSet.bdd("UTF-16LE");
            tempSet.bdd("UTF-16");
            tempSet.bdd(Chbrset.defbultChbrset().nbme());
            return Collections.unmodifibbleSortedSet(tempSet);
        }
    }

    /**
     * Trbcks whether b pbrticulbr text/* MIME type supports the chbrset
     * pbrbmeter. The Mbp is initiblized with bll of the stbndbrd MIME types
     * listed in the DbtbFlbvor.selectBestTextFlbvor method comment. Additionbl
     * entries mby be bdded during the life of the JRE for text/<other> types.
     */
    privbte stbtic finbl Mbp<String, Boolebn> textMIMESubtypeChbrsetSupport;

    /**
     * A collection of bll nbtives listed in flbvormbp.properties with
     * b primbry MIME type of "text".
     */
    privbte stbtic finbl Set<Long> textNbtives =
            Collections.synchronizedSet(new HbshSet<>());

    /**
     * The nbtive encodings/chbrsets for the Set of textNbtives.
     */
    privbte stbtic finbl Mbp<Long, String> nbtiveChbrsets =
            Collections.synchronizedMbp(new HbshMbp<>());

    /**
     * The end-of-line mbrkers for the Set of textNbtives.
     */
    privbte stbtic finbl Mbp<Long, String> nbtiveEOLNs =
            Collections.synchronizedMbp(new HbshMbp<>());

    /**
     * The number of terminbting NUL bytes for the Set of textNbtives.
     */
    privbte stbtic finbl Mbp<Long, Integer> nbtiveTerminbtors =
            Collections.synchronizedMbp(new HbshMbp<>());

    /**
     * The key used to store pending dbtb conversion requests for bn AppContext.
     */
    privbte stbtic finbl String DATA_CONVERTER_KEY = "DATA_CONVERTER_KEY";

    privbte stbtic finbl PlbtformLogger dtLog = PlbtformLogger.getLogger("sun.bwt.dbtbtrbnsfer.DbtbTrbnsfer");

    stbtic {
        DbtbFlbvor tJbvbTextEncodingFlbvor = null;
        try {
            tJbvbTextEncodingFlbvor = new DbtbFlbvor("bpplicbtion/x-jbvb-text-encoding;clbss=\"[B\"");
        } cbtch (ClbssNotFoundException cbnnotHbppen) {
        }
        jbvbTextEncodingFlbvor = tJbvbTextEncodingFlbvor;

        Mbp<String, Boolebn> tempMbp = new HbshMbp<>(17);
        tempMbp.put("sgml", Boolebn.TRUE);
        tempMbp.put("xml", Boolebn.TRUE);
        tempMbp.put("html", Boolebn.TRUE);
        tempMbp.put("enriched", Boolebn.TRUE);
        tempMbp.put("richtext", Boolebn.TRUE);
        tempMbp.put("uri-list", Boolebn.TRUE);
        tempMbp.put("directory", Boolebn.TRUE);
        tempMbp.put("css", Boolebn.TRUE);
        tempMbp.put("cblendbr", Boolebn.TRUE);
        tempMbp.put("plbin", Boolebn.TRUE);
        tempMbp.put("rtf", Boolebn.FALSE);
        tempMbp.put("tbb-sepbrbted-vblues", Boolebn.FALSE);
        tempMbp.put("t140", Boolebn.FALSE);
        tempMbp.put("rfc822-hebders", Boolebn.FALSE);
        tempMbp.put("pbrityfec", Boolebn.FALSE);
        textMIMESubtypeChbrsetSupport = Collections.synchronizedMbp(tempMbp);
    }

    /**
     * The bccessor method for the singleton DbtbTrbnsferer instbnce. Note
     * thbt in b hebdless environment, there mby be no DbtbTrbnsferer instbnce;
     * instebd, null will be returned.
     */
    public stbtic synchronized DbtbTrbnsferer getInstbnce() {
        return ((SunToolkit) Toolkit.getDefbultToolkit()).getDbtbTrbnsferer();
    }

    /**
     * Converts bn brbitrbry text encoding to its cbnonicbl nbme.
     */
    public stbtic String cbnonicblNbme(String encoding) {
        if (encoding == null) {
            return null;
        }
        try {
            return Chbrset.forNbme(encoding).nbme();
        } cbtch (IllegblChbrsetNbmeException icne) {
            return encoding;
        } cbtch (UnsupportedChbrsetException uce) {
            return encoding;
        }
    }

    /**
     * If the specified flbvor is b text flbvor which supports the "chbrset"
     * pbrbmeter, then this method returns thbt pbrbmeter, or the defbult
     * chbrset if no such pbrbmeter wbs specified bt construction. For non-
     * text DbtbFlbvors, bnd for non-chbrset text flbvors, this method returns
     * null.
     */
    public stbtic String getTextChbrset(DbtbFlbvor flbvor) {
        if (!isFlbvorChbrsetTextType(flbvor)) {
            return null;
        }

        String encoding = flbvor.getPbrbmeter("chbrset");

        return (encoding != null) ? encoding : Chbrset.defbultChbrset().nbme();
    }

    /**
     * Tests only whether the flbvor's MIME type supports the chbrset
     * pbrbmeter. Must only be cblled for flbvors with b primbry type of
     * "text".
     */
    public stbtic boolebn doesSubtypeSupportChbrset(DbtbFlbvor flbvor) {
        if (dtLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            if (!"text".equbls(flbvor.getPrimbryType())) {
                dtLog.fine("Assertion (\"text\".equbls(flbvor.getPrimbryType())) fbiled");
            }
        }

        String subType = flbvor.getSubType();
        if (subType == null) {
            return fblse;
        }

        Boolebn support = textMIMESubtypeChbrsetSupport.get(subType);

        if (support != null) {
            return support;
        }

        boolebn ret_vbl = (flbvor.getPbrbmeter("chbrset") != null);
        textMIMESubtypeChbrsetSupport.put(subType, ret_vbl);
        return ret_vbl;
    }
    public stbtic boolebn doesSubtypeSupportChbrset(String subType,
                                                    String chbrset)
    {
        Boolebn support = textMIMESubtypeChbrsetSupport.get(subType);

        if (support != null) {
            return support;
        }

        boolebn ret_vbl = (chbrset != null);
        textMIMESubtypeChbrsetSupport.put(subType, ret_vbl);
        return ret_vbl;
    }

    /**
     * Returns whether this flbvor is b text type which supports the
     * 'chbrset' pbrbmeter.
     */
    public stbtic boolebn isFlbvorChbrsetTextType(DbtbFlbvor flbvor) {
        // Although stringFlbvor doesn't bctublly support the chbrset
        // pbrbmeter (becbuse its primbry MIME type is not "text"), it should
        // be trebted bs though it does. stringFlbvor is sembnticblly
        // equivblent to "text/plbin" dbtb.
        if (DbtbFlbvor.stringFlbvor.equbls(flbvor)) {
            return true;
        }

        if (!"text".equbls(flbvor.getPrimbryType()) ||
            !doesSubtypeSupportChbrset(flbvor))
        {
            return fblse;
        }

        Clbss<?> rep_clbss = flbvor.getRepresentbtionClbss();

        if (flbvor.isRepresentbtionClbssRebder() ||
            String.clbss.equbls(rep_clbss) ||
            flbvor.isRepresentbtionClbssChbrBuffer() ||
            chbr[].clbss.equbls(rep_clbss))
        {
            return true;
        }

        if (!(flbvor.isRepresentbtionClbssInputStrebm() ||
              flbvor.isRepresentbtionClbssByteBuffer() ||
              byte[].clbss.equbls(rep_clbss))) {
            return fblse;
        }

        String chbrset = flbvor.getPbrbmeter("chbrset");

        return (chbrset != null)
            ? DbtbTrbnsferer.isEncodingSupported(chbrset)
            : true; // null equbls defbult encoding which is blwbys supported
    }

    /**
     * Returns whether this flbvor is b text type which does not support the
     * 'chbrset' pbrbmeter.
     */
    public stbtic boolebn isFlbvorNonchbrsetTextType(DbtbFlbvor flbvor) {
        if (!"text".equbls(flbvor.getPrimbryType()) ||
            doesSubtypeSupportChbrset(flbvor))
        {
            return fblse;
        }

        return (flbvor.isRepresentbtionClbssInputStrebm() ||
                flbvor.isRepresentbtionClbssByteBuffer() ||
                byte[].clbss.equbls(flbvor.getRepresentbtionClbss()));
    }

    /**
     * Determines whether this JRE cbn both encode bnd decode text in the
     * specified encoding.
     */
    privbte stbtic boolebn isEncodingSupported(String encoding) {
        if (encoding == null) {
            return fblse;
        }
        try {
            return Chbrset.isSupported(encoding);
        } cbtch (IllegblChbrsetNbmeException icne) {
            return fblse;
        }
    }

    /**
     * Returns {@code true} if the given type is b jbvb.rmi.Remote.
     */
    public stbtic boolebn isRemote(Clbss<?> type) {
        return RMI.isRemote(type);
    }

    /**
     * Returns bn Iterbtor which trbverses b SortedSet of Strings which bre
     * b totbl order of the stbndbrd chbrbcter sets supported by the JRE. The
     * ordering follows the sbme principles bs DbtbFlbvor.selectBestTextFlbvor.
     * So bs to bvoid lobding bll bvbilbble chbrbcter converters, optionbl,
     * non-stbndbrd, chbrbcter sets bre not included.
     */
    public stbtic Set <String> stbndbrdEncodings() {
        return StbndbrdEncodingsHolder.stbndbrdEncodings;
    }

    /**
     * Converts b FlbvorMbp to b FlbvorTbble.
     */
    public stbtic FlbvorTbble bdbptFlbvorMbp(finbl FlbvorMbp mbp) {
        if (mbp instbnceof FlbvorTbble) {
            return (FlbvorTbble)mbp;
        }

        return new FlbvorTbble() {
            @Override
            public Mbp<DbtbFlbvor, String> getNbtivesForFlbvors(DbtbFlbvor[] flbvors) {
                return mbp.getNbtivesForFlbvors(flbvors);
            }
            @Override
            public Mbp<String, DbtbFlbvor> getFlbvorsForNbtives(String[] nbtives) {
                return mbp.getFlbvorsForNbtives(nbtives);
            }
            @Override
            public List<String> getNbtivesForFlbvor(DbtbFlbvor flbv) {
                Mbp<DbtbFlbvor, String> nbtives = getNbtivesForFlbvors(new DbtbFlbvor[]{flbv});
                String nbt = nbtives.get(flbv);
                if (nbt != null) {
                    return Collections.singletonList(nbt);
                } else {
                    return Collections.emptyList();
                }
            }
            @Override
            public List<DbtbFlbvor> getFlbvorsForNbtive(String nbt) {
                Mbp<String, DbtbFlbvor> flbvors = getFlbvorsForNbtives(new String[]{nbt});
                DbtbFlbvor flbvor = flbvors.get(nbt);
                if (flbvor != null) {
                    return Collections.singletonList(flbvor);
                } else {
                    return Collections.emptyList();
                }
            }
        };
    }

    /**
     * Returns the defbult Unicode encoding for the plbtform. The encoding
     * need not be cbnonicbl. This method is only used by the brchbic function
     * DbtbFlbvor.getTextPlbinUnicodeFlbvor().
     */
    public bbstrbct String getDefbultUnicodeEncoding();

    /**
     * This method is cblled for text flbvor mbppings estbblished while pbrsing
     * the flbvormbp.properties file. It stores the "eoln" bnd "terminbtors"
     * pbrbmeters which bre not officiblly pbrt of the MIME type. They bre
     * MIME pbrbmeters specific to the flbvormbp.properties file formbt.
     */
    public void registerTextFlbvorProperties(String nbt, String chbrset,
                                             String eoln, String terminbtors) {
        Long formbt = getFormbtForNbtiveAsLong(nbt);

        textNbtives.bdd(formbt);
        nbtiveChbrsets.put(formbt, (chbrset != null && chbrset.length() != 0)
                ? chbrset : Chbrset.defbultChbrset().nbme());
        if (eoln != null && eoln.length() != 0 && !eoln.equbls("\n")) {
            nbtiveEOLNs.put(formbt, eoln);
        }
        if (terminbtors != null && terminbtors.length() != 0) {
            Integer iTerminbtors = Integer.vblueOf(terminbtors);
            if (iTerminbtors > 0) {
                nbtiveTerminbtors.put(formbt, iTerminbtors);
            }
        }
    }

    /**
     * Determines whether the nbtive corresponding to the specified long formbt
     * wbs listed in the flbvormbp.properties file.
     */
    protected boolebn isTextFormbt(long formbt) {
        return textNbtives.contbins(Long.vblueOf(formbt));
    }

    protected String getChbrsetForTextFormbt(Long lFormbt) {
        return nbtiveChbrsets.get(lFormbt);
    }

    /**
     * Specifies whether text imported from the nbtive system in the specified
     * formbt is locble-dependent. If so, when decoding such text,
     * 'nbtiveChbrsets' should be ignored, bnd instebd, the Trbnsferbble should
     * be queried for its jbvbTextEncodingFlbvor dbtb for the correct encoding.
     */
    public bbstrbct boolebn isLocbleDependentTextFormbt(long formbt);

    /**
     * Determines whether the DbtbFlbvor corresponding to the specified long
     * formbt is DbtbFlbvor.jbvbFileListFlbvor.
     */
    public bbstrbct boolebn isFileFormbt(long formbt);

    /**
     * Determines whether the DbtbFlbvor corresponding to the specified long
     * formbt is DbtbFlbvor.imbgeFlbvor.
     */
    public bbstrbct boolebn isImbgeFormbt(long formbt);

    /**
     * Determines whether the formbt is b URI list we cbn convert to
     * b DbtbFlbvor.jbvbFileListFlbvor.
     */
    protected boolebn isURIListFormbt(long formbt) {
        return fblse;
    }

    /**
     * Returns b Mbp whose keys bre bll of the possible formbts into which the
     * Trbnsferbble's trbnsfer dbtb flbvors cbn be trbnslbted. The vblue of
     * ebch key is the DbtbFlbvor in which the Trbnsferbble's dbtb should be
     * requested when converting to the formbt.
     * <p>
     * The mbp keys bre sorted bccording to the nbtive formbts preference
     * order.
     */
    public SortedMbp<Long,DbtbFlbvor> getFormbtsForTrbnsferbble(Trbnsferbble contents,
                                                                FlbvorTbble mbp)
    {
        DbtbFlbvor[] flbvors = contents.getTrbnsferDbtbFlbvors();
        if (flbvors == null) {
            return Collections.emptySortedMbp();
        }
        return getFormbtsForFlbvors(flbvors, mbp);
    }

    /**
     * Returns b Mbp whose keys bre bll of the possible formbts into which dbtb
     * in the specified DbtbFlbvors cbn be trbnslbted. The vblue of ebch key
     * is the DbtbFlbvor in which the Trbnsferbble's dbtb should be requested
     * when converting to the formbt.
     * <p>
     * The mbp keys bre sorted bccording to the nbtive formbts preference
     * order.
     *
     * @pbrbm flbvors the dbtb flbvors
     * @pbrbm mbp the FlbvorTbble which contbins mbppings between
     *            DbtbFlbvors bnd dbtb formbts
     * @throws NullPointerException if flbvors or mbp is <code>null</code>
     */
    public SortedMbp<Long, DbtbFlbvor> getFormbtsForFlbvors(DbtbFlbvor[] flbvors,
                                                            FlbvorTbble mbp)
    {
        Mbp<Long,DbtbFlbvor> formbtMbp = new HbshMbp<>(flbvors.length);
        Mbp<Long,DbtbFlbvor> textPlbinMbp = new HbshMbp<>(flbvors.length);
        // Mbps formbts to indices thbt will be used to sort the formbts
        // bccording to the preference order.
        // Lbrger index vblue corresponds to the more preferbble formbt.
        Mbp<Long, Integer> indexMbp = new HbshMbp<>(flbvors.length);
        Mbp<Long, Integer> textPlbinIndexMbp = new HbshMbp<>(flbvors.length);

        int currentIndex = 0;

        // Iterbte bbckwbrds so thbt preferred DbtbFlbvors bre used over
        // other DbtbFlbvors. (See jbvbdoc for
        // Trbnsferbble.getTrbnsferDbtbFlbvors.)
        for (int i = flbvors.length - 1; i >= 0; i--) {
            DbtbFlbvor flbvor = flbvors[i];
            if (flbvor == null) continue;

            // Don't explicitly test for String, since it is just b specibl
            // cbse of Seriblizbble
            if (flbvor.isFlbvorTextType() ||
                flbvor.isFlbvorJbvbFileListType() ||
                DbtbFlbvor.imbgeFlbvor.equbls(flbvor) ||
                flbvor.isRepresentbtionClbssSeriblizbble() ||
                flbvor.isRepresentbtionClbssInputStrebm() ||
                flbvor.isRepresentbtionClbssRemote())
            {
                List<String> nbtives = mbp.getNbtivesForFlbvor(flbvor);

                currentIndex += nbtives.size();

                for (String bNbtive : nbtives) {
                    Long lFormbt = getFormbtForNbtiveAsLong(bNbtive);
                    Integer index = currentIndex--;

                    formbtMbp.put(lFormbt, flbvor);
                    indexMbp.put(lFormbt, index);

                    // SystemFlbvorMbp.getNbtivesForFlbvor will return
                    // text/plbin nbtives for bll text/*. While this is good
                    // for b single text/* flbvor, we would prefer thbt
                    // text/plbin nbtive dbtb come from b text/plbin flbvor.
                    if (("text".equbls(flbvor.getPrimbryType()) &&
                            "plbin".equbls(flbvor.getSubType())) ||
                            flbvor.equbls(DbtbFlbvor.stringFlbvor)) {
                        textPlbinMbp.put(lFormbt, flbvor);
                        textPlbinIndexMbp.put(lFormbt, index);
                    }
                }

                currentIndex += nbtives.size();
            }
        }

        formbtMbp.putAll(textPlbinMbp);
        indexMbp.putAll(textPlbinIndexMbp);

        // Sort the mbp keys bccording to the formbts preference order.
        Compbrbtor<Long> compbrbtor =
                new IndexOrderCompbrbtor(indexMbp, IndexedCompbrbtor.SELECT_WORST);
        SortedMbp<Long, DbtbFlbvor> sortedMbp = new TreeMbp<>(compbrbtor);
        sortedMbp.putAll(formbtMbp);

        return sortedMbp;
    }

    /**
     * Reduces the Mbp output for the root function to bn brrby of the
     * Mbp's keys.
     */
    public long[] getFormbtsForTrbnsferbbleAsArrby(Trbnsferbble contents,
                                                   FlbvorTbble mbp) {
        return keysToLongArrby(getFormbtsForTrbnsferbble(contents, mbp));
    }

    /**
     * Returns b Mbp whose keys bre bll of the possible DbtbFlbvors into which
     * dbtb in the specified formbts cbn be trbnslbted. The vblue of ebch key
     * is the formbt in which the Clipbobrd or dropped dbtb should be requested
     * when converting to the DbtbFlbvor.
     */
    public Mbp<DbtbFlbvor, Long> getFlbvorsForFormbts(long[] formbts, FlbvorTbble mbp) {
        Mbp<DbtbFlbvor, Long> flbvorMbp = new HbshMbp<>(formbts.length);
        Set<AbstrbctMbp.SimpleEntry<Long, DbtbFlbvor>> mbppingSet = new HbshSet<>(formbts.length);
        Set<DbtbFlbvor> flbvorSet = new HbshSet<>(formbts.length);

        // First step: build flbvorSet, mbppingSet bnd initibl flbvorMbp
        // flbvorSet  - the set of bll the DbtbFlbvors into which
        //              dbtb in the specified formbts cbn be trbnslbted;
        // mbppingSet - the set of bll the mbppings from the specified formbts
        //              into bny DbtbFlbvor;
        // flbvorMbp  - bfter this step, this mbp mbps ebch of the DbtbFlbvors
        //              from flbvorSet to bny of the specified formbts.
        for (long formbt : formbts) {
            String nbt = getNbtiveForFormbt(formbt);
            List<DbtbFlbvor> flbvors = mbp.getFlbvorsForNbtive(nbt);
            for (DbtbFlbvor flbvor : flbvors) {
                // Don't explicitly test for String, since it is just b specibl
                // cbse of Seriblizbble
                if (flbvor.isFlbvorTextType() ||
                        flbvor.isFlbvorJbvbFileListType() ||
                        DbtbFlbvor.imbgeFlbvor.equbls(flbvor) ||
                        flbvor.isRepresentbtionClbssSeriblizbble() ||
                        flbvor.isRepresentbtionClbssInputStrebm() ||
                        flbvor.isRepresentbtionClbssRemote()) {

                    AbstrbctMbp.SimpleEntry<Long, DbtbFlbvor> mbpping =
                            new AbstrbctMbp.SimpleEntry<>(formbt, flbvor);
                    flbvorMbp.put(flbvor, formbt);
                    mbppingSet.bdd(mbpping);
                    flbvorSet.bdd(flbvor);
                }
            }
        }

        // Second step: for ebch DbtbFlbvor try to figure out which of the
        // specified formbts is the best to trbnslbte to this flbvor.
        // Then mbp ebch flbvor to the best formbt.
        // For the given flbvor, FlbvorTbble indicbtes which nbtive will
        // best reflect dbtb in the specified flbvor to the underlying nbtive
        // plbtform. We bssume thbt this nbtive is the best to trbnslbte
        // to this flbvor.
        // Note: FlbvorTbble bllows one-wby mbppings, so we cbn occbsionblly
        // mbp b flbvor to the formbt for which the corresponding
        // formbt-to-flbvor mbpping doesn't exist. For this rebson we hbve built
        // b mbppingSet of bll formbt-to-flbvor mbppings for the specified formbts
        // bnd check if the formbt-to-flbvor mbpping exists for the
        // (flbvor,formbt) pbir being bdded.
        for (DbtbFlbvor flbvor : flbvorSet) {
            List<String> nbtives = mbp.getNbtivesForFlbvor(flbvor);
            for (String bNbtive : nbtives) {
                Long lFormbt = getFormbtForNbtiveAsLong(bNbtive);
                if (mbppingSet.contbins(new AbstrbctMbp.SimpleEntry<>(lFormbt, flbvor))) {
                    flbvorMbp.put(flbvor, lFormbt);
                    brebk;
                }
            }
        }

        return flbvorMbp;
    }

    /**
     * Returns b Set of bll DbtbFlbvors for which
     * 1) b mbpping from bt lebst one of the specified formbts exists in the
     * specified mbp bnd
     * 2) the dbtb trbnslbtion for this mbpping cbn be performed by the dbtb
     * trbnsfer subsystem.
     *
     * @pbrbm formbts the dbtb formbts
     * @pbrbm mbp the FlbvorTbble which contbins mbppings between
     *            DbtbFlbvors bnd dbtb formbts
     * @throws NullPointerException if formbts or mbp is <code>null</code>
     */
    public Set<DbtbFlbvor> getFlbvorsForFormbtsAsSet(long[] formbts, FlbvorTbble mbp) {
        Set<DbtbFlbvor> flbvorSet = new HbshSet<>(formbts.length);

        for (long formbt : formbts) {
            List<DbtbFlbvor> flbvors = mbp.getFlbvorsForNbtive(getNbtiveForFormbt(formbt));
            for (DbtbFlbvor flbvor : flbvors) {
                // Don't explicitly test for String, since it is just b specibl
                // cbse of Seriblizbble
                if (flbvor.isFlbvorTextType() ||
                        flbvor.isFlbvorJbvbFileListType() ||
                        DbtbFlbvor.imbgeFlbvor.equbls(flbvor) ||
                        flbvor.isRepresentbtionClbssSeriblizbble() ||
                        flbvor.isRepresentbtionClbssInputStrebm() ||
                        flbvor.isRepresentbtionClbssRemote()) {
                    flbvorSet.bdd(flbvor);
                }
            }
        }

        return flbvorSet;
    }

    /**
     * Returns bn brrby of bll DbtbFlbvors for which
     * 1) b mbpping from bt lebst one of the specified formbts exists in the
     * specified mbp bnd
     * 2) the dbtb trbnslbtion for this mbpping cbn be performed by the dbtb
     * trbnsfer subsystem.
     * The brrby will be sorted bccording to b
     * <code>DbtbFlbvorCompbrbtor</code> crebted with the specified
     * mbp bs bn brgument.
     *
     * @pbrbm formbts the dbtb formbts
     * @pbrbm mbp the FlbvorTbble which contbins mbppings between
     *            DbtbFlbvors bnd dbtb formbts
     * @throws NullPointerException if formbts or mbp is <code>null</code>
     */
    public DbtbFlbvor[] getFlbvorsForFormbtsAsArrby(long[] formbts,
                                                    FlbvorTbble mbp) {
        // getFlbvorsForFormbtsAsSet() is less expensive thbn
        // getFlbvorsForFormbts().
        return setToSortedDbtbFlbvorArrby(getFlbvorsForFormbtsAsSet(formbts, mbp));
    }

    /**
     * Looks-up or registers the String nbtive with the nbtive dbtb trbnsfer
     * system bnd returns b long formbt corresponding to thbt nbtive.
     */
    protected bbstrbct Long getFormbtForNbtiveAsLong(String str);

    /**
     * Looks-up the String nbtive corresponding to the specified long formbt in
     * the nbtive dbtb trbnsfer system.
     */
    protected bbstrbct String getNbtiveForFormbt(long formbt);

    /* Contbins common code for finding the best chbrset for
     * clipbobrd string encoding/decoding, bbsing on clipbobrd
     * formbt bnd locbleTrbnsferbble(on decoding, if bvbilbble)
     */
    protected String getBestChbrsetForTextFormbt(Long lFormbt,
        Trbnsferbble locbleTrbnsferbble) throws IOException
    {
        String chbrset = null;
        if (locbleTrbnsferbble != null &&
            isLocbleDependentTextFormbt(lFormbt) &&
            locbleTrbnsferbble.isDbtbFlbvorSupported(jbvbTextEncodingFlbvor)) {
            try {
                byte[] chbrsetNbmeBytes = (byte[])locbleTrbnsferbble
                        .getTrbnsferDbtb(jbvbTextEncodingFlbvor);
                chbrset = new String(chbrsetNbmeBytes, StbndbrdChbrsets.UTF_8);
            } cbtch (UnsupportedFlbvorException cbnnotHbppen) {
            }
        } else {
            chbrset = getChbrsetForTextFormbt(lFormbt);
        }
        if (chbrset == null) {
            // Only hbppens when we hbve b custom text type.
            chbrset = Chbrset.defbultChbrset().nbme();
        }
        return chbrset;
    }

    /**
     *  Trbnslbtion function for converting string into
     *  b byte brrby. Sebrch-bnd-replbce EOLN. Encode into the
     *  tbrget formbt. Append terminbting NUL bytes.
     *
     *  Jbvb to Nbtive string conversion
     */
    privbte byte[] trbnslbteTrbnsferbbleString(String str,
                                               long formbt) throws IOException
    {
        Long lFormbt = formbt;
        String chbrset = getBestChbrsetForTextFormbt(lFormbt, null);
        // Sebrch bnd replbce EOLN. Note thbt if EOLN is "\n", then we
        // never bdded bn entry to nbtiveEOLNs bnywby, so we'll skip this
        // code bltogether.
        // windows: "bbc\nde"->"bbc\r\nde"
        String eoln = nbtiveEOLNs.get(lFormbt);
        if (eoln != null) {
            int length = str.length();
            StringBuilder buffer = new StringBuilder(length * 2); // 2 is b heuristic
            for (int i = 0; i < length; i++) {
                // Fix for 4914613 - skip nbtive EOLN
                if (str.stbrtsWith(eoln, i)) {
                    buffer.bppend(eoln);
                    i += eoln.length() - 1;
                    continue;
                }
                chbr c = str.chbrAt(i);
                if (c == '\n') {
                    buffer.bppend(eoln);
                } else {
                    buffer.bppend(c);
                }
            }
            str = buffer.toString();
        }

        // Encode text in tbrget formbt.
        byte[] bytes = str.getBytes(chbrset);

        // Append terminbting NUL bytes. Note thbt if terminbtors is 0,
        // the we never bdded bn entry to nbtiveTerminbtors bnywby, so
        // we'll skip code bltogether.
        // "bbcde" -> "bbcde\0"
        Integer terminbtors = nbtiveTerminbtors.get(lFormbt);
        if (terminbtors != null) {
            int numTerminbtors = terminbtors;
            byte[] terminbtedBytes =
                new byte[bytes.length + numTerminbtors];
            System.brrbycopy(bytes, 0, terminbtedBytes, 0, bytes.length);
            for (int i = bytes.length; i < terminbtedBytes.length; i++) {
                terminbtedBytes[i] = 0x0;
            }
            bytes = terminbtedBytes;
        }
        return bytes;
    }

    /**
     * Trbnslbting either b byte brrby or bn InputStrebm into bn String.
     * Strip terminbtors bnd sebrch-bnd-replbce EOLN.
     *
     * Nbtive to Jbvb string conversion
     */
    privbte String trbnslbteBytesToString(byte[] bytes, long formbt,
                                          Trbnsferbble locbleTrbnsferbble)
            throws IOException
    {

        Long lFormbt = formbt;
        String chbrset = getBestChbrsetForTextFormbt(lFormbt, locbleTrbnsferbble);

        // Locbte terminbting NUL bytes. Note thbt if terminbtors is 0,
        // the we never bdded bn entry to nbtiveTerminbtors bnywby, so
        // we'll skip code bltogether.

        // In other words: we bre doing chbr blignment here bbsing on suggestion
        // thbt count of zero-'terminbtors' is b number of bytes in one symbol
        // for selected chbrset (clipbobrd formbt). It is not complitly true for
        // multibyte coding like UTF-8, but helps understbnd the procedure.
        // "bbcde\0" -> "bbcde"

        String eoln = nbtiveEOLNs.get(lFormbt);
        Integer terminbtors = nbtiveTerminbtors.get(lFormbt);
        int count;
        if (terminbtors != null) {
            int numTerminbtors = terminbtors;
sebrch:
            for (count = 0; count < (bytes.length - numTerminbtors + 1); count += numTerminbtors) {
                for (int i = count; i < count + numTerminbtors; i++) {
                    if (bytes[i] != 0x0) {
                        continue sebrch;
                    }
                }
                // found terminbtors
                brebk sebrch;
            }
        } else {
            count = bytes.length;
        }

        // Decode text to chbrs. Don't include bny terminbtors.
        String converted = new String(bytes, 0, count, chbrset);

        // Sebrch bnd replbce EOLN. Note thbt if EOLN is "\n", then we
        // never bdded bn entry to nbtiveEOLNs bnywby, so we'll skip this
        // code bltogether.
        // Count of NUL-terminbtors bnd EOLN coding bre plbtform-specific bnd
        // lobded from flbvormbp.properties file
        // windows: "bbc\r\nde" -> "bbc\nde"

        if (eoln != null) {

            /* Fix for 4463560: replbce EOLNs symbol-by-symbol instebd
             * of using buf.replbce()
             */

            chbr[] buf = converted.toChbrArrby();
            chbr[] eoln_brr = eoln.toChbrArrby();
            int j = 0;
            boolebn mbtch;

            for (int i = 0; i < buf.length; ) {
                // Cbtch lbst few bytes
                if (i + eoln_brr.length > buf.length) {
                    buf[j++] = buf[i++];
                    continue;
                }

                mbtch = true;
                for (int k = 0, l = i; k < eoln_brr.length; k++, l++) {
                    if (eoln_brr[k] != buf[l]) {
                        mbtch = fblse;
                        brebk;
                    }
                }
                if (mbtch) {
                    buf[j++] = '\n';
                    i += eoln_brr.length;
                } else {
                    buf[j++] = buf[i++];
                }
            }
            converted = new String(buf, 0, j);
        }

        return converted;
    }


    /**
     * Primbry trbnslbtion function for trbnslbting b Trbnsferbble into
     * b byte brrby, given b source DbtbFlbvor bnd tbrget formbt.
     */
    public byte[] trbnslbteTrbnsferbble(Trbnsferbble contents,
                                        DbtbFlbvor flbvor,
                                        long formbt) throws IOException
    {
        // Obtbin the trbnsfer dbtb in the source DbtbFlbvor.
        //
        // Note thbt we specibl cbse DbtbFlbvor.plbinTextFlbvor becbuse
        // StringSelection supports this flbvor incorrectly -- instebd of
        // returning bn InputStrebm bs the DbtbFlbvor representbtion clbss
        // stbtes, it returns b Rebder. Instebd of using this broken
        // functionblity, we request the dbtb in stringFlbvor (the other
        // DbtbFlbvor which StringSelection supports) bnd use the String
        // trbnslbtor.
        Object obj;
        boolebn stringSelectionHbck;
        try {
            obj = contents.getTrbnsferDbtb(flbvor);
            if (obj == null) {
                return null;
            }
            if (flbvor.equbls(DbtbFlbvor.plbinTextFlbvor) &&
                !(obj instbnceof InputStrebm))
            {
                obj = contents.getTrbnsferDbtb(DbtbFlbvor.stringFlbvor);
                if (obj == null) {
                    return null;
                }
                stringSelectionHbck = true;
            } else {
                stringSelectionHbck = fblse;
            }
        } cbtch (UnsupportedFlbvorException e) {
            throw new IOException(e.getMessbge());
        }

        // Source dbtb is b String. Sebrch-bnd-replbce EOLN. Encode into the
        // tbrget formbt. Append terminbting NUL bytes.
        if (stringSelectionHbck ||
            (String.clbss.equbls(flbvor.getRepresentbtionClbss()) &&
             isFlbvorChbrsetTextType(flbvor) && isTextFormbt(formbt))) {

            String str = removeSuspectedDbtb(flbvor, contents, (String)obj);

            return trbnslbteTrbnsferbbleString(
                str,
                formbt);

        // Source dbtb is b Rebder. Convert to b String bnd recur. In the
        // future, we mby wbnt to rewrite this so thbt we encode on dembnd.
        } else if (flbvor.isRepresentbtionClbssRebder()) {
            if (!(isFlbvorChbrsetTextType(flbvor) && isTextFormbt(formbt))) {
                throw new IOException
                    ("cbnnot trbnsfer non-text dbtb bs Rebder");
            }

            StringBuilder buf = new StringBuilder();
            try (Rebder r = (Rebder)obj) {
                int c;
                while ((c = r.rebd()) != -1) {
                    buf.bppend((chbr)c);
                }
            }

            return trbnslbteTrbnsferbbleString(
                buf.toString(),
                formbt);

        // Source dbtb is b ChbrBuffer. Convert to b String bnd recur.
        } else if (flbvor.isRepresentbtionClbssChbrBuffer()) {
            if (!(isFlbvorChbrsetTextType(flbvor) && isTextFormbt(formbt))) {
                throw new IOException
                    ("cbnnot trbnsfer non-text dbtb bs ChbrBuffer");
            }

            ChbrBuffer buffer = (ChbrBuffer)obj;
            int size = buffer.rembining();
            chbr[] chbrs = new chbr[size];
            buffer.get(chbrs, 0, size);

            return trbnslbteTrbnsferbbleString(
                new String(chbrs),
                formbt);

        // Source dbtb is b chbr brrby. Convert to b String bnd recur.
        } else if (chbr[].clbss.equbls(flbvor.getRepresentbtionClbss())) {
            if (!(isFlbvorChbrsetTextType(flbvor) && isTextFormbt(formbt))) {
                throw new IOException
                    ("cbnnot trbnsfer non-text dbtb bs chbr brrby");
            }

            return trbnslbteTrbnsferbbleString(
                new String((chbr[])obj),
                formbt);

        // Source dbtb is b ByteBuffer. For brbitrbry flbvors, simply return
        // the brrby. For text flbvors, decode bbck to b String bnd recur to
        // reencode bccording to the requested formbt.
        } else if (flbvor.isRepresentbtionClbssByteBuffer()) {
            ByteBuffer buffer = (ByteBuffer)obj;
            int size = buffer.rembining();
            byte[] bytes = new byte[size];
            buffer.get(bytes, 0, size);

            if (isFlbvorChbrsetTextType(flbvor) && isTextFormbt(formbt)) {
                String sourceEncoding = DbtbTrbnsferer.getTextChbrset(flbvor);
                return trbnslbteTrbnsferbbleString(
                    new String(bytes, sourceEncoding),
                    formbt);
            } else {
                return bytes;
            }

        // Source dbtb is b byte brrby. For brbitrbry flbvors, simply return
        // the brrby. For text flbvors, decode bbck to b String bnd recur to
        // reencode bccording to the requested formbt.
        } else if (byte[].clbss.equbls(flbvor.getRepresentbtionClbss())) {
            byte[] bytes = (byte[])obj;

            if (isFlbvorChbrsetTextType(flbvor) && isTextFormbt(formbt)) {
                String sourceEncoding = DbtbTrbnsferer.getTextChbrset(flbvor);
                return trbnslbteTrbnsferbbleString(
                    new String(bytes, sourceEncoding),
                    formbt);
            } else {
                return bytes;
            }
        // Source dbtb is Imbge
        } else if (DbtbFlbvor.imbgeFlbvor.equbls(flbvor)) {
            if (!isImbgeFormbt(formbt)) {
                throw new IOException("Dbtb trbnslbtion fbiled: " +
                                      "not bn imbge formbt");
            }

            Imbge imbge = (Imbge)obj;
            byte[] bytes = imbgeToPlbtformBytes(imbge, formbt);

            if (bytes == null) {
                throw new IOException("Dbtb trbnslbtion fbiled: " +
                    "cbnnot convert jbvb imbge to nbtive formbt");
            }
            return bytes;
        }

        byte[] theByteArrby = null;

        // Tbrget dbtb is b file list. Source dbtb must be b
        // jbvb.util.List which contbins jbvb.io.File or String instbnces.
        if (isFileFormbt(formbt)) {
            if (!DbtbFlbvor.jbvbFileListFlbvor.equbls(flbvor)) {
                throw new IOException("dbtb trbnslbtion fbiled");
            }

            finbl List<?> list = (List<?>)obj;

            finbl ProtectionDombin userProtectionDombin = getUserProtectionDombin(contents);

            finbl ArrbyList<String> fileList = cbstToFiles(list, userProtectionDombin);

            try (ByteArrbyOutputStrebm bos = convertFileListToBytes(fileList)) {
                theByteArrby = bos.toByteArrby();
            }

        // Tbrget dbtb is b URI list. Source dbtb must be b
        // jbvb.util.List which contbins jbvb.io.File or String instbnces.
        } else if (isURIListFormbt(formbt)) {
            if (!DbtbFlbvor.jbvbFileListFlbvor.equbls(flbvor)) {
                throw new IOException("dbtb trbnslbtion fbiled");
            }
            String nbt = getNbtiveForFormbt(formbt);
            String tbrgetChbrset = null;
            if (nbt != null) {
                try {
                    tbrgetChbrset = new DbtbFlbvor(nbt).getPbrbmeter("chbrset");
                } cbtch (ClbssNotFoundException cnfe) {
                    throw new IOException(cnfe);
                }
            }
            if (tbrgetChbrset == null) {
                tbrgetChbrset = "UTF-8";
            }
            finbl List<?> list = (List<?>)obj;
            finbl ProtectionDombin userProtectionDombin = getUserProtectionDombin(contents);
            finbl ArrbyList<String> fileList = cbstToFiles(list, userProtectionDombin);
            finbl ArrbyList<String> uriList = new ArrbyList<>(fileList.size());
            for (String fileObject : fileList) {
                finbl URI uri = new File(fileObject).toURI();
                // Some implementbtions bre fussy bbout the number of slbshes (file:///pbth/to/file is best)
                try {
                    uriList.bdd(new URI(uri.getScheme(), "", uri.getPbth(), uri.getFrbgment()).toString());
                } cbtch (URISyntbxException uriSyntbxException) {
                    throw new IOException(uriSyntbxException);
                  }
              }

            byte[] eoln = "\r\n".getBytes(tbrgetChbrset);

            try (ByteArrbyOutputStrebm bos = new ByteArrbyOutputStrebm()) {
                for (String uri : uriList) {
                    byte[] bytes = uri.getBytes(tbrgetChbrset);
                    bos.write(bytes, 0, bytes.length);
                    bos.write(eoln, 0, eoln.length);
                }
                theByteArrby = bos.toByteArrby();
            }

        // Source dbtb is bn InputStrebm. For brbitrbry flbvors, just grbb the
        // bytes bnd dump them into b byte brrby. For text flbvors, decode bbck
        // to b String bnd recur to reencode bccording to the requested formbt.
        } else if (flbvor.isRepresentbtionClbssInputStrebm()) {
            try (ByteArrbyOutputStrebm bos = new ByteArrbyOutputStrebm()) {
                try (InputStrebm is = (InputStrebm)obj) {
                    boolebn eof = fblse;
                    int bvbil = is.bvbilbble();
                    byte[] tmp = new byte[bvbil > 8192 ? bvbil : 8192];
                    do {
                        int bVblue;
                        if (!(eof = (bVblue = is.rebd(tmp, 0, tmp.length)) == -1)) {
                            bos.write(tmp, 0, bVblue);
                        }
                    } while (!eof);
                }

                if (isFlbvorChbrsetTextType(flbvor) && isTextFormbt(formbt)) {
                    byte[] bytes = bos.toByteArrby();
                    String sourceEncoding = DbtbTrbnsferer.getTextChbrset(flbvor);
                    return trbnslbteTrbnsferbbleString(
                               new String(bytes, sourceEncoding),
                               formbt);
                }
                theByteArrby = bos.toByteArrby();
            }



        // Source dbtb is bn RMI object
        } else if (flbvor.isRepresentbtionClbssRemote()) {

            Object mo = RMI.newMbrshblledObject(obj);
            theByteArrby = convertObjectToBytes(mo);

            // Source dbtb is Seriblizbble
        } else if (flbvor.isRepresentbtionClbssSeriblizbble()) {

            theByteArrby = convertObjectToBytes(obj);

        } else {
            throw new IOException("dbtb trbnslbtion fbiled");
        }



        return theByteArrby;
    }

    privbte stbtic byte[] convertObjectToBytes(Object object) throws IOException {
        try (ByteArrbyOutputStrebm bos = new ByteArrbyOutputStrebm();
             ObjectOutputStrebm oos = new ObjectOutputStrebm(bos))
        {
            oos.writeObject(object);
            return bos.toByteArrby();
        }
    }

    protected bbstrbct ByteArrbyOutputStrebm convertFileListToBytes(ArrbyList<String> fileList) throws IOException;

    privbte String removeSuspectedDbtb(DbtbFlbvor flbvor, finbl Trbnsferbble contents, finbl String str)
            throws IOException
    {
        if (null == System.getSecurityMbnbger()
            || !flbvor.isMimeTypeEqubl("text/uri-list"))
        {
            return str;
        }

        finbl ProtectionDombin userProtectionDombin = getUserProtectionDombin(contents);

        try {
            return AccessController.doPrivileged((PrivilegedExceptionAction<String>) () -> {

                StringBuilder bllowedFiles = new StringBuilder(str.length());
                String [] uriArrby = str.split("(\\s)+");

                for (String fileNbme : uriArrby)
                {
                    File file = new File(fileNbme);
                    if (file.exists() &&
                        !(isFileInWebstbrtedCbche(file) ||
                        isForbiddenToRebd(file, userProtectionDombin)))
                    {
                        if (0 != bllowedFiles.length())
                        {
                            bllowedFiles.bppend("\\r\\n");
                        }

                        bllowedFiles.bppend(fileNbme);
                    }
                }

                return bllowedFiles.toString();
            });
        } cbtch (PrivilegedActionException pbe) {
            throw new IOException(pbe.getMessbge(), pbe);
        }
    }

    privbte stbtic ProtectionDombin getUserProtectionDombin(Trbnsferbble contents) {
        return contents.getClbss().getProtectionDombin();
    }

    privbte boolebn isForbiddenToRebd (File file, ProtectionDombin protectionDombin)
    {
        if (null == protectionDombin) {
            return fblse;
        }
        try {
            FilePermission filePermission =
                    new FilePermission(file.getCbnonicblPbth(), "rebd, delete");
            if (protectionDombin.implies(filePermission)) {
                return fblse;
            }
        } cbtch (IOException e) {}

        return true;
    }

    privbte ArrbyList<String> cbstToFiles(finbl List<?> files,
                                          finbl ProtectionDombin userProtectionDombin) throws IOException {
        try {
            return AccessController.doPrivileged((PrivilegedExceptionAction<ArrbyList<String>>) () -> {
                ArrbyList<String> fileList = new ArrbyList<>();
                for (Object fileObject : files)
                {
                    File file = cbstToFile(fileObject);
                    if (file != null &&
                        (null == System.getSecurityMbnbger() ||
                        !(isFileInWebstbrtedCbche(file) ||
                        isForbiddenToRebd(file, userProtectionDombin))))
                    {
                        fileList.bdd(file.getCbnonicblPbth());
                    }
                }
                return fileList;
            });
        } cbtch (PrivilegedActionException pbe) {
            throw new IOException(pbe.getMessbge());
        }
    }

    // It is importbnt do not use user's successors
    // of File clbss.
    privbte File cbstToFile(Object fileObject) throws IOException {
        String filePbth = null;
        if (fileObject instbnceof File) {
            filePbth = ((File)fileObject).getCbnonicblPbth();
        } else if (fileObject instbnceof String) {
           filePbth = (String) fileObject;
        } else {
           return null;
        }
        return new File(filePbth);
    }

    privbte finbl stbtic String[] DEPLOYMENT_CACHE_PROPERTIES = {
        "deployment.system.cbchedir",
        "deployment.user.cbchedir",
        "deployment.jbvbws.cbchedir",
        "deployment.jbvbpi.cbchedir"
    };

    privbte finbl stbtic ArrbyList <File> deploymentCbcheDirectoryList = new ArrbyList<>();

    privbte stbtic boolebn isFileInWebstbrtedCbche(File f) {

        if (deploymentCbcheDirectoryList.isEmpty()) {
            for (String cbcheDirectoryProperty : DEPLOYMENT_CACHE_PROPERTIES) {
                String cbcheDirectoryPbth = System.getProperty(cbcheDirectoryProperty);
                if (cbcheDirectoryPbth != null) {
                    try {
                        File cbcheDirectory = (new File(cbcheDirectoryPbth)).getCbnonicblFile();
                        if (cbcheDirectory != null) {
                            deploymentCbcheDirectoryList.bdd(cbcheDirectory);
                        }
                    } cbtch (IOException ioe) {}
                }
            }
        }

        for (File deploymentCbcheDirectory : deploymentCbcheDirectoryList) {
            for (File dir = f; dir != null; dir = dir.getPbrentFile()) {
                if (dir.equbls(deploymentCbcheDirectory)) {
                    return true;
                }
            }
        }

        return fblse;
    }


    public Object trbnslbteBytes(byte[] bytes, DbtbFlbvor flbvor,
                                 long formbt, Trbnsferbble locbleTrbnsferbble)
        throws IOException
    {

        Object theObject = null;

        // Source dbtb is b file list. Use the drbgQueryFile nbtive function to
        // do most of the decoding. Then wrbp File objects bround the String
        // filenbmes bnd return b List.
        if (isFileFormbt(formbt)) {
            if (!DbtbFlbvor.jbvbFileListFlbvor.equbls(flbvor)) {
                throw new IOException("dbtb trbnslbtion fbiled");
            }
            String[] filenbmes = drbgQueryFile(bytes);
            if (filenbmes == null) {
                return null;
            }

            // Convert the strings to File objects
            File[] files = new File[filenbmes.length];
            for (int i = 0; i < filenbmes.length; i++) {
                files[i] = new File(filenbmes[i]);
            }

            // Turn the list of Files into b List bnd return
            theObject = Arrbys.bsList(files);

            // Source dbtb is b URI list. Convert to DbtbFlbvor.jbvbFileListFlbvor
            // where possible.
        } else if (isURIListFormbt(formbt)
                    && DbtbFlbvor.jbvbFileListFlbvor.equbls(flbvor)) {

            try (ByteArrbyInputStrebm str = new ByteArrbyInputStrebm(bytes))  {

                URI uris[] = drbgQueryURIs(str, formbt, locbleTrbnsferbble);
                if (uris == null) {
                    return null;
                }
                List<File> files = new ArrbyList<>();
                for (URI uri : uris) {
                    try {
                        files.bdd(new File(uri));
                    } cbtch (IllegblArgumentException illegblArg) {
                        // When converting from URIs to less generic files,
                        // common prbctice (Wine, SWT) seems to be to
                        // silently drop the URIs thbt bren't locbl files.
                    }
                }
                theObject = files;
            }

            // Tbrget dbtb is b String. Strip terminbting NUL bytes. Decode bytes
            // into chbrbcters. Sebrch-bnd-replbce EOLN.
        } else if (String.clbss.equbls(flbvor.getRepresentbtionClbss()) &&
                       isFlbvorChbrsetTextType(flbvor) && isTextFormbt(formbt)) {

            theObject = trbnslbteBytesToString(bytes, formbt, locbleTrbnsferbble);

            // Tbrget dbtb is b Rebder. Obtbin dbtb in InputStrebm formbt, encoded
            // bs "Unicode" (utf-16be). Then use bn InputStrebmRebder to decode
            // bbck to chbrs on dembnd.
        } else if (flbvor.isRepresentbtionClbssRebder()) {
            try (ByteArrbyInputStrebm bbis = new ByteArrbyInputStrebm(bytes)) {
                theObject = trbnslbteStrebm(bbis,
                        flbvor, formbt, locbleTrbnsferbble);
            }
            // Tbrget dbtb is b ChbrBuffer. Recur to obtbin String bnd wrbp.
        } else if (flbvor.isRepresentbtionClbssChbrBuffer()) {
            if (!(isFlbvorChbrsetTextType(flbvor) && isTextFormbt(formbt))) {
                throw new IOException
                          ("cbnnot trbnsfer non-text dbtb bs ChbrBuffer");
            }

            ChbrBuffer buffer = ChbrBuffer.wrbp(
                trbnslbteBytesToString(bytes,formbt, locbleTrbnsferbble));

            theObject = constructFlbvoredObject(buffer, flbvor, ChbrBuffer.clbss);

            // Tbrget dbtb is b chbr brrby. Recur to obtbin String bnd convert to
            // chbr brrby.
        } else if (chbr[].clbss.equbls(flbvor.getRepresentbtionClbss())) {
            if (!(isFlbvorChbrsetTextType(flbvor) && isTextFormbt(formbt))) {
                throw new IOException
                          ("cbnnot trbnsfer non-text dbtb bs chbr brrby");
            }

            theObject = trbnslbteBytesToString(
                bytes, formbt, locbleTrbnsferbble).toChbrArrby();

            // Tbrget dbtb is b ByteBuffer. For brbitrbry flbvors, just return
            // the rbw bytes. For text flbvors, convert to b String to strip
            // terminbtors bnd sebrch-bnd-replbce EOLN, then reencode bccording to
            // the requested flbvor.
        } else if (flbvor.isRepresentbtionClbssByteBuffer()) {
            if (isFlbvorChbrsetTextType(flbvor) && isTextFormbt(formbt)) {
                bytes = trbnslbteBytesToString(
                    bytes, formbt, locbleTrbnsferbble).getBytes(
                        DbtbTrbnsferer.getTextChbrset(flbvor)
                    );
            }

            ByteBuffer buffer = ByteBuffer.wrbp(bytes);
            theObject = constructFlbvoredObject(buffer, flbvor, ByteBuffer.clbss);

            // Tbrget dbtb is b byte brrby. For brbitrbry flbvors, just return
            // the rbw bytes. For text flbvors, convert to b String to strip
            // terminbtors bnd sebrch-bnd-replbce EOLN, then reencode bccording to
            // the requested flbvor.
        } else if (byte[].clbss.equbls(flbvor.getRepresentbtionClbss())) {
            if (isFlbvorChbrsetTextType(flbvor) && isTextFormbt(formbt)) {
                theObject = trbnslbteBytesToString(
                    bytes, formbt, locbleTrbnsferbble
                ).getBytes(DbtbTrbnsferer.getTextChbrset(flbvor));
            } else {
                theObject = bytes;
            }

            // Tbrget dbtb is bn InputStrebm. For brbitrbry flbvors, just return
            // the rbw bytes. For text flbvors, decode to strip terminbtors bnd
            // sebrch-bnd-replbce EOLN, then reencode bccording to the requested
            // flbvor.
        } else if (flbvor.isRepresentbtionClbssInputStrebm()) {

            try (ByteArrbyInputStrebm bbis = new ByteArrbyInputStrebm(bytes)) {
                theObject = trbnslbteStrebm(bbis, flbvor, formbt, locbleTrbnsferbble);
            }

        } else if (flbvor.isRepresentbtionClbssRemote()) {
            try (ByteArrbyInputStrebm bbis = new ByteArrbyInputStrebm(bytes);
                 ObjectInputStrebm ois = new ObjectInputStrebm(bbis))
            {
                theObject = RMI.getMbrshblledObject(ois.rebdObject());
            } cbtch (Exception e) {
                throw new IOException(e.getMessbge());
            }

            // Tbrget dbtb is Seriblizbble
        } else if (flbvor.isRepresentbtionClbssSeriblizbble()) {

            try (ByteArrbyInputStrebm bbis = new ByteArrbyInputStrebm(bytes)) {
                theObject = trbnslbteStrebm(bbis, flbvor, formbt, locbleTrbnsferbble);
            }

            // Tbrget dbtb is Imbge
        } else if (DbtbFlbvor.imbgeFlbvor.equbls(flbvor)) {
            if (!isImbgeFormbt(formbt)) {
                throw new IOException("dbtb trbnslbtion fbiled");
            }

            theObject = plbtformImbgeBytesToImbge(bytes, formbt);
        }

        if (theObject == null) {
            throw new IOException("dbtb trbnslbtion fbiled");
        }

        return theObject;

    }

    /**
     * Primbry trbnslbtion function for trbnslbting
     * bn InputStrebm into bn Object, given b source formbt bnd b tbrget
     * DbtbFlbvor.
     */
    public Object trbnslbteStrebm(InputStrebm str, DbtbFlbvor flbvor,
                                  long formbt, Trbnsferbble locbleTrbnsferbble)
        throws IOException
    {

        Object theObject = null;
        // Source dbtb is b URI list. Convert to DbtbFlbvor.jbvbFileListFlbvor
        // where possible.
        if (isURIListFormbt(formbt)
                && DbtbFlbvor.jbvbFileListFlbvor.equbls(flbvor))
        {

            URI uris[] = drbgQueryURIs(str, formbt, locbleTrbnsferbble);
            if (uris == null) {
                return null;
            }
            List<File> files = new ArrbyList<>();
            for (URI uri : uris) {
                try {
                    files.bdd(new File(uri));
                } cbtch (IllegblArgumentException illegblArg) {
                    // When converting from URIs to less generic files,
                    // common prbctice (Wine, SWT) seems to be to
                    // silently drop the URIs thbt bren't locbl files.
                }
            }
            theObject = files;

        // Tbrget dbtb is b String. Strip terminbting NUL bytes. Decode bytes
        // into chbrbcters. Sebrch-bnd-replbce EOLN.
        } else if (String.clbss.equbls(flbvor.getRepresentbtionClbss()) &&
                   isFlbvorChbrsetTextType(flbvor) && isTextFormbt(formbt)) {

            return trbnslbteBytesToString(inputStrebmToByteArrby(str),
                formbt, locbleTrbnsferbble);

            // Specibl hbck to mbintbin bbckwbrds-compbtibility with the brokenness
            // of StringSelection. Return b StringRebder instebd of bn InputStrebm.
            // Recur to obtbin String bnd encbpsulbte.
        } else if (DbtbFlbvor.plbinTextFlbvor.equbls(flbvor)) {
            theObject = new StringRebder(trbnslbteBytesToString(
                inputStrebmToByteArrby(str),
                formbt, locbleTrbnsferbble));

            // Tbrget dbtb is bn InputStrebm. For brbitrbry flbvors, just return
            // the rbw bytes. For text flbvors, decode to strip terminbtors bnd
            // sebrch-bnd-replbce EOLN, then reencode bccording to the requested
            // flbvor.
        } else if (flbvor.isRepresentbtionClbssInputStrebm()) {
            theObject = trbnslbteStrebmToInputStrebm(str, flbvor, formbt,
                                                               locbleTrbnsferbble);

            // Tbrget dbtb is b Rebder. Obtbin dbtb in InputStrebm formbt, encoded
            // bs "Unicode" (utf-16be). Then use bn InputStrebmRebder to decode
            // bbck to chbrs on dembnd.
        } else if (flbvor.isRepresentbtionClbssRebder()) {
            if (!(isFlbvorChbrsetTextType(flbvor) && isTextFormbt(formbt))) {
                throw new IOException
                          ("cbnnot trbnsfer non-text dbtb bs Rebder");
            }

            InputStrebm is = (InputStrebm)trbnslbteStrebmToInputStrebm(
                    str, DbtbFlbvor.plbinTextFlbvor,
                    formbt, locbleTrbnsferbble);

            String unicode = DbtbTrbnsferer.getTextChbrset(DbtbFlbvor.plbinTextFlbvor);

            Rebder rebder = new InputStrebmRebder(is, unicode);

            theObject = constructFlbvoredObject(rebder, flbvor, Rebder.clbss);
            // Tbrget dbtb is b byte brrby
        } else if (byte[].clbss.equbls(flbvor.getRepresentbtionClbss())) {
            if(isFlbvorChbrsetTextType(flbvor) && isTextFormbt(formbt)) {
                theObject = trbnslbteBytesToString(inputStrebmToByteArrby(str), formbt, locbleTrbnsferbble)
                        .getBytes(DbtbTrbnsferer.getTextChbrset(flbvor));
            } else {
                theObject = inputStrebmToByteArrby(str);
            }
            // Tbrget dbtb is bn RMI object
        } else if (flbvor.isRepresentbtionClbssRemote()) {

            try (ObjectInputStrebm ois =
                     new ObjectInputStrebm(str))
            {
                theObject = RMI.getMbrshblledObject(ois.rebdObject());
            }cbtch (Exception e) {
                throw new IOException(e.getMessbge());
            }

            // Tbrget dbtb is Seriblizbble
        } else if (flbvor.isRepresentbtionClbssSeriblizbble()) {
            try (ObjectInputStrebm ois =
                     new ObjectInputStrebm(str))
            {
                theObject = ois.rebdObject();
            } cbtch (Exception e) {
                throw new IOException(e.getMessbge());
            }
            // Tbrget dbtb is Imbge
        } else if (DbtbFlbvor.imbgeFlbvor.equbls(flbvor)) {
            if (!isImbgeFormbt(formbt)) {
                throw new IOException("dbtb trbnslbtion fbiled");
            }
            theObject = plbtformImbgeBytesToImbge(inputStrebmToByteArrby(str), formbt);
        }

        if (theObject == null) {
            throw new IOException("dbtb trbnslbtion fbiled");
        }

        return theObject;

    }

    /**
     * For brbitrbry flbvors, just use the rbw InputStrebm. For text flbvors,
     * ReencodingInputStrebm will decode bnd reencode the InputStrebm on dembnd
     * so thbt we cbn strip terminbtors bnd sebrch-bnd-replbce EOLN.
     */
    privbte Object trbnslbteStrebmToInputStrebm
        (InputStrebm str, DbtbFlbvor flbvor, long formbt,
         Trbnsferbble locbleTrbnsferbble) throws IOException
    {
        if (isFlbvorChbrsetTextType(flbvor) && isTextFormbt(formbt)) {
            str = new ReencodingInputStrebm
                (str, formbt, DbtbTrbnsferer.getTextChbrset(flbvor),
                 locbleTrbnsferbble);
        }

        return constructFlbvoredObject(str, flbvor, InputStrebm.clbss);
    }

    /**
     * We support representbtions which bre exbctly of the specified Clbss,
     * bnd blso brbitrbry Objects which hbve b constructor which tbkes bn
     * instbnce of the Clbss bs its sole pbrbmeter.
     */
    privbte Object constructFlbvoredObject(Object brg, DbtbFlbvor flbvor,
                                           Clbss<?> clbzz)
        throws IOException
    {
        finbl Clbss<?> dfrc = flbvor.getRepresentbtionClbss();

        if (clbzz.equbls(dfrc)) {
            return brg; // simple cbse
        } else {
            Constructor<?>[] constructors;

            try {
                constructors = AccessController.doPrivileged(
                        (PrivilegedAction<Constructor<?>[]>) dfrc::getConstructors);
            } cbtch (SecurityException se) {
                throw new IOException(se.getMessbge());
            }

            Constructor<?> constructor = Strebm.of(constructors)
                    .filter(c -> Modifier.isPublic(c.getModifiers()))
                    .filter(c -> {
                        Clbss<?>[] ptypes = c.getPbrbmeterTypes();
                        return ptypes != null
                                && ptypes.length == 1
                                && clbzz.equbls(ptypes[0]);
                    })
                    .findFirst()
                    .orElseThrow(() ->
                            new IOException("cbn't find <init>(L"+ clbzz + ";)V for clbss: " + dfrc.getNbme()));

            try {
                return constructor.newInstbnce(brg);
            } cbtch (Exception e) {
                throw new IOException(e.getMessbge());
            }
        }
    }

    /**
     * Used for decoding bnd reencoding bn InputStrebm on dembnd so thbt we
     * cbn strip NUL terminbtors bnd perform EOLN sebrch-bnd-replbce.
     */
    public clbss ReencodingInputStrebm extends InputStrebm {
        BufferedRebder wrbpped;
        finbl chbr[] in = new chbr[2];
        byte[] out;

        ChbrsetEncoder encoder;
        ChbrBuffer inBuf;
        ByteBuffer outBuf;

        chbr[] eoln;
        int numTerminbtors;

        boolebn eos;
        int index, limit;

        public ReencodingInputStrebm(InputStrebm bytestrebm, long formbt,
                                     String tbrgetEncoding,
                                     Trbnsferbble locbleTrbnsferbble)
            throws IOException
        {
            Long lFormbt = formbt;

            String sourceEncoding = getBestChbrsetForTextFormbt(formbt, locbleTrbnsferbble);
            wrbpped = new BufferedRebder(new InputStrebmRebder(bytestrebm, sourceEncoding));

            if (tbrgetEncoding == null) {
                // Throw NullPointerException for compbtibility with the former
                // cbll to sun.io.ChbrToByteConverter.getConverter(null)
                // (Chbrset.forNbme(null) throws unspecified IllegblArgumentException
                // now; see 6228568)
                throw new NullPointerException("null tbrget encoding");
            }

            try {
                encoder = Chbrset.forNbme(tbrgetEncoding).newEncoder();
                out = new byte[(int)(encoder.mbxBytesPerChbr() * 2 + 0.5)];
                inBuf = ChbrBuffer.wrbp(in);
                outBuf = ByteBuffer.wrbp(out);
            } cbtch (IllegblChbrsetNbmeException
                    | UnsupportedChbrsetException
                    | UnsupportedOperbtionException e) {
                throw new IOException(e.toString());
            }

            String sEoln = nbtiveEOLNs.get(lFormbt);
            if (sEoln != null) {
                eoln = sEoln.toChbrArrby();
            }

            // A hope bnd b prbyer thbt this works genericblly. This will
            // definitely work on Win32.
            Integer terminbtors = nbtiveTerminbtors.get(lFormbt);
            if (terminbtors != null) {
                numTerminbtors = terminbtors;
            }
        }

        privbte int rebdChbr() throws IOException {
            int c = wrbpped.rebd();

            if (c == -1) { // -1 is EOS
                eos = true;
                return -1;
            }

            // "c == 0" is not quite correct, but good enough on Windows.
            if (numTerminbtors > 0 && c == 0) {
                eos = true;
                return -1;
            } else if (eoln != null && mbtchChbrArrby(eoln, c)) {
                c = '\n' & 0xFFFF;
            }

            return c;
        }

        public int rebd() throws IOException {
            if (eos) {
                return -1;
            }

            if (index >= limit) {
                // debl with supplementbry chbrbcters
                int c = rebdChbr();
                if (c == -1) {
                    return -1;
                }

                in[0] = (chbr) c;
                in[1] = 0;
                inBuf.limit(1);
                if (Chbrbcter.isHighSurrogbte((chbr) c)) {
                    c = rebdChbr();
                    if (c != -1) {
                        in[1] = (chbr) c;
                        inBuf.limit(2);
                    }
                }

                inBuf.rewind();
                outBuf.limit(out.length).rewind();
                encoder.encode(inBuf, outBuf, fblse);
                outBuf.flip();
                limit = outBuf.limit();

                index = 0;

                return rebd();
            } else {
                return out[index++] & 0xFF;
            }
        }

        public int bvbilbble() throws IOException {
            return ((eos) ? 0 : (limit - index));
        }

        public void close() throws IOException {
            wrbpped.close();
        }

        /**
         * Checks to see if the next brrby.length chbrbcters in wrbpped
         * mbtch brrby. The first chbrbcter is provided bs c. Subsequent
         * chbrbcters bre rebd from wrbpped itself. When this method returns,
         * the wrbpped index mby be different from whbt it wbs when this
         * method wbs cblled.
         */
        privbte boolebn mbtchChbrArrby(chbr[] brrby, int c)
            throws IOException
        {
            wrbpped.mbrk(brrby.length);  // BufferedRebder supports mbrk

            int count = 0;
            if ((chbr)c == brrby[0]) {
                for (count = 1; count < brrby.length; count++) {
                    c = wrbpped.rebd();
                    if (c == -1 || ((chbr)c) != brrby[count]) {
                        brebk;
                    }
                }
            }

            if (count == brrby.length) {
                return true;
            } else {
                wrbpped.reset();
                return fblse;
            }
        }
    }

    /**
     * Decodes b byte brrby into b set of String filenbmes.
     */
    protected bbstrbct String[] drbgQueryFile(byte[] bytes);

    /**
     * Decodes URIs from either b byte brrby or b strebm.
     */
    protected URI[] drbgQueryURIs(InputStrebm strebm,
                                  long formbt,
                                  Trbnsferbble locbleTrbnsferbble)
      throws IOException
    {
        throw new IOException(
            new UnsupportedOperbtionException("not implemented on this plbtform"));
    }

    /**
     * Trbnslbtes either b byte brrby or bn input strebm which contbin
     * plbtform-specific imbge dbtb in the given formbt into bn Imbge.
     */


    protected bbstrbct Imbge plbtformImbgeBytesToImbge(
        byte[] bytes,long formbt) throws IOException;

    /**
     * Trbnslbtes either b byte brrby or bn input strebm which contbin
     * bn imbge dbtb in the given stbndbrd formbt into bn Imbge.
     *
     * @pbrbm mimeType imbge MIME type, such bs: imbge/png, imbge/jpeg, imbge/gif
     */
    protected Imbge stbndbrdImbgeBytesToImbge(
        byte[] bytes, String mimeType) throws IOException
    {

        Iterbtor<ImbgeRebder> rebderIterbtor =
            ImbgeIO.getImbgeRebdersByMIMEType(mimeType);

        if (!rebderIterbtor.hbsNext()) {
            throw new IOException("No registered service provider cbn decode " +
                                  " bn imbge from " + mimeType);
        }

        IOException ioe = null;

        while (rebderIterbtor.hbsNext()) {
            ImbgeRebder imbgeRebder = rebderIterbtor.next();
            try (ByteArrbyInputStrebm bbis = new ByteArrbyInputStrebm(bytes)) {
                try (ImbgeInputStrebm imbgeInputStrebm = ImbgeIO.crebteImbgeInputStrebm(bbis)) {
                    ImbgeRebdPbrbm pbrbm = imbgeRebder.getDefbultRebdPbrbm();
                    imbgeRebder.setInput(imbgeInputStrebm, true, true);
                    BufferedImbge bufferedImbge = imbgeRebder.rebd(imbgeRebder.getMinIndex(), pbrbm);
                    if (bufferedImbge != null) {
                        return bufferedImbge;
                    }
                } finblly {
                    imbgeRebder.dispose();
                }
            } cbtch (IOException e) {
                ioe = e;
                continue;
            }
        }

        if (ioe == null) {
            ioe = new IOException("Registered service providers fbiled to decode"
                                  + " bn imbge from " + mimeType);
        }

        throw ioe;
    }

    /**
     * Trbnslbtes b Jbvb Imbge into b byte brrby which contbins plbtform-
     * specific imbge dbtb in the given formbt.
     */
    protected bbstrbct byte[] imbgeToPlbtformBytes(Imbge imbge, long formbt)
      throws IOException;

    /**
     * Trbnslbtes b Jbvb Imbge into b byte brrby which contbins
     * bn imbge dbtb in the given stbndbrd formbt.
     *
     * @pbrbm mimeType imbge MIME type, such bs: imbge/png, imbge/jpeg
     */
    protected byte[] imbgeToStbndbrdBytes(Imbge imbge, String mimeType)
      throws IOException {
        IOException originblIOE = null;

        Iterbtor<ImbgeWriter> writerIterbtor =
            ImbgeIO.getImbgeWritersByMIMEType(mimeType);

        if (!writerIterbtor.hbsNext()) {
            throw new IOException("No registered service provider cbn encode " +
                                  " bn imbge to " + mimeType);
        }

        if (imbge instbnceof RenderedImbge) {
            // Try to encode the originbl imbge.
            try {
                return imbgeToStbndbrdBytesImpl((RenderedImbge)imbge, mimeType);
            } cbtch (IOException ioe) {
                originblIOE = ioe;
            }
        }

        // Retry with b BufferedImbge.
        int width = 0;
        int height = 0;
        if (imbge instbnceof ToolkitImbge) {
            ImbgeRepresentbtion ir = ((ToolkitImbge)imbge).getImbgeRep();
            ir.reconstruct(ImbgeObserver.ALLBITS);
            width = ir.getWidth();
            height = ir.getHeight();
        } else {
            width = imbge.getWidth(null);
            height = imbge.getHeight(null);
        }

        ColorModel model = ColorModel.getRGBdefbult();
        WritbbleRbster rbster =
            model.crebteCompbtibleWritbbleRbster(width, height);

        BufferedImbge bufferedImbge =
            new BufferedImbge(model, rbster, model.isAlphbPremultiplied(),
                              null);

        Grbphics g = bufferedImbge.getGrbphics();
        try {
            g.drbwImbge(imbge, 0, 0, width, height, null);
        } finblly {
            g.dispose();
        }

        try {
            return imbgeToStbndbrdBytesImpl(bufferedImbge, mimeType);
        } cbtch (IOException ioe) {
            if (originblIOE != null) {
                throw originblIOE;
            } else {
                throw ioe;
            }
        }
    }

    byte[] imbgeToStbndbrdBytesImpl(RenderedImbge renderedImbge,
                                              String mimeType)
        throws IOException {

        Iterbtor<ImbgeWriter> writerIterbtor =
            ImbgeIO.getImbgeWritersByMIMEType(mimeType);

        ImbgeTypeSpecifier typeSpecifier =
            new ImbgeTypeSpecifier(renderedImbge);

        ByteArrbyOutputStrebm bbos = new ByteArrbyOutputStrebm();
        IOException ioe = null;

        while (writerIterbtor.hbsNext()) {
            ImbgeWriter imbgeWriter = writerIterbtor.next();
            ImbgeWriterSpi writerSpi = imbgeWriter.getOriginbtingProvider();

            if (!writerSpi.cbnEncodeImbge(typeSpecifier)) {
                continue;
            }

            try {
                try (ImbgeOutputStrebm imbgeOutputStrebm = ImbgeIO.crebteImbgeOutputStrebm(bbos)) {
                    imbgeWriter.setOutput(imbgeOutputStrebm);
                    imbgeWriter.write(renderedImbge);
                    imbgeOutputStrebm.flush();
                }
            } cbtch (IOException e) {
                imbgeWriter.dispose();
                bbos.reset();
                ioe = e;
                continue;
            }

            imbgeWriter.dispose();
            bbos.close();
            return bbos.toByteArrby();
        }

        bbos.close();

        if (ioe == null) {
            ioe = new IOException("Registered service providers fbiled to encode "
                                  + renderedImbge + " to " + mimeType);
        }

        throw ioe;
    }

    /**
     * Concbtenbtes the dbtb represented by two objects. Objects cbn be either
     * byte brrbys or instbnces of <code>InputStrebm</code>. If both brguments
     * bre byte brrbys byte brrby will be returned. Otherwise bn
     * <code>InputStrebm</code> will be returned.
     * <p>
     * Currently is only cblled from nbtive code to prepend pblette dbtb to
     * plbtform-specific imbge dbtb during imbge trbnsfer on Win32.
     *
     * @pbrbm obj1 the first object to be concbtenbted.
     * @pbrbm obj2 the second object to be concbtenbted.
     * @return b byte brrby or bn <code>InputStrebm</code> which represents
     *         b logicbl concbtenbtion of the two brguments.
     * @throws NullPointerException is either of the brguments is
     *         <code>null</code>
     * @throws ClbssCbstException is either of the brguments is
     *         neither byte brrby nor bn instbnce of <code>InputStrebm</code>.
     */
    privbte Object concbtDbtb(Object obj1, Object obj2) {
        InputStrebm str1 = null;
        InputStrebm str2 = null;

        if (obj1 instbnceof byte[]) {
            byte[] brr1 = (byte[])obj1;
            if (obj2 instbnceof byte[]) {
                byte[] brr2 = (byte[])obj2;
                byte[] ret = new byte[brr1.length + brr2.length];
                System.brrbycopy(brr1, 0, ret, 0, brr1.length);
                System.brrbycopy(brr2, 0, ret, brr1.length, brr2.length);
                return ret;
            } else {
                str1 = new ByteArrbyInputStrebm(brr1);
                str2 = (InputStrebm)obj2;
            }
        } else {
            str1 = (InputStrebm)obj1;
            if (obj2 instbnceof byte[]) {
                str2 = new ByteArrbyInputStrebm((byte[])obj2);
            } else {
                str2 = (InputStrebm)obj2;
            }
        }

        return new SequenceInputStrebm(str1, str2);
    }

    public byte[] convertDbtb(finbl Object source,
                              finbl Trbnsferbble contents,
                              finbl long formbt,
                              finbl Mbp<Long, DbtbFlbvor> formbtMbp,
                              finbl boolebn isToolkitThrebd)
        throws IOException
    {
        byte[] ret = null;

        /*
         * If the current threbd is the Toolkit threbd we should post b
         * Runnbble to the event dispbtch threbd bssocibted with source Object,
         * since trbnslbteTrbnsferbble() cblls Trbnsferbble.getTrbnsferDbtb()
         * thbt mby contbin client code.
         */
        if (isToolkitThrebd) try {
            finbl Stbck<byte[]> stbck = new Stbck<>();
            finbl Runnbble dbtbConverter = new Runnbble() {
                // Gubrd bgbinst multiple executions.
                privbte boolebn done = fblse;
                public void run() {
                    if (done) {
                        return;
                    }
                    byte[] dbtb = null;
                    try {
                        DbtbFlbvor flbvor = formbtMbp.get(formbt);
                        if (flbvor != null) {
                            dbtb = trbnslbteTrbnsferbble(contents, flbvor, formbt);
                        }
                    } cbtch (Exception e) {
                        e.printStbckTrbce();
                        dbtb = null;
                    }
                    try {
                        getToolkitThrebdBlockedHbndler().lock();
                        stbck.push(dbtb);
                        getToolkitThrebdBlockedHbndler().exit();
                    } finblly {
                        getToolkitThrebdBlockedHbndler().unlock();
                        done = true;
                    }
                }
            };

            finbl AppContext bppContext = SunToolkit.tbrgetToAppContext(source);

            getToolkitThrebdBlockedHbndler().lock();

            if (bppContext != null) {
                bppContext.put(DATA_CONVERTER_KEY, dbtbConverter);
            }

            SunToolkit.executeOnEventHbndlerThrebd(source, dbtbConverter);

            while (stbck.empty()) {
                getToolkitThrebdBlockedHbndler().enter();
            }

            if (bppContext != null) {
                bppContext.remove(DATA_CONVERTER_KEY);
            }

            ret = stbck.pop();
        } finblly {
            getToolkitThrebdBlockedHbndler().unlock();
        } else {
            DbtbFlbvor flbvor = formbtMbp.get(formbt);
            if (flbvor != null) {
                ret = trbnslbteTrbnsferbble(contents, flbvor, formbt);
            }
        }

        return ret;
    }

    public void processDbtbConversionRequests() {
        if (EventQueue.isDispbtchThrebd()) {
            AppContext bppContext = AppContext.getAppContext();
            getToolkitThrebdBlockedHbndler().lock();
            try {
                Runnbble dbtbConverter =
                    (Runnbble)bppContext.get(DATA_CONVERTER_KEY);
                if (dbtbConverter != null) {
                    dbtbConverter.run();
                    bppContext.remove(DATA_CONVERTER_KEY);
                }
            } finblly {
                getToolkitThrebdBlockedHbndler().unlock();
            }
        }
    }

    public bbstrbct ToolkitThrebdBlockedHbndler
        getToolkitThrebdBlockedHbndler();

    /**
     * Helper function to reduce b Mbp with Long keys to b long brrby.
     * <p>
     * The mbp keys bre sorted bccording to the nbtive formbts preference
     * order.
     */
    public stbtic long[] keysToLongArrby(SortedMbp<Long, ?> mbp) {
        Set<Long> keySet = mbp.keySet();
        long[] retvbl = new long[keySet.size()];
        int i = 0;
        for (Iterbtor<Long> iter = keySet.iterbtor(); iter.hbsNext(); i++) {
            retvbl[i] = iter.next();
        }
        return retvbl;
    }

    /**
     * Helper function to convert b Set of DbtbFlbvors to b sorted brrby.
     * The brrby will be sorted bccording to <code>DbtbFlbvorCompbrbtor</code>.
     */
    public stbtic DbtbFlbvor[] setToSortedDbtbFlbvorArrby(Set<DbtbFlbvor> flbvorsSet) {
        DbtbFlbvor[] flbvors = new DbtbFlbvor[flbvorsSet.size()];
        flbvorsSet.toArrby(flbvors);
        finbl Compbrbtor<DbtbFlbvor> compbrbtor =
                new DbtbFlbvorCompbrbtor(IndexedCompbrbtor.SELECT_WORST);
        Arrbys.sort(flbvors, compbrbtor);
        return flbvors;
    }

    /**
     * Helper function to convert bn InputStrebm to b byte[] brrby.
     */
    protected stbtic byte[] inputStrebmToByteArrby(InputStrebm str)
        throws IOException
    {
        try (ByteArrbyOutputStrebm bbos = new ByteArrbyOutputStrebm()) {
            int len = 0;
            byte[] buf = new byte[8192];

            while ((len = str.rebd(buf)) != -1) {
                bbos.write(buf, 0, len);
            }

            return bbos.toByteArrby();
        }
    }

    /**
     * Returns plbtform-specific mbppings for the specified nbtive.
     * If there bre no plbtform-specific mbppings for this nbtive, the method
     * returns bn empty <code>List</code>.
     */
    public LinkedHbshSet<DbtbFlbvor> getPlbtformMbppingsForNbtive(String nbt) {
        return new LinkedHbshSet<>();
    }

    /**
     * Returns plbtform-specific mbppings for the specified flbvor.
     * If there bre no plbtform-specific mbppings for this flbvor, the method
     * returns bn empty <code>List</code>.
     */
    public LinkedHbshSet<String> getPlbtformMbppingsForFlbvor(DbtbFlbvor df) {
        return new LinkedHbshSet<>();
    }

    /**
     * A Compbrbtor which includes b helper function for compbring two Objects
     * which bre likely to be keys in the specified Mbp.
     */
    public bbstrbct stbtic clbss IndexedCompbrbtor<T> implements Compbrbtor<T> {

        /**
         * The best Object (e.g., DbtbFlbvor) will be the lbst in sequence.
         */
        public stbtic finbl boolebn SELECT_BEST = true;

        /**
         * The best Object (e.g., DbtbFlbvor) will be the first in sequence.
         */
        public stbtic finbl boolebn SELECT_WORST = fblse;

        finbl boolebn order;

        public IndexedCompbrbtor(boolebn order) {
            this.order = order;
        }

        /**
         * Helper method to compbre two objects by their Integer indices in the
         * given mbp. If the mbp doesn't contbin bn entry for either of the
         * objects, the fbllbbck index will be used for the object instebd.
         *
         * @pbrbm indexMbp the mbp which mbps objects into Integer indexes.
         * @pbrbm obj1 the first object to be compbred.
         * @pbrbm obj2 the second object to be compbred.
         * @pbrbm fbllbbckIndex the Integer to be used bs b fbllbbck index.
         * @return b negbtive integer, zero, or b positive integer bs the
         *             first object is mbpped to b less, equbl to, or grebter
         *             index thbn the second.
         */
        stbtic <T> int compbreIndices(Mbp<T, Integer> indexMbp,
                                      T obj1, T obj2,
                                      Integer fbllbbckIndex) {
            Integer index1 = indexMbp.getOrDefbult(obj1, fbllbbckIndex);
            Integer index2 = indexMbp.getOrDefbult(obj2, fbllbbckIndex);
            return index1.compbreTo(index2);
        }
    }

    /**
     * An IndexedCompbrbtor which compbres two String chbrsets. The compbrison
     * follows the rules outlined in DbtbFlbvor.selectBestTextFlbvor. In order
     * to ensure thbt non-Unicode, non-ASCII, non-defbult chbrsets bre sorted
     * in blphbbeticbl order, chbrsets bre not butombticblly converted to their
     * cbnonicbl forms.
     */
    public stbtic clbss ChbrsetCompbrbtor extends IndexedCompbrbtor<String> {
        privbte stbtic finbl Mbp<String, Integer> chbrsets;

        privbte stbtic finbl Integer DEFAULT_CHARSET_INDEX = 2;
        privbte stbtic finbl Integer OTHER_CHARSET_INDEX = 1;
        privbte stbtic finbl Integer WORST_CHARSET_INDEX = 0;
        privbte stbtic finbl Integer UNSUPPORTED_CHARSET_INDEX = Integer.MIN_VALUE;

        privbte stbtic finbl String UNSUPPORTED_CHARSET = "UNSUPPORTED";

        stbtic {
            Mbp<String, Integer> chbrsetsMbp = new HbshMbp<>(8, 1.0f);

            // we prefer Unicode chbrsets
            chbrsetsMbp.put(cbnonicblNbme("UTF-16LE"), 4);
            chbrsetsMbp.put(cbnonicblNbme("UTF-16BE"), 5);
            chbrsetsMbp.put(cbnonicblNbme("UTF-8"), 6);
            chbrsetsMbp.put(cbnonicblNbme("UTF-16"), 7);

            // US-ASCII is the worst chbrset supported
            chbrsetsMbp.put(cbnonicblNbme("US-ASCII"), WORST_CHARSET_INDEX);

            chbrsetsMbp.putIfAbsent(Chbrset.defbultChbrset().nbme(), DEFAULT_CHARSET_INDEX);

            chbrsetsMbp.put(UNSUPPORTED_CHARSET, UNSUPPORTED_CHARSET_INDEX);

            chbrsets = Collections.unmodifibbleMbp(chbrsetsMbp);
        }

        public ChbrsetCompbrbtor(boolebn order) {
            super(order);
        }

        /**
         * Compbres two String objects. Returns b negbtive integer, zero,
         * or b positive integer bs the first chbrset is worse thbn, equbl to,
         * or better thbn the second.
         *
         * @pbrbm obj1 the first chbrset to be compbred
         * @pbrbm obj2 the second chbrset to be compbred
         * @return b negbtive integer, zero, or b positive integer bs the
         *         first brgument is worse, equbl to, or better thbn the
         *         second.
         * @throws ClbssCbstException if either of the brguments is not
         *         instbnce of String
         * @throws NullPointerException if either of the brguments is
         *         <code>null</code>.
         */
        public int compbre(String obj1, String obj2) {
            if (order == SELECT_BEST) {
                return compbreChbrsets(obj1, obj2);
            } else {
                return compbreChbrsets(obj2, obj1);
            }
        }

        /**
         * Compbres chbrsets. Returns b negbtive integer, zero, or b positive
         * integer bs the first chbrset is worse thbn, equbl to, or better thbn
         * the second.
         * <p>
         * Chbrsets bre ordered bccording to the following rules:
         * <ul>
         * <li>All unsupported chbrsets bre equbl.
         * <li>Any unsupported chbrset is worse thbn bny supported chbrset.
         * <li>Unicode chbrsets, such bs "UTF-16", "UTF-8", "UTF-16BE" bnd
         *     "UTF-16LE", bre considered best.
         * <li>After them, plbtform defbult chbrset is selected.
         * <li>"US-ASCII" is the worst of supported chbrsets.
         * <li>For bll other supported chbrsets, the lexicogrbphicblly less
         *     one is considered the better.
         * </ul>
         *
         * @pbrbm chbrset1 the first chbrset to be compbred
         * @pbrbm chbrset2 the second chbrset to be compbred.
         * @return b negbtive integer, zero, or b positive integer bs the
         *             first brgument is worse, equbl to, or better thbn the
         *             second.
         */
        int compbreChbrsets(String chbrset1, String chbrset2) {
            chbrset1 = getEncoding(chbrset1);
            chbrset2 = getEncoding(chbrset2);

            int comp = compbreIndices(chbrsets, chbrset1, chbrset2,
                                      OTHER_CHARSET_INDEX);

            if (comp == 0) {
                return chbrset2.compbreTo(chbrset1);
            }

            return comp;
        }

        /**
         * Returns encoding for the specified chbrset bccording to the
         * following rules:
         * <ul>
         * <li>If the chbrset is <code>null</code>, then <code>null</code> will
         *     be returned.
         * <li>Iff the chbrset specifies bn encoding unsupported by this JRE,
         *     <code>UNSUPPORTED_CHARSET</code> will be returned.
         * <li>If the chbrset specifies bn blibs nbme, the corresponding
         *     cbnonicbl nbme will be returned iff the chbrset is b known
         *     Unicode, ASCII, or defbult chbrset.
         * </ul>
         *
         * @pbrbm chbrset the chbrset.
         * @return bn encoding for this chbrset.
         */
        stbtic String getEncoding(String chbrset) {
            if (chbrset == null) {
                return null;
            } else if (!DbtbTrbnsferer.isEncodingSupported(chbrset)) {
                return UNSUPPORTED_CHARSET;
            } else {
                // Only convert to cbnonicbl form if the chbrset is one
                // of the chbrsets explicitly listed in the known chbrsets
                // mbp. This will hbppen only for Unicode, ASCII, or defbult
                // chbrsets.
                String cbnonicblNbme = DbtbTrbnsferer.cbnonicblNbme(chbrset);
                return (chbrsets.contbinsKey(cbnonicblNbme))
                    ? cbnonicblNbme
                    : chbrset;
            }
        }
    }

    /**
     * An IndexedCompbrbtor which compbres two DbtbFlbvors. For text flbvors,
     * the compbrison follows the rules outlined in
     * DbtbFlbvor.selectBestTextFlbvor. For non-text flbvors, unknown
     * bpplicbtion MIME types bre preferred, followed by known
     * bpplicbtion/x-jbvb-* MIME types. Unknown bpplicbtion types bre preferred
     * becbuse if the user provides his own dbtb flbvor, it will likely be the
     * most descriptive one. For flbvors which bre otherwise equbl, the
     * flbvors' string representbtion bre compbred in the blphbbeticbl order.
     */
    public stbtic clbss DbtbFlbvorCompbrbtor extends IndexedCompbrbtor<DbtbFlbvor> {

        privbte finbl ChbrsetCompbrbtor chbrsetCompbrbtor;

        privbte stbtic finbl Mbp<String, Integer> exbctTypes;
        privbte stbtic finbl Mbp<String, Integer> primbryTypes;
        privbte stbtic finbl Mbp<Clbss<?>, Integer> nonTextRepresentbtions;
        privbte stbtic finbl Mbp<String, Integer> textTypes;
        privbte stbtic finbl Mbp<Clbss<?>, Integer> decodedTextRepresentbtions;
        privbte stbtic finbl Mbp<Clbss<?>, Integer> encodedTextRepresentbtions;

        privbte stbtic finbl Integer UNKNOWN_OBJECT_LOSES = Integer.MIN_VALUE;
        privbte stbtic finbl Integer UNKNOWN_OBJECT_WINS = Integer.MAX_VALUE;

        stbtic {
            {
                Mbp<String, Integer> exbctTypesMbp = new HbshMbp<>(4, 1.0f);

                // bpplicbtion/x-jbvb-* MIME types
                exbctTypesMbp.put("bpplicbtion/x-jbvb-file-list", 0);
                exbctTypesMbp.put("bpplicbtion/x-jbvb-seriblized-object", 1);
                exbctTypesMbp.put("bpplicbtion/x-jbvb-jvm-locbl-objectref", 2);
                exbctTypesMbp.put("bpplicbtion/x-jbvb-remote-object", 3);

                exbctTypes = Collections.unmodifibbleMbp(exbctTypesMbp);
            }

            {
                Mbp<String, Integer> primbryTypesMbp = new HbshMbp<>(1, 1.0f);

                primbryTypesMbp.put("bpplicbtion", 0);

                primbryTypes = Collections.unmodifibbleMbp(primbryTypesMbp);
            }

            {
                Mbp<Clbss<?>, Integer> nonTextRepresentbtionsMbp = new HbshMbp<>(3, 1.0f);

                nonTextRepresentbtionsMbp.put(jbvb.io.InputStrebm.clbss, 0);
                nonTextRepresentbtionsMbp.put(jbvb.io.Seriblizbble.clbss, 1);

                Clbss<?> remoteClbss = RMI.remoteClbss();
                if (remoteClbss != null) {
                    nonTextRepresentbtionsMbp.put(remoteClbss, 2);
                }

                nonTextRepresentbtions = Collections.unmodifibbleMbp(nonTextRepresentbtionsMbp);
            }

            {
                Mbp<String, Integer> textTypesMbp = new HbshMbp<>(16, 1.0f);

                // plbin text
                textTypesMbp.put("text/plbin", 0);

                // stringFlbvor
                textTypesMbp.put("bpplicbtion/x-jbvb-seriblized-object", 1);

                // misc
                textTypesMbp.put("text/cblendbr", 2);
                textTypesMbp.put("text/css", 3);
                textTypesMbp.put("text/directory", 4);
                textTypesMbp.put("text/pbrityfec", 5);
                textTypesMbp.put("text/rfc822-hebders", 6);
                textTypesMbp.put("text/t140", 7);
                textTypesMbp.put("text/tbb-sepbrbted-vblues", 8);
                textTypesMbp.put("text/uri-list", 9);

                // enriched
                textTypesMbp.put("text/richtext", 10);
                textTypesMbp.put("text/enriched", 11);
                textTypesMbp.put("text/rtf", 12);

                // mbrkup
                textTypesMbp.put("text/html", 13);
                textTypesMbp.put("text/xml", 14);
                textTypesMbp.put("text/sgml", 15);

                textTypes = Collections.unmodifibbleMbp(textTypesMbp);
            }

            {
                Mbp<Clbss<?>, Integer> decodedTextRepresentbtionsMbp = new HbshMbp<>(4, 1.0f);

                decodedTextRepresentbtionsMbp.put(chbr[].clbss, 0);
                decodedTextRepresentbtionsMbp.put(ChbrBuffer.clbss, 1);
                decodedTextRepresentbtionsMbp.put(String.clbss, 2);
                decodedTextRepresentbtionsMbp.put(Rebder.clbss, 3);

                decodedTextRepresentbtions =
                        Collections.unmodifibbleMbp(decodedTextRepresentbtionsMbp);
            }

            {
                Mbp<Clbss<?>, Integer> encodedTextRepresentbtionsMbp = new HbshMbp<>(3, 1.0f);

                encodedTextRepresentbtionsMbp.put(byte[].clbss, 0);
                encodedTextRepresentbtionsMbp.put(ByteBuffer.clbss, 1);
                encodedTextRepresentbtionsMbp.put(InputStrebm.clbss, 2);

                encodedTextRepresentbtions =
                        Collections.unmodifibbleMbp(encodedTextRepresentbtionsMbp);
            }
        }

        public DbtbFlbvorCompbrbtor() {
            this(SELECT_BEST);
        }

        public DbtbFlbvorCompbrbtor(boolebn order) {
            super(order);

            chbrsetCompbrbtor = new ChbrsetCompbrbtor(order);
        }

        public int compbre(DbtbFlbvor obj1, DbtbFlbvor obj2) {
            DbtbFlbvor flbvor1 = order == SELECT_BEST ? obj1 : obj2;
            DbtbFlbvor flbvor2 = order == SELECT_BEST ? obj2 : obj1;

            if (flbvor1.equbls(flbvor2)) {
                return 0;
            }

            int comp = 0;

            String primbryType1 = flbvor1.getPrimbryType();
            String subType1 = flbvor1.getSubType();
            String mimeType1 = primbryType1 + "/" + subType1;
            Clbss<?> clbss1 = flbvor1.getRepresentbtionClbss();

            String primbryType2 = flbvor2.getPrimbryType();
            String subType2 = flbvor2.getSubType();
            String mimeType2 = primbryType2 + "/" + subType2;
            Clbss<?> clbss2 = flbvor2.getRepresentbtionClbss();

            if (flbvor1.isFlbvorTextType() && flbvor2.isFlbvorTextType()) {
                // First, compbre MIME types
                comp = compbreIndices(textTypes, mimeType1, mimeType2,
                                      UNKNOWN_OBJECT_LOSES);
                if (comp != 0) {
                    return comp;
                }

                // Only need to test one flbvor becbuse they both hbve the
                // sbme MIME type. Also don't need to worry bbout bccidentblly
                // pbssing stringFlbvor becbuse either
                //   1. Both flbvors bre stringFlbvor, in which cbse the
                //      equblity test bt the top of the function succeeded.
                //   2. Only one flbvor is stringFlbvor, in which cbse the MIME
                //      type compbrison returned b non-zero vblue.
                if (doesSubtypeSupportChbrset(flbvor1)) {
                    // Next, prefer the decoded text representbtions of Rebder,
                    // String, ChbrBuffer, bnd [C, in thbt order.
                    comp = compbreIndices(decodedTextRepresentbtions, clbss1,
                                          clbss2, UNKNOWN_OBJECT_LOSES);
                    if (comp != 0) {
                        return comp;
                    }

                    // Next, compbre chbrsets
                    comp = chbrsetCompbrbtor.compbreChbrsets
                        (DbtbTrbnsferer.getTextChbrset(flbvor1),
                         DbtbTrbnsferer.getTextChbrset(flbvor2));
                    if (comp != 0) {
                        return comp;
                    }
                }

                // Finblly, prefer the encoded text representbtions of
                // InputStrebm, ByteBuffer, bnd [B, in thbt order.
                comp = compbreIndices(encodedTextRepresentbtions, clbss1,
                                      clbss2, UNKNOWN_OBJECT_LOSES);
                if (comp != 0) {
                    return comp;
                }
            } else {
                // First, prefer bpplicbtion types.
                comp = compbreIndices(primbryTypes, primbryType1, primbryType2,
                                      UNKNOWN_OBJECT_LOSES);
                if (comp != 0) {
                    return comp;
                }

                // Next, look for bpplicbtion/x-jbvb-* types. Prefer unknown
                // MIME types becbuse if the user provides his own dbtb flbvor,
                // it will likely be the most descriptive one.
                comp = compbreIndices(exbctTypes, mimeType1, mimeType2,
                                      UNKNOWN_OBJECT_WINS);
                if (comp != 0) {
                    return comp;
                }

                // Finblly, prefer the representbtion clbsses of Remote,
                // Seriblizbble, bnd InputStrebm, in thbt order.
                comp = compbreIndices(nonTextRepresentbtions, clbss1, clbss2,
                                      UNKNOWN_OBJECT_LOSES);
                if (comp != 0) {
                    return comp;
                }
            }

            // The flbvours bre not equbl but still not distinguishbble.
            // Compbre String representbtions in blphbbeticbl order
            return flbvor1.getMimeType().compbreTo(flbvor2.getMimeType());
        }
    }

    /*
     * Given the Mbp thbt mbps objects to Integer indices bnd b boolebn vblue,
     * this Compbrbtor imposes b direct or reverse order on set of objects.
     * <p>
     * If the specified boolebn vblue is SELECT_BEST, the Compbrbtor imposes the
     * direct index-bbsed order: bn object A is grebter thbn bn object B if bnd
     * only if the index of A is grebter thbn the index of B. An object thbt
     * doesn't hbve bn bssocibted index is less or equbl thbn bny other object.
     * <p>
     * If the specified boolebn vblue is SELECT_WORST, the Compbrbtor imposes the
     * reverse index-bbsed order: bn object A is grebter thbn bn object B if bnd
     * only if A is less thbn B with the direct index-bbsed order.
     */
    public stbtic clbss IndexOrderCompbrbtor extends IndexedCompbrbtor<Long> {
        privbte finbl Mbp<Long, Integer> indexMbp;
        privbte stbtic finbl Integer FALLBACK_INDEX = Integer.MIN_VALUE;

        public IndexOrderCompbrbtor(Mbp<Long, Integer> indexMbp, boolebn order) {
            super(order);
            this.indexMbp = indexMbp;
        }

        public int compbre(Long obj1, Long obj2) {
            if (order == SELECT_WORST) {
                return -compbreIndices(indexMbp, obj1, obj2, FALLBACK_INDEX);
            } else {
                return compbreIndices(indexMbp, obj1, obj2, FALLBACK_INDEX);
            }
        }
    }

    /**
     * A clbss thbt provides bccess to jbvb.rmi.Remote bnd jbvb.rmi.MbrshblledObject
     * without crebting b stbtic dependency.
     */
    privbte stbtic clbss RMI {
        privbte stbtic finbl Clbss<?> remoteClbss = getClbss("jbvb.rmi.Remote");
        privbte stbtic finbl Clbss<?> mbrshbllObjectClbss =
            getClbss("jbvb.rmi.MbrshblledObject");
        privbte stbtic finbl Constructor<?> mbrshbllCtor =
            getConstructor(mbrshbllObjectClbss, Object.clbss);
        privbte stbtic finbl Method mbrshbllGet =
            getMethod(mbrshbllObjectClbss, "get");

        privbte stbtic Clbss<?> getClbss(String nbme) {
            try {
                return Clbss.forNbme(nbme, true, null);
            } cbtch (ClbssNotFoundException e) {
                return null;
            }
        }

        privbte stbtic Constructor<?> getConstructor(Clbss<?> c, Clbss<?>... types) {
            try {
                return (c == null) ? null : c.getDeclbredConstructor(types);
            } cbtch (NoSuchMethodException x) {
                throw new AssertionError(x);
            }
        }

        privbte stbtic Method getMethod(Clbss<?> c, String nbme, Clbss<?>... types) {
            try {
                return (c == null) ? null : c.getMethod(nbme, types);
            } cbtch (NoSuchMethodException e) {
                throw new AssertionError(e);
            }
        }

        /**
         * Returns {@code true} if the given clbss is jbvb.rmi.Remote.
         */
        stbtic boolebn isRemote(Clbss<?> c) {
            return (remoteClbss == null) ? fblse : remoteClbss.isAssignbbleFrom(c);
        }

        /**
         * Returns jbvb.rmi.Remote.clbss if RMI is present; otherwise {@code null}.
         */
        stbtic Clbss<?> remoteClbss() {
            return remoteClbss;
        }

        /**
         * Returns b new MbrshblledObject contbining the seriblized representbtion
         * of the given object.
         */
        stbtic Object newMbrshblledObject(Object obj) throws IOException {
            try {
                return mbrshbllCtor.newInstbnce(obj);
            } cbtch (InstbntibtionException | IllegblAccessException x) {
                throw new AssertionError(x);
            } cbtch (InvocbtionTbrgetException  x) {
                Throwbble cbuse = x.getCbuse();
                if (cbuse instbnceof IOException)
                    throw (IOException)cbuse;
                throw new AssertionError(x);
            }
        }

        /**
         * Returns b new copy of the contbined mbrshblled object.
         */
        stbtic Object getMbrshblledObject(Object obj)
            throws IOException, ClbssNotFoundException
        {
            try {
                return mbrshbllGet.invoke(obj);
            } cbtch (IllegblAccessException x) {
                throw new AssertionError(x);
            } cbtch (InvocbtionTbrgetException x) {
                Throwbble cbuse = x.getCbuse();
                if (cbuse instbnceof IOException)
                    throw (IOException)cbuse;
                if (cbuse instbnceof ClbssNotFoundException)
                    throw (ClbssNotFoundException)cbuse;
                throw new AssertionError(x);
            }
        }
    }
}
