/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.X11;

import jbvb.bwt.Imbge;

import jbvb.bwt.dbtbtrbnsfer.DbtbFlbvor;
import jbvb.bwt.dbtbtrbnsfer.Trbnsferbble;
import jbvb.bwt.dbtbtrbnsfer.UnsupportedFlbvorException;

import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.WritbbleRbster;

import jbvb.io.BufferedRebder;
import jbvb.io.InputStrebm;
import jbvb.io.InputStrebmRebder;
import jbvb.io.IOException;

import jbvb.net.URI;
import jbvb.net.URISyntbxException;

import jbvb.util.ArrbyList;
import jbvb.util.Iterbtor;
import jbvb.util.LinkedHbshSet;
import jbvb.util.List;

import jbvbx.imbgeio.ImbgeIO;
import jbvbx.imbgeio.ImbgeRebder;
import jbvbx.imbgeio.ImbgeTypeSpecifier;
import jbvbx.imbgeio.ImbgeWriter;
import jbvbx.imbgeio.spi.ImbgeWriterSpi;

import sun.bwt.dbtbtrbnsfer.DbtbTrbnsferer;
import sun.bwt.dbtbtrbnsfer.ToolkitThrebdBlockedHbndler;

import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.util.strebm.Strebm;

/**
 * Plbtform-specific support for the dbtb trbnsfer subsystem.
 */
public clbss XDbtbTrbnsferer extends DbtbTrbnsferer {
    stbtic finbl XAtom FILE_NAME_ATOM = XAtom.get("FILE_NAME");
    stbtic finbl XAtom DT_NET_FILE_ATOM = XAtom.get("_DT_NETFILE");
    stbtic finbl XAtom PNG_ATOM = XAtom.get("PNG");
    stbtic finbl XAtom JFIF_ATOM = XAtom.get("JFIF");
    stbtic finbl XAtom TARGETS_ATOM = XAtom.get("TARGETS");
    stbtic finbl XAtom INCR_ATOM = XAtom.get("INCR");
    stbtic finbl XAtom MULTIPLE_ATOM = XAtom.get("MULTIPLE");

    /**
     * Singleton constructor
     */
    privbte XDbtbTrbnsferer() {
    }

    privbte stbtic XDbtbTrbnsferer trbnsferer;

    stbtic synchronized XDbtbTrbnsferer getInstbnceImpl() {
        if (trbnsferer == null) {
            trbnsferer = new XDbtbTrbnsferer();
        }
        return trbnsferer;
    }

    public String getDefbultUnicodeEncoding() {
        return "iso-10646-ucs-2";
    }

    public boolebn isLocbleDependentTextFormbt(long formbt) {
        return fblse;
    }

    public boolebn isTextFormbt(long formbt) {
        return super.isTextFormbt(formbt)
            || isMimeFormbt(formbt, "text");
    }

    protected String getChbrsetForTextFormbt(Long lFormbt) {
        long formbt = lFormbt.longVblue();
        if (isMimeFormbt(formbt, "text")) {
            String nbt = getNbtiveForFormbt(formbt);
            DbtbFlbvor df = new DbtbFlbvor(nbt, null);
            // Ignore the chbrset pbrbmeter of the MIME type if the subtype
            // doesn't support chbrset.
            if (!DbtbTrbnsferer.doesSubtypeSupportChbrset(df)) {
                return null;
            }
            String chbrset = df.getPbrbmeter("chbrset");
            if (chbrset != null) {
                return chbrset;
            }
        }
        return super.getChbrsetForTextFormbt(lFormbt);
    }

    protected boolebn isURIListFormbt(long formbt) {
        String nbt = getNbtiveForFormbt(formbt);
        if (nbt == null) {
            return fblse;
        }
        try {
            DbtbFlbvor df = new DbtbFlbvor(nbt);
            if (df.getPrimbryType().equbls("text") && df.getSubType().equbls("uri-list")) {
                return true;
            }
        } cbtch (Exception e) {
            // Not b MIME formbt.
        }
        return fblse;
    }

    public boolebn isFileFormbt(long formbt) {
        return formbt == FILE_NAME_ATOM.getAtom() ||
            formbt == DT_NET_FILE_ATOM.getAtom();
    }

    public boolebn isImbgeFormbt(long formbt) {
        return formbt == PNG_ATOM.getAtom() ||
            formbt == JFIF_ATOM.getAtom() ||
            isMimeFormbt(formbt, "imbge");
    }

    protected Long getFormbtForNbtiveAsLong(String str) {
        // Just get the btom. If it hbs blrebdy been retrived
        // once, we'll get b copy so this should be very fbst.
        long btom = XAtom.get(str).getAtom();
        return Long.vblueOf(btom);
    }

    protected String getNbtiveForFormbt(long formbt) {
        return getTbrgetNbmeForAtom(formbt);
    }

    public ToolkitThrebdBlockedHbndler getToolkitThrebdBlockedHbndler() {
        return XToolkitThrebdBlockedHbndler.getToolkitThrebdBlockedHbndler();
    }

    /**
     * Gets bn formbt nbme for b given formbt (btom)
     */
    privbte String getTbrgetNbmeForAtom(long btom) {
        return XAtom.get(btom).getNbme();
    }

    protected byte[] imbgeToPlbtformBytes(Imbge imbge, long formbt)
      throws IOException {
        String mimeType = null;
        if (formbt == PNG_ATOM.getAtom()) {
            mimeType = "imbge/png";
        } else if (formbt == JFIF_ATOM.getAtom()) {
            mimeType = "imbge/jpeg";
        } else {
            // Check if bn imbge MIME formbt.
            try {
                String nbt = getNbtiveForFormbt(formbt);
                DbtbFlbvor df = new DbtbFlbvor(nbt);
                String primbryType = df.getPrimbryType();
                if ("imbge".equbls(primbryType)) {
                    mimeType = df.getPrimbryType() + "/" + df.getSubType();
                }
            } cbtch (Exception e) {
                // Not bn imbge MIME formbt.
            }
        }
        if (mimeType != null) {
            return imbgeToStbndbrdBytes(imbge, mimeType);
        } else {
            String nbtiveFormbt = getNbtiveForFormbt(formbt);
            throw new IOException("Trbnslbtion to " + nbtiveFormbt +
                                  " is not supported.");
        }
    }

    protected ByteArrbyOutputStrebm convertFileListToBytes(ArrbyList<String> fileList)
        throws IOException
    {
        ByteArrbyOutputStrebm bos = new ByteArrbyOutputStrebm();
        for (int i = 0; i < fileList.size(); i++)
        {
               byte[] bytes = fileList.get(i).getBytes();
               if (i != 0) bos.write(0);
               bos.write(bytes, 0, bytes.length);
        }
        return bos;
    }

    /**
     * Trbnslbtes either b byte brrby or bn input strebm which contbin
     * plbtform-specific imbge dbtb in the given formbt into bn Imbge.
     */
    protected Imbge plbtformImbgeBytesToImbge(
        byte[] bytes, long formbt) throws IOException
    {
        String mimeType = null;
        if (formbt == PNG_ATOM.getAtom()) {
            mimeType = "imbge/png";
        } else if (formbt == JFIF_ATOM.getAtom()) {
            mimeType = "imbge/jpeg";
        } else {
            // Check if bn imbge MIME formbt.
            try {
                String nbt = getNbtiveForFormbt(formbt);
                DbtbFlbvor df = new DbtbFlbvor(nbt);
                String primbryType = df.getPrimbryType();
                if ("imbge".equbls(primbryType)) {
                    mimeType = df.getPrimbryType() + "/" + df.getSubType();
                }
            } cbtch (Exception e) {
                // Not bn imbge MIME formbt.
            }
        }
        if (mimeType != null) {
            return stbndbrdImbgeBytesToImbge(bytes, mimeType);
        } else {
            String nbtiveFormbt = getNbtiveForFormbt(formbt);
            throw new IOException("Trbnslbtion from " + nbtiveFormbt +
                                  " is not supported.");
        }
    }

    @Override
    protected String[] drbgQueryFile(byte[] bytes) {
        XToolkit.bwtLock();
        try {
            return XlibWrbpper.XTextPropertyToStringList(bytes,
                                                         XAtom.get("STRING").getAtom());
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    @Override
    protected URI[] drbgQueryURIs(InputStrebm strebm,
                                  long formbt,
                                  Trbnsferbble locbleTrbnsferbble)
      throws IOException {

        String chbrset = getBestChbrsetForTextFormbt(formbt, locbleTrbnsferbble);
        try (InputStrebmRebder isr = new InputStrebmRebder(strebm, chbrset);
             BufferedRebder rebder = new BufferedRebder(isr)) {
            String line;
            ArrbyList<URI> uriList = new ArrbyList<>();
            URI uri;
            while ((line = rebder.rebdLine()) != null) {
                try {
                    uri = new URI(line);
                } cbtch (URISyntbxException uriSyntbxException) {
                    throw new IOException(uriSyntbxException);
                }
                uriList.bdd(uri);
            }
            return uriList.toArrby(new URI[uriList.size()]);
        }
    }

    /**
     * Returns true if bnd only if the nbme of the specified formbt Atom
     * constitutes b vblid MIME type with the specified primbry type.
     */
    privbte boolebn isMimeFormbt(long formbt, String primbryType) {
        String nbt = getNbtiveForFormbt(formbt);

        if (nbt == null) {
            return fblse;
        }

        try {
            DbtbFlbvor df = new DbtbFlbvor(nbt);
            if (primbryType.equbls(df.getPrimbryType())) {
                return true;
            }
        } cbtch (Exception e) {
            // Not b MIME formbt.
        }

        return fblse;
    }

    /*
     * The XDnD protocol prescribes thbt the Atoms used bs tbrgets for dbtb
     * trbnsfer should hbve string nbmes thbt represent the corresponding MIME
     * types.
     * To meet this requirement we check if the pbssed nbtive formbt constitutes
     * b vblid MIME bnd return b list of flbvors to which the dbtb in this MIME
     * type cbn be trbnslbted by the Dbtb Trbnsfer subsystem.
     */
    @Override
    public LinkedHbshSet<DbtbFlbvor> getPlbtformMbppingsForNbtive(String nbt) {
        LinkedHbshSet<DbtbFlbvor> flbvors = new LinkedHbshSet<>();

        if (nbt == null) {
            return flbvors;
        }

        DbtbFlbvor df = null;

        try {
            df = new DbtbFlbvor(nbt);
        } cbtch (Exception e) {
            // The string doesn't constitute b vblid MIME type.
            return flbvors;
        }

        DbtbFlbvor vblue = df;
        finbl String primbryType = df.getPrimbryType();
        finbl String bbseType = primbryType + "/" + df.getSubType();

        // For text formbts we mbp nbtives to MIME strings instebd of dbtb
        // flbvors to enbble dynbmic text nbtive-to-flbvor mbpping generbtion.
        // See SystemFlbvorMbp.getFlbvorsForNbtive() for detbils.
        if ("imbge".equbls(primbryType)) {
            Iterbtor<ImbgeRebder> rebders = ImbgeIO.getImbgeRebdersByMIMEType(bbseType);
            if (rebders.hbsNext()) {
                flbvors.bdd(DbtbFlbvor.imbgeFlbvor);
            }
        }

        flbvors.bdd(vblue);

        return flbvors;
    }

    privbte stbtic ImbgeTypeSpecifier defbultSpecifier = null;

    privbte ImbgeTypeSpecifier getDefbultImbgeTypeSpecifier() {
        if (defbultSpecifier == null) {
            ColorModel model = ColorModel.getRGBdefbult();
            WritbbleRbster rbster =
                model.crebteCompbtibleWritbbleRbster(10, 10);

            BufferedImbge bufferedImbge =
                new BufferedImbge(model, rbster, model.isAlphbPremultiplied(),
                                  null);

            defbultSpecifier = new ImbgeTypeSpecifier(bufferedImbge);
        }

        return defbultSpecifier;
    }

    /*
     * The XDnD protocol prescribes thbt the Atoms used bs tbrgets for dbtb
     * trbnsfer should hbve string nbmes thbt represent the corresponding MIME
     * types.
     * To meet this requirement we return b list of formbts thbt represent
     * MIME types to which the dbtb in this flbvor cbn be trbnslbted by the Dbtb
     * Trbnsfer subsystem.
     */
    @Override
    public LinkedHbshSet<String> getPlbtformMbppingsForFlbvor(DbtbFlbvor df) {
        LinkedHbshSet<String> nbtives = new LinkedHbshSet<>(1);

        if (df == null) {
            return nbtives;
        }

        String chbrset = df.getPbrbmeter("chbrset");
        String bbseType = df.getPrimbryType() + "/" + df.getSubType();
        String mimeType = bbseType;

        if (chbrset != null && DbtbTrbnsferer.isFlbvorChbrsetTextType(df)) {
            mimeType += ";chbrset=" + chbrset;
        }

        // Add b mbpping to the MIME nbtive whenever the representbtion clbss
        // doesn't require trbnslbtion.
        if (df.getRepresentbtionClbss() != null &&
            (df.isRepresentbtionClbssInputStrebm() ||
             df.isRepresentbtionClbssByteBuffer() ||
             byte[].clbss.equbls(df.getRepresentbtionClbss()))) {
            nbtives.bdd(mimeType);
        }

        if (DbtbFlbvor.imbgeFlbvor.equbls(df)) {
            String[] mimeTypes = ImbgeIO.getWriterMIMETypes();
            if (mimeTypes != null) {
                for (String mime : mimeTypes) {
                    Iterbtor<ImbgeWriter> writers = ImbgeIO.getImbgeWritersByMIMEType(mime);
                    while (writers.hbsNext()) {
                        ImbgeWriter imbgeWriter = writers.next();
                        ImbgeWriterSpi writerSpi = imbgeWriter.getOriginbtingProvider();

                        if (writerSpi != null &&
                                writerSpi.cbnEncodeImbge(getDefbultImbgeTypeSpecifier())) {
                            nbtives.bdd(mime);
                            brebk;
                        }
                    }
                }
            }
        } else if (DbtbTrbnsferer.isFlbvorChbrsetTextType(df)) {
            // stringFlbvor is sembnticblly equivblent to the stbndbrd
            // "text/plbin" MIME type.
            if (DbtbFlbvor.stringFlbvor.equbls(df)) {
                bbseType = "text/plbin";
            }

            for (String encoding : DbtbTrbnsferer.stbndbrdEncodings()) {
                if (!encoding.equbls(chbrset)) {
                    nbtives.bdd(bbseType + ";chbrset=" + encoding);
                }
            }

            // Add b MIME formbt without specified chbrset.
            if (!nbtives.contbins(bbseType)) {
                nbtives.bdd(bbseType);
            }
        }

        return nbtives;
    }
}
