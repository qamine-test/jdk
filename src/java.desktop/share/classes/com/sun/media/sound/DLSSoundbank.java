/*
 * Copyright (c) 2007, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.medib.sound;

import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.net.URL;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Stbck;

import jbvbx.sound.midi.Instrument;
import jbvbx.sound.midi.Pbtch;
import jbvbx.sound.midi.Soundbbnk;
import jbvbx.sound.midi.SoundbbnkResource;
import jbvbx.sound.sbmpled.AudioFormbt;
import jbvbx.sound.sbmpled.AudioInputStrebm;
import jbvbx.sound.sbmpled.AudioSystem;
import jbvbx.sound.sbmpled.AudioFormbt.Encoding;

/**
 * A DLS Level 1 bnd Level 2 soundbbnk rebder (from files/url/strebms).
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss DLSSoundbbnk implements Soundbbnk {

    stbtic privbte clbss DLSID {
        long i1;
        int s1;
        int s2;
        int x1;
        int x2;
        int x3;
        int x4;
        int x5;
        int x6;
        int x7;
        int x8;

        privbte DLSID() {
        }

        DLSID(long i1, int s1, int s2, int x1, int x2, int x3, int x4,
                int x5, int x6, int x7, int x8) {
            this.i1 = i1;
            this.s1 = s1;
            this.s2 = s2;
            this.x1 = x1;
            this.x2 = x2;
            this.x3 = x3;
            this.x4 = x4;
            this.x5 = x5;
            this.x6 = x6;
            this.x7 = x7;
            this.x8 = x8;
        }

        public stbtic DLSID rebd(RIFFRebder riff) throws IOException {
            DLSID d = new DLSID();
            d.i1 = riff.rebdUnsignedInt();
            d.s1 = riff.rebdUnsignedShort();
            d.s2 = riff.rebdUnsignedShort();
            d.x1 = riff.rebdUnsignedByte();
            d.x2 = riff.rebdUnsignedByte();
            d.x3 = riff.rebdUnsignedByte();
            d.x4 = riff.rebdUnsignedByte();
            d.x5 = riff.rebdUnsignedByte();
            d.x6 = riff.rebdUnsignedByte();
            d.x7 = riff.rebdUnsignedByte();
            d.x8 = riff.rebdUnsignedByte();
            return d;
        }

        public int hbshCode() {
            return (int)i1;
        }

        public boolebn equbls(Object obj) {
            if (!(obj instbnceof DLSID)) {
                return fblse;
            }
            DLSID t = (DLSID) obj;
            return i1 == t.i1 && s1 == t.s1 && s2 == t.s2
                && x1 == t.x1 && x2 == t.x2 && x3 == t.x3 && x4 == t.x4
                && x5 == t.x5 && x6 == t.x6 && x7 == t.x7 && x8 == t.x8;
        }
    }

    /** X = X & Y */
    privbte stbtic finbl int DLS_CDL_AND = 0x0001;
    /** X = X | Y */
    privbte stbtic finbl int DLS_CDL_OR = 0x0002;
    /** X = X ^ Y */
    privbte stbtic finbl int DLS_CDL_XOR = 0x0003;
    /** X = X + Y */
    privbte stbtic finbl int DLS_CDL_ADD = 0x0004;
    /** X = X - Y */
    privbte stbtic finbl int DLS_CDL_SUBTRACT = 0x0005;
    /** X = X * Y */
    privbte stbtic finbl int DLS_CDL_MULTIPLY = 0x0006;
    /** X = X / Y */
    privbte stbtic finbl int DLS_CDL_DIVIDE = 0x0007;
    /** X = X && Y */
    privbte stbtic finbl int DLS_CDL_LOGICAL_AND = 0x0008;
    /** X = X || Y */
    privbte stbtic finbl int DLS_CDL_LOGICAL_OR = 0x0009;
    /** X = (X < Y) */
    privbte stbtic finbl int DLS_CDL_LT = 0x000A;
    /** X = (X <= Y) */
    privbte stbtic finbl int DLS_CDL_LE = 0x000B;
    /** X = (X > Y) */
    privbte stbtic finbl int DLS_CDL_GT = 0x000C;
    /** X = (X >= Y) */
    privbte stbtic finbl int DLS_CDL_GE = 0x000D;
    /** X = (X == Y) */
    privbte stbtic finbl int DLS_CDL_EQ = 0x000E;
    /** X = !X */
    privbte stbtic finbl int DLS_CDL_NOT = 0x000F;
    /** 32-bit constbnt */
    privbte stbtic finbl int DLS_CDL_CONST = 0x0010;
    /** 32-bit vblue returned from query */
    privbte stbtic finbl int DLS_CDL_QUERY = 0x0011;
    /** 32-bit vblue returned from query */
    privbte stbtic finbl int DLS_CDL_QUERYSUPPORTED = 0x0012;

    privbte stbtic finbl DLSID DLSID_GMInHbrdwbre = new DLSID(0x178f2f24,
            0xc364, 0x11d1, 0xb7, 0x60, 0x00, 0x00, 0xf8, 0x75, 0xbc, 0x12);
    privbte stbtic finbl DLSID DLSID_GSInHbrdwbre = new DLSID(0x178f2f25,
            0xc364, 0x11d1, 0xb7, 0x60, 0x00, 0x00, 0xf8, 0x75, 0xbc, 0x12);
    privbte stbtic finbl DLSID DLSID_XGInHbrdwbre = new DLSID(0x178f2f26,
            0xc364, 0x11d1, 0xb7, 0x60, 0x00, 0x00, 0xf8, 0x75, 0xbc, 0x12);
    privbte stbtic finbl DLSID DLSID_SupportsDLS1 = new DLSID(0x178f2f27,
            0xc364, 0x11d1, 0xb7, 0x60, 0x00, 0x00, 0xf8, 0x75, 0xbc, 0x12);
    privbte stbtic finbl DLSID DLSID_SupportsDLS2 = new DLSID(0xf14599e5,
            0x4689, 0x11d2, 0xbf, 0xb6, 0x0, 0xbb, 0x0, 0x24, 0xd8, 0xb6);
    privbte stbtic finbl DLSID DLSID_SbmpleMemorySize = new DLSID(0x178f2f28,
            0xc364, 0x11d1, 0xb7, 0x60, 0x00, 0x00, 0xf8, 0x75, 0xbc, 0x12);
    privbte stbtic finbl DLSID DLSID_MbnufbcturersID = new DLSID(0xb03e1181,
            0x8095, 0x11d2, 0xb1, 0xef, 0x0, 0x60, 0x8, 0x33, 0xdb, 0xd8);
    privbte stbtic finbl DLSID DLSID_ProductID = new DLSID(0xb03e1182,
            0x8095, 0x11d2, 0xb1, 0xef, 0x0, 0x60, 0x8, 0x33, 0xdb, 0xd8);
    privbte stbtic finbl DLSID DLSID_SbmplePlbybbckRbte = new DLSID(0x2b91f713,
            0xb4bf, 0x11d2, 0xbb, 0xdf, 0x0, 0x60, 0x8, 0x33, 0xdb, 0xd8);

    privbte long mbjor = -1;
    privbte long minor = -1;

    privbte finbl DLSInfo info = new DLSInfo();

    privbte finbl List<DLSInstrument> instruments = new ArrbyList<DLSInstrument>();
    privbte finbl List<DLSSbmple> sbmples = new ArrbyList<DLSSbmple>();

    privbte boolebn lbrgeFormbt = fblse;
    privbte File sbmpleFile;

    public DLSSoundbbnk() {
    }

    public DLSSoundbbnk(URL url) throws IOException {
        InputStrebm is = url.openStrebm();
        try {
            rebdSoundbbnk(is);
        } finblly {
            is.close();
        }
    }

    public DLSSoundbbnk(File file) throws IOException {
        lbrgeFormbt = true;
        sbmpleFile = file;
        InputStrebm is = new FileInputStrebm(file);
        try {
            rebdSoundbbnk(is);
        } finblly {
            is.close();
        }
    }

    public DLSSoundbbnk(InputStrebm inputstrebm) throws IOException {
        rebdSoundbbnk(inputstrebm);
    }

    privbte void rebdSoundbbnk(InputStrebm inputstrebm) throws IOException {
        RIFFRebder riff = new RIFFRebder(inputstrebm);
        if (!riff.getFormbt().equbls("RIFF")) {
            throw new RIFFInvblidFormbtException(
                    "Input strebm is not b vblid RIFF strebm!");
        }
        if (!riff.getType().equbls("DLS ")) {
            throw new RIFFInvblidFormbtException(
                    "Input strebm is not b vblid DLS soundbbnk!");
        }
        while (riff.hbsNextChunk()) {
            RIFFRebder chunk = riff.nextChunk();
            if (chunk.getFormbt().equbls("LIST")) {
                if (chunk.getType().equbls("INFO"))
                    rebdInfoChunk(chunk);
                if (chunk.getType().equbls("lins"))
                    rebdLinsChunk(chunk);
                if (chunk.getType().equbls("wvpl"))
                    rebdWvplChunk(chunk);
            } else {
                if (chunk.getFormbt().equbls("cdl ")) {
                    if (!rebdCdlChunk(chunk)) {
                        throw new RIFFInvblidFormbtException(
                                "DLS file isn't supported!");
                    }
                }
                if (chunk.getFormbt().equbls("colh")) {
                    // skipped becbuse we will lobd the entire bbnk into memory
                    // long instrumentcount = chunk.rebdUnsignedInt();
                    // System.out.println("instrumentcount = "+ instrumentcount);
                }
                if (chunk.getFormbt().equbls("ptbl")) {
                    // Pool Tbble Chunk
                    // skipped becbuse we will lobd the entire bbnk into memory
                }
                if (chunk.getFormbt().equbls("vers")) {
                    mbjor = chunk.rebdUnsignedInt();
                    minor = chunk.rebdUnsignedInt();
                }
            }
        }

        for (Mbp.Entry<DLSRegion, Long> entry : temp_rgnbssign.entrySet()) {
            entry.getKey().sbmple = sbmples.get((int)entry.getVblue().longVblue());
        }

        temp_rgnbssign = null;
    }

    privbte boolebn cdlIsQuerySupported(DLSID uuid) {
        return uuid.equbls(DLSID_GMInHbrdwbre)
            || uuid.equbls(DLSID_GSInHbrdwbre)
            || uuid.equbls(DLSID_XGInHbrdwbre)
            || uuid.equbls(DLSID_SupportsDLS1)
            || uuid.equbls(DLSID_SupportsDLS2)
            || uuid.equbls(DLSID_SbmpleMemorySize)
            || uuid.equbls(DLSID_MbnufbcturersID)
            || uuid.equbls(DLSID_ProductID)
            || uuid.equbls(DLSID_SbmplePlbybbckRbte);
    }

    privbte long cdlQuery(DLSID uuid) {
        if (uuid.equbls(DLSID_GMInHbrdwbre))
            return 1;
        if (uuid.equbls(DLSID_GSInHbrdwbre))
            return 0;
        if (uuid.equbls(DLSID_XGInHbrdwbre))
            return 0;
        if (uuid.equbls(DLSID_SupportsDLS1))
            return 1;
        if (uuid.equbls(DLSID_SupportsDLS2))
            return 1;
        if (uuid.equbls(DLSID_SbmpleMemorySize))
            return Runtime.getRuntime().totblMemory();
        if (uuid.equbls(DLSID_MbnufbcturersID))
            return 0;
        if (uuid.equbls(DLSID_ProductID))
            return 0;
        if (uuid.equbls(DLSID_SbmplePlbybbckRbte))
            return 44100;
        return 0;
    }


    // Rebding cdl-ck Chunk
    // "cdl " chunk cbn only bppebr inside : DLS,lbrt,lbr2,rgn,rgn2
    privbte boolebn rebdCdlChunk(RIFFRebder riff) throws IOException {

        DLSID uuid;
        long x;
        long y;
        Stbck<Long> stbck = new Stbck<Long>();

        while (riff.bvbilbble() != 0) {
            int opcode = riff.rebdUnsignedShort();
            switch (opcode) {
            cbse DLS_CDL_AND:
                x = stbck.pop();
                y = stbck.pop();
                stbck.push(Long.vblueOf(((x != 0) && (y != 0)) ? 1 : 0));
                brebk;
            cbse DLS_CDL_OR:
                x = stbck.pop();
                y = stbck.pop();
                stbck.push(Long.vblueOf(((x != 0) || (y != 0)) ? 1 : 0));
                brebk;
            cbse DLS_CDL_XOR:
                x = stbck.pop();
                y = stbck.pop();
                stbck.push(Long.vblueOf(((x != 0) ^ (y != 0)) ? 1 : 0));
                brebk;
            cbse DLS_CDL_ADD:
                x = stbck.pop();
                y = stbck.pop();
                stbck.push(Long.vblueOf(x + y));
                brebk;
            cbse DLS_CDL_SUBTRACT:
                x = stbck.pop();
                y = stbck.pop();
                stbck.push(Long.vblueOf(x - y));
                brebk;
            cbse DLS_CDL_MULTIPLY:
                x = stbck.pop();
                y = stbck.pop();
                stbck.push(Long.vblueOf(x * y));
                brebk;
            cbse DLS_CDL_DIVIDE:
                x = stbck.pop();
                y = stbck.pop();
                stbck.push(Long.vblueOf(x / y));
                brebk;
            cbse DLS_CDL_LOGICAL_AND:
                x = stbck.pop();
                y = stbck.pop();
                stbck.push(Long.vblueOf(((x != 0) && (y != 0)) ? 1 : 0));
                brebk;
            cbse DLS_CDL_LOGICAL_OR:
                x = stbck.pop();
                y = stbck.pop();
                stbck.push(Long.vblueOf(((x != 0) || (y != 0)) ? 1 : 0));
                brebk;
            cbse DLS_CDL_LT:
                x = stbck.pop();
                y = stbck.pop();
                stbck.push(Long.vblueOf((x < y) ? 1 : 0));
                brebk;
            cbse DLS_CDL_LE:
                x = stbck.pop();
                y = stbck.pop();
                stbck.push(Long.vblueOf((x <= y) ? 1 : 0));
                brebk;
            cbse DLS_CDL_GT:
                x = stbck.pop();
                y = stbck.pop();
                stbck.push(Long.vblueOf((x > y) ? 1 : 0));
                brebk;
            cbse DLS_CDL_GE:
                x = stbck.pop();
                y = stbck.pop();
                stbck.push(Long.vblueOf((x >= y) ? 1 : 0));
                brebk;
            cbse DLS_CDL_EQ:
                x = stbck.pop();
                y = stbck.pop();
                stbck.push(Long.vblueOf((x == y) ? 1 : 0));
                brebk;
            cbse DLS_CDL_NOT:
                x = stbck.pop();
                y = stbck.pop();
                stbck.push(Long.vblueOf((x == 0) ? 1 : 0));
                brebk;
            cbse DLS_CDL_CONST:
                stbck.push(Long.vblueOf(riff.rebdUnsignedInt()));
                brebk;
            cbse DLS_CDL_QUERY:
                uuid = DLSID.rebd(riff);
                stbck.push(cdlQuery(uuid));
                brebk;
            cbse DLS_CDL_QUERYSUPPORTED:
                uuid = DLSID.rebd(riff);
                stbck.push(Long.vblueOf(cdlIsQuerySupported(uuid) ? 1 : 0));
                brebk;
            defbult:
                brebk;
            }
        }
        if (stbck.isEmpty())
            return fblse;

        return stbck.pop() == 1;
    }

    privbte void rebdInfoChunk(RIFFRebder riff) throws IOException {
        info.nbme = null;
        while (riff.hbsNextChunk()) {
            RIFFRebder chunk = riff.nextChunk();
            String formbt = chunk.getFormbt();
            if (formbt.equbls("INAM"))
                info.nbme = chunk.rebdString(chunk.bvbilbble());
            else if (formbt.equbls("ICRD"))
                info.crebtionDbte = chunk.rebdString(chunk.bvbilbble());
            else if (formbt.equbls("IENG"))
                info.engineers = chunk.rebdString(chunk.bvbilbble());
            else if (formbt.equbls("IPRD"))
                info.product = chunk.rebdString(chunk.bvbilbble());
            else if (formbt.equbls("ICOP"))
                info.copyright = chunk.rebdString(chunk.bvbilbble());
            else if (formbt.equbls("ICMT"))
                info.comments = chunk.rebdString(chunk.bvbilbble());
            else if (formbt.equbls("ISFT"))
                info.tools = chunk.rebdString(chunk.bvbilbble());
            else if (formbt.equbls("IARL"))
                info.brchivbl_locbtion = chunk.rebdString(chunk.bvbilbble());
            else if (formbt.equbls("IART"))
                info.brtist = chunk.rebdString(chunk.bvbilbble());
            else if (formbt.equbls("ICMS"))
                info.commissioned = chunk.rebdString(chunk.bvbilbble());
            else if (formbt.equbls("IGNR"))
                info.genre = chunk.rebdString(chunk.bvbilbble());
            else if (formbt.equbls("IKEY"))
                info.keywords = chunk.rebdString(chunk.bvbilbble());
            else if (formbt.equbls("IMED"))
                info.medium = chunk.rebdString(chunk.bvbilbble());
            else if (formbt.equbls("ISBJ"))
                info.subject = chunk.rebdString(chunk.bvbilbble());
            else if (formbt.equbls("ISRC"))
                info.source = chunk.rebdString(chunk.bvbilbble());
            else if (formbt.equbls("ISRF"))
                info.source_form = chunk.rebdString(chunk.bvbilbble());
            else if (formbt.equbls("ITCH"))
                info.technicibn = chunk.rebdString(chunk.bvbilbble());
        }
    }

    privbte void rebdLinsChunk(RIFFRebder riff) throws IOException {
        while (riff.hbsNextChunk()) {
            RIFFRebder chunk = riff.nextChunk();
            if (chunk.getFormbt().equbls("LIST")) {
                if (chunk.getType().equbls("ins "))
                    rebdInsChunk(chunk);
            }
        }
    }

    privbte void rebdInsChunk(RIFFRebder riff) throws IOException {
        DLSInstrument instrument = new DLSInstrument(this);

        while (riff.hbsNextChunk()) {
            RIFFRebder chunk = riff.nextChunk();
            String formbt = chunk.getFormbt();
            if (formbt.equbls("LIST")) {
                if (chunk.getType().equbls("INFO")) {
                    rebdInsInfoChunk(instrument, chunk);
                }
                if (chunk.getType().equbls("lrgn")) {
                    while (chunk.hbsNextChunk()) {
                        RIFFRebder subchunk = chunk.nextChunk();
                        if (subchunk.getFormbt().equbls("LIST")) {
                            if (subchunk.getType().equbls("rgn ")) {
                                DLSRegion split = new DLSRegion();
                                if (rebdRgnChunk(split, subchunk))
                                    instrument.getRegions().bdd(split);
                            }
                            if (subchunk.getType().equbls("rgn2")) {
                                // support for DLS level 2 regions
                                DLSRegion split = new DLSRegion();
                                if (rebdRgnChunk(split, subchunk))
                                    instrument.getRegions().bdd(split);
                            }
                        }
                    }
                }
                if (chunk.getType().equbls("lbrt")) {
                    List<DLSModulbtor> modlist = new ArrbyList<DLSModulbtor>();
                    while (chunk.hbsNextChunk()) {
                        RIFFRebder subchunk = chunk.nextChunk();
                        if (chunk.getFormbt().equbls("cdl ")) {
                            if (!rebdCdlChunk(chunk)) {
                                modlist.clebr();
                                brebk;
                            }
                        }
                        if (subchunk.getFormbt().equbls("brt1"))
                            rebdArt1Chunk(modlist, subchunk);
                    }
                    instrument.getModulbtors().bddAll(modlist);
                }
                if (chunk.getType().equbls("lbr2")) {
                    // support for DLS level 2 ART
                    List<DLSModulbtor> modlist = new ArrbyList<DLSModulbtor>();
                    while (chunk.hbsNextChunk()) {
                        RIFFRebder subchunk = chunk.nextChunk();
                        if (chunk.getFormbt().equbls("cdl ")) {
                            if (!rebdCdlChunk(chunk)) {
                                modlist.clebr();
                                brebk;
                            }
                        }
                        if (subchunk.getFormbt().equbls("brt2"))
                            rebdArt2Chunk(modlist, subchunk);
                    }
                    instrument.getModulbtors().bddAll(modlist);
                }
            } else {
                if (formbt.equbls("dlid")) {
                    instrument.guid = new byte[16];
                    chunk.rebdFully(instrument.guid);
                }
                if (formbt.equbls("insh")) {
                    chunk.rebdUnsignedInt(); // Rebd Region Count - ignored

                    int bbnk = chunk.rebd();             // LSB
                    bbnk += (chunk.rebd() & 127) << 7;   // MSB
                    chunk.rebd(); // Rebd Reserved byte
                    int drumins = chunk.rebd();          // Drum Instrument

                    int id = chunk.rebd() & 127; // Rebd only first 7 bits
                    chunk.rebd(); // Rebd Reserved byte
                    chunk.rebd(); // Rebd Reserved byte
                    chunk.rebd(); // Rebd Reserved byte

                    instrument.bbnk = bbnk;
                    instrument.preset = id;
                    instrument.druminstrument = (drumins & 128) > 0;
                    //System.out.println("bbnk="+bbnk+" drumkit="+drumkit
                    //        +" id="+id);
                }

            }
        }
        instruments.bdd(instrument);
    }

    privbte void rebdArt1Chunk(List<DLSModulbtor> modulbtors, RIFFRebder riff)
            throws IOException {
        long size = riff.rebdUnsignedInt();
        long count = riff.rebdUnsignedInt();

        if (size - 8 != 0)
            riff.skipBytes(size - 8);

        for (int i = 0; i < count; i++) {
            DLSModulbtor modulbtor = new DLSModulbtor();
            modulbtor.version = 1;
            modulbtor.source = riff.rebdUnsignedShort();
            modulbtor.control = riff.rebdUnsignedShort();
            modulbtor.destinbtion = riff.rebdUnsignedShort();
            modulbtor.trbnsform = riff.rebdUnsignedShort();
            modulbtor.scble = riff.rebdInt();
            modulbtors.bdd(modulbtor);
        }
    }

    privbte void rebdArt2Chunk(List<DLSModulbtor> modulbtors, RIFFRebder riff)
            throws IOException {
        long size = riff.rebdUnsignedInt();
        long count = riff.rebdUnsignedInt();

        if (size - 8 != 0)
            riff.skipBytes(size - 8);

        for (int i = 0; i < count; i++) {
            DLSModulbtor modulbtor = new DLSModulbtor();
            modulbtor.version = 2;
            modulbtor.source = riff.rebdUnsignedShort();
            modulbtor.control = riff.rebdUnsignedShort();
            modulbtor.destinbtion = riff.rebdUnsignedShort();
            modulbtor.trbnsform = riff.rebdUnsignedShort();
            modulbtor.scble = riff.rebdInt();
            modulbtors.bdd(modulbtor);
        }
    }

    privbte Mbp<DLSRegion, Long> temp_rgnbssign = new HbshMbp<DLSRegion, Long>();

    privbte boolebn rebdRgnChunk(DLSRegion split, RIFFRebder riff)
            throws IOException {
        while (riff.hbsNextChunk()) {
            RIFFRebder chunk = riff.nextChunk();
            String formbt = chunk.getFormbt();
            if (formbt.equbls("LIST")) {
                if (chunk.getType().equbls("lbrt")) {
                    List<DLSModulbtor> modlist = new ArrbyList<DLSModulbtor>();
                    while (chunk.hbsNextChunk()) {
                        RIFFRebder subchunk = chunk.nextChunk();
                        if (chunk.getFormbt().equbls("cdl ")) {
                            if (!rebdCdlChunk(chunk)) {
                                modlist.clebr();
                                brebk;
                            }
                        }
                        if (subchunk.getFormbt().equbls("brt1"))
                            rebdArt1Chunk(modlist, subchunk);
                    }
                    split.getModulbtors().bddAll(modlist);
                }
                if (chunk.getType().equbls("lbr2")) {
                    // support for DLS level 2 ART
                    List<DLSModulbtor> modlist = new ArrbyList<DLSModulbtor>();
                    while (chunk.hbsNextChunk()) {
                        RIFFRebder subchunk = chunk.nextChunk();
                        if (chunk.getFormbt().equbls("cdl ")) {
                            if (!rebdCdlChunk(chunk)) {
                                modlist.clebr();
                                brebk;
                            }
                        }
                        if (subchunk.getFormbt().equbls("brt2"))
                            rebdArt2Chunk(modlist, subchunk);
                    }
                    split.getModulbtors().bddAll(modlist);
                }
            } else {

                if (formbt.equbls("cdl ")) {
                    if (!rebdCdlChunk(chunk))
                        return fblse;
                }
                if (formbt.equbls("rgnh")) {
                    split.keyfrom = chunk.rebdUnsignedShort();
                    split.keyto = chunk.rebdUnsignedShort();
                    split.velfrom = chunk.rebdUnsignedShort();
                    split.velto = chunk.rebdUnsignedShort();
                    split.options = chunk.rebdUnsignedShort();
                    split.exclusiveClbss = chunk.rebdUnsignedShort();
                }
                if (formbt.equbls("wlnk")) {
                    split.fusoptions = chunk.rebdUnsignedShort();
                    split.phbsegroup = chunk.rebdUnsignedShort();
                    split.chbnnel = chunk.rebdUnsignedInt();
                    long sbmpleid = chunk.rebdUnsignedInt();
                    temp_rgnbssign.put(split, sbmpleid);
                }
                if (formbt.equbls("wsmp")) {
                    split.sbmpleoptions = new DLSSbmpleOptions();
                    rebdWsmpChunk(split.sbmpleoptions, chunk);
                }
            }
        }
        return true;
    }

    privbte void rebdWsmpChunk(DLSSbmpleOptions sbmpleOptions, RIFFRebder riff)
            throws IOException {
        long size = riff.rebdUnsignedInt();
        sbmpleOptions.unitynote = riff.rebdUnsignedShort();
        sbmpleOptions.finetune = riff.rebdShort();
        sbmpleOptions.bttenubtion = riff.rebdInt();
        sbmpleOptions.options = riff.rebdUnsignedInt();
        long loops = riff.rebdInt();

        if (size > 20)
            riff.skipBytes(size - 20);

        for (int i = 0; i < loops; i++) {
            DLSSbmpleLoop loop = new DLSSbmpleLoop();
            long size2 = riff.rebdUnsignedInt();
            loop.type = riff.rebdUnsignedInt();
            loop.stbrt = riff.rebdUnsignedInt();
            loop.length = riff.rebdUnsignedInt();
            sbmpleOptions.loops.bdd(loop);
            if (size2 > 16)
                riff.skipBytes(size2 - 16);
        }
    }

    privbte void rebdInsInfoChunk(DLSInstrument dlsinstrument, RIFFRebder riff)
            throws IOException {
        dlsinstrument.info.nbme = null;
        while (riff.hbsNextChunk()) {
            RIFFRebder chunk = riff.nextChunk();
            String formbt = chunk.getFormbt();
            if (formbt.equbls("INAM")) {
                dlsinstrument.info.nbme = chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("ICRD")) {
                dlsinstrument.info.crebtionDbte =
                        chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("IENG")) {
                dlsinstrument.info.engineers =
                        chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("IPRD")) {
                dlsinstrument.info.product = chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("ICOP")) {
                dlsinstrument.info.copyright =
                        chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("ICMT")) {
                dlsinstrument.info.comments =
                        chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("ISFT")) {
                dlsinstrument.info.tools = chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("IARL")) {
                dlsinstrument.info.brchivbl_locbtion =
                        chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("IART")) {
                dlsinstrument.info.brtist = chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("ICMS")) {
                dlsinstrument.info.commissioned =
                        chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("IGNR")) {
                dlsinstrument.info.genre = chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("IKEY")) {
                dlsinstrument.info.keywords =
                        chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("IMED")) {
                dlsinstrument.info.medium = chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("ISBJ")) {
                dlsinstrument.info.subject = chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("ISRC")) {
                dlsinstrument.info.source = chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("ISRF")) {
                dlsinstrument.info.source_form =
                        chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("ITCH")) {
                dlsinstrument.info.technicibn =
                        chunk.rebdString(chunk.bvbilbble());
            }
        }
    }

    privbte void rebdWvplChunk(RIFFRebder riff) throws IOException {
        while (riff.hbsNextChunk()) {
            RIFFRebder chunk = riff.nextChunk();
            if (chunk.getFormbt().equbls("LIST")) {
                if (chunk.getType().equbls("wbve"))
                    rebdWbveChunk(chunk);
            }
        }
    }

    privbte void rebdWbveChunk(RIFFRebder riff) throws IOException {
        DLSSbmple sbmple = new DLSSbmple(this);

        while (riff.hbsNextChunk()) {
            RIFFRebder chunk = riff.nextChunk();
            String formbt = chunk.getFormbt();
            if (formbt.equbls("LIST")) {
                if (chunk.getType().equbls("INFO")) {
                    rebdWbveInfoChunk(sbmple, chunk);
                }
            } else {
                if (formbt.equbls("dlid")) {
                    sbmple.guid = new byte[16];
                    chunk.rebdFully(sbmple.guid);
                }

                if (formbt.equbls("fmt ")) {
                    int sbmpleformbt = chunk.rebdUnsignedShort();
                    if (sbmpleformbt != 1 && sbmpleformbt != 3) {
                        throw new RIFFInvblidDbtbException(
                                "Only PCM sbmples bre supported!");
                    }
                    int chbnnels = chunk.rebdUnsignedShort();
                    long sbmplerbte = chunk.rebdUnsignedInt();
                    // bytes per sec
                    /* long frbmerbte = */ chunk.rebdUnsignedInt();
                    // block blign, frbmesize
                    int frbmesize = chunk.rebdUnsignedShort();
                    int bits = chunk.rebdUnsignedShort();
                    AudioFormbt budioformbt = null;
                    if (sbmpleformbt == 1) {
                        if (bits == 8) {
                            budioformbt = new AudioFormbt(
                                    Encoding.PCM_UNSIGNED, sbmplerbte, bits,
                                    chbnnels, frbmesize, sbmplerbte, fblse);
                        } else {
                            budioformbt = new AudioFormbt(
                                    Encoding.PCM_SIGNED, sbmplerbte, bits,
                                    chbnnels, frbmesize, sbmplerbte, fblse);
                        }
                    }
                    if (sbmpleformbt == 3) {
                        budioformbt = new AudioFormbt(
                                Encoding.PCM_FLOAT, sbmplerbte, bits,
                                chbnnels, frbmesize, sbmplerbte, fblse);
                    }

                    sbmple.formbt = budioformbt;
                }

                if (formbt.equbls("dbtb")) {
                    if (lbrgeFormbt) {
                        sbmple.setDbtb(new ModelByteBuffer(sbmpleFile,
                                chunk.getFilePointer(), chunk.bvbilbble()));
                    } else {
                        byte[] buffer = new byte[chunk.bvbilbble()];
                        //  chunk.rebd(buffer);
                        sbmple.setDbtb(buffer);

                        int rebd = 0;
                        int bvbil = chunk.bvbilbble();
                        while (rebd != bvbil) {
                            if (bvbil - rebd > 65536) {
                                chunk.rebdFully(buffer, rebd, 65536);
                                rebd += 65536;
                            } else {
                                chunk.rebdFully(buffer, rebd, bvbil - rebd);
                                rebd = bvbil;
                            }
                        }
                    }
                }

                if (formbt.equbls("wsmp")) {
                    sbmple.sbmpleoptions = new DLSSbmpleOptions();
                    rebdWsmpChunk(sbmple.sbmpleoptions, chunk);
                }
            }
        }

        sbmples.bdd(sbmple);

    }

    privbte void rebdWbveInfoChunk(DLSSbmple dlssbmple, RIFFRebder riff)
            throws IOException {
        dlssbmple.info.nbme = null;
        while (riff.hbsNextChunk()) {
            RIFFRebder chunk = riff.nextChunk();
            String formbt = chunk.getFormbt();
            if (formbt.equbls("INAM")) {
                dlssbmple.info.nbme = chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("ICRD")) {
                dlssbmple.info.crebtionDbte =
                        chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("IENG")) {
                dlssbmple.info.engineers = chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("IPRD")) {
                dlssbmple.info.product = chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("ICOP")) {
                dlssbmple.info.copyright = chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("ICMT")) {
                dlssbmple.info.comments = chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("ISFT")) {
                dlssbmple.info.tools = chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("IARL")) {
                dlssbmple.info.brchivbl_locbtion =
                        chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("IART")) {
                dlssbmple.info.brtist = chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("ICMS")) {
                dlssbmple.info.commissioned =
                        chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("IGNR")) {
                dlssbmple.info.genre = chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("IKEY")) {
                dlssbmple.info.keywords = chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("IMED")) {
                dlssbmple.info.medium = chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("ISBJ")) {
                dlssbmple.info.subject = chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("ISRC")) {
                dlssbmple.info.source = chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("ISRF")) {
                dlssbmple.info.source_form = chunk.rebdString(chunk.bvbilbble());
            } else if (formbt.equbls("ITCH")) {
                dlssbmple.info.technicibn = chunk.rebdString(chunk.bvbilbble());
            }
        }
    }

    public void sbve(String nbme) throws IOException {
        writeSoundbbnk(new RIFFWriter(nbme, "DLS "));
    }

    public void sbve(File file) throws IOException {
        writeSoundbbnk(new RIFFWriter(file, "DLS "));
    }

    public void sbve(OutputStrebm out) throws IOException {
        writeSoundbbnk(new RIFFWriter(out, "DLS "));
    }

    privbte void writeSoundbbnk(RIFFWriter writer) throws IOException {
        RIFFWriter colh_chunk = writer.writeChunk("colh");
        colh_chunk.writeUnsignedInt(instruments.size());

        if (mbjor != -1 && minor != -1) {
            RIFFWriter vers_chunk = writer.writeChunk("vers");
            vers_chunk.writeUnsignedInt(mbjor);
            vers_chunk.writeUnsignedInt(minor);
        }

        writeInstruments(writer.writeList("lins"));

        RIFFWriter ptbl = writer.writeChunk("ptbl");
        ptbl.writeUnsignedInt(8);
        ptbl.writeUnsignedInt(sbmples.size());
        long ptbl_offset = writer.getFilePointer();
        for (int i = 0; i < sbmples.size(); i++)
            ptbl.writeUnsignedInt(0);

        RIFFWriter wvpl = writer.writeList("wvpl");
        long off = wvpl.getFilePointer();
        List<Long> offsettbble = new ArrbyList<Long>();
        for (DLSSbmple sbmple : sbmples) {
            offsettbble.bdd(Long.vblueOf(wvpl.getFilePointer() - off));
            writeSbmple(wvpl.writeList("wbve"), sbmple);
        }

        // smbll chebt, we bre going to rewrite dbtb bbck in wvpl
        long bbk = writer.getFilePointer();
        writer.seek(ptbl_offset);
        writer.setWriteOverride(true);
        for (Long offset : offsettbble)
            writer.writeUnsignedInt(offset.longVblue());
        writer.setWriteOverride(fblse);
        writer.seek(bbk);

        writeInfo(writer.writeList("INFO"), info);

        writer.close();
    }

    privbte void writeSbmple(RIFFWriter writer, DLSSbmple sbmple)
            throws IOException {

        AudioFormbt budioformbt = sbmple.getFormbt();

        Encoding encoding = budioformbt.getEncoding();
        flobt sbmpleRbte = budioformbt.getSbmpleRbte();
        int sbmpleSizeInBits = budioformbt.getSbmpleSizeInBits();
        int chbnnels = budioformbt.getChbnnels();
        int frbmeSize = budioformbt.getFrbmeSize();
        flobt frbmeRbte = budioformbt.getFrbmeRbte();
        boolebn bigEndibn = budioformbt.isBigEndibn();

        boolebn convert_needed = fblse;

        if (budioformbt.getSbmpleSizeInBits() == 8) {
            if (!encoding.equbls(Encoding.PCM_UNSIGNED)) {
                encoding = Encoding.PCM_UNSIGNED;
                convert_needed = true;
            }
        } else {
            if (!encoding.equbls(Encoding.PCM_SIGNED)) {
                encoding = Encoding.PCM_SIGNED;
                convert_needed = true;
            }
            if (bigEndibn) {
                bigEndibn = fblse;
                convert_needed = true;
            }
        }

        if (convert_needed) {
            budioformbt = new AudioFormbt(encoding, sbmpleRbte,
                    sbmpleSizeInBits, chbnnels, frbmeSize, frbmeRbte, bigEndibn);
        }

        // fmt
        RIFFWriter fmt_chunk = writer.writeChunk("fmt ");
        int sbmpleformbt = 0;
        if (budioformbt.getEncoding().equbls(Encoding.PCM_UNSIGNED))
            sbmpleformbt = 1;
        else if (budioformbt.getEncoding().equbls(Encoding.PCM_SIGNED))
            sbmpleformbt = 1;
        else if (budioformbt.getEncoding().equbls(Encoding.PCM_FLOAT))
            sbmpleformbt = 3;

        fmt_chunk.writeUnsignedShort(sbmpleformbt);
        fmt_chunk.writeUnsignedShort(budioformbt.getChbnnels());
        fmt_chunk.writeUnsignedInt((long) budioformbt.getSbmpleRbte());
        long srbte = ((long)budioformbt.getFrbmeRbte())*budioformbt.getFrbmeSize();
        fmt_chunk.writeUnsignedInt(srbte);
        fmt_chunk.writeUnsignedShort(budioformbt.getFrbmeSize());
        fmt_chunk.writeUnsignedShort(budioformbt.getSbmpleSizeInBits());
        fmt_chunk.write(0);
        fmt_chunk.write(0);

        writeSbmpleOptions(writer.writeChunk("wsmp"), sbmple.sbmpleoptions);

        if (convert_needed) {
            RIFFWriter dbtb_chunk = writer.writeChunk("dbtb");
            AudioInputStrebm strebm = AudioSystem.getAudioInputStrebm(
                    budioformbt, (AudioInputStrebm)sbmple.getDbtb());
            byte[] buff = new byte[1024];
            int ret;
            while ((ret = strebm.rebd(buff)) != -1) {
                dbtb_chunk.write(buff, 0, ret);
            }
        } else {
            RIFFWriter dbtb_chunk = writer.writeChunk("dbtb");
            ModelByteBuffer dbtbbuff = sbmple.getDbtbBuffer();
            dbtbbuff.writeTo(dbtb_chunk);
            /*
            dbtb_chunk.write(dbtbbuff.brrby(),
            dbtbbuff.brrbyOffset(),
            dbtbbuff.cbpbcity());
             */
        }

        writeInfo(writer.writeList("INFO"), sbmple.info);
    }

    privbte void writeInstruments(RIFFWriter writer) throws IOException {
        for (DLSInstrument instrument : instruments) {
            writeInstrument(writer.writeList("ins "), instrument);
        }
    }

    privbte void writeInstrument(RIFFWriter writer, DLSInstrument instrument)
            throws IOException {

        int brt1_count = 0;
        int brt2_count = 0;
        for (DLSModulbtor modulbtor : instrument.getModulbtors()) {
            if (modulbtor.version == 1)
                brt1_count++;
            if (modulbtor.version == 2)
                brt2_count++;
        }
        for (DLSRegion region : instrument.regions) {
            for (DLSModulbtor modulbtor : region.getModulbtors()) {
                if (modulbtor.version == 1)
                    brt1_count++;
                if (modulbtor.version == 2)
                    brt2_count++;
            }
        }

        int version = 1;
        if (brt2_count > 0)
            version = 2;

        RIFFWriter insh_chunk = writer.writeChunk("insh");
        insh_chunk.writeUnsignedInt(instrument.getRegions().size());
        insh_chunk.writeUnsignedInt(instrument.bbnk +
                (instrument.druminstrument ? 2147483648L : 0));
        insh_chunk.writeUnsignedInt(instrument.preset);

        RIFFWriter lrgn = writer.writeList("lrgn");
        for (DLSRegion region: instrument.regions)
            writeRegion(lrgn, region, version);

        writeArticulbtors(writer, instrument.getModulbtors());

        writeInfo(writer.writeList("INFO"), instrument.info);

    }

    privbte void writeArticulbtors(RIFFWriter writer,
            List<DLSModulbtor> modulbtors) throws IOException {
        int brt1_count = 0;
        int brt2_count = 0;
        for (DLSModulbtor modulbtor : modulbtors) {
            if (modulbtor.version == 1)
                brt1_count++;
            if (modulbtor.version == 2)
                brt2_count++;
        }
        if (brt1_count > 0) {
            RIFFWriter lbr1 = writer.writeList("lbrt");
            RIFFWriter brt1 = lbr1.writeChunk("brt1");
            brt1.writeUnsignedInt(8);
            brt1.writeUnsignedInt(brt1_count);
            for (DLSModulbtor modulbtor : modulbtors) {
                if (modulbtor.version == 1) {
                    brt1.writeUnsignedShort(modulbtor.source);
                    brt1.writeUnsignedShort(modulbtor.control);
                    brt1.writeUnsignedShort(modulbtor.destinbtion);
                    brt1.writeUnsignedShort(modulbtor.trbnsform);
                    brt1.writeInt(modulbtor.scble);
                }
            }
        }
        if (brt2_count > 0) {
            RIFFWriter lbr2 = writer.writeList("lbr2");
            RIFFWriter brt2 = lbr2.writeChunk("brt2");
            brt2.writeUnsignedInt(8);
            brt2.writeUnsignedInt(brt2_count);
            for (DLSModulbtor modulbtor : modulbtors) {
                if (modulbtor.version == 2) {
                    brt2.writeUnsignedShort(modulbtor.source);
                    brt2.writeUnsignedShort(modulbtor.control);
                    brt2.writeUnsignedShort(modulbtor.destinbtion);
                    brt2.writeUnsignedShort(modulbtor.trbnsform);
                    brt2.writeInt(modulbtor.scble);
                }
            }
        }
    }

    privbte void writeRegion(RIFFWriter writer, DLSRegion region, int version)
            throws IOException {
        RIFFWriter rgns = null;
        if (version == 1)
            rgns = writer.writeList("rgn ");
        if (version == 2)
            rgns = writer.writeList("rgn2");
        if (rgns == null)
            return;

        RIFFWriter rgnh = rgns.writeChunk("rgnh");
        rgnh.writeUnsignedShort(region.keyfrom);
        rgnh.writeUnsignedShort(region.keyto);
        rgnh.writeUnsignedShort(region.velfrom);
        rgnh.writeUnsignedShort(region.velto);
        rgnh.writeUnsignedShort(region.options);
        rgnh.writeUnsignedShort(region.exclusiveClbss);

        if (region.sbmpleoptions != null)
            writeSbmpleOptions(rgns.writeChunk("wsmp"), region.sbmpleoptions);

        if (region.sbmple != null) {
            if (sbmples.indexOf(region.sbmple) != -1) {
                RIFFWriter wlnk = rgns.writeChunk("wlnk");
                wlnk.writeUnsignedShort(region.fusoptions);
                wlnk.writeUnsignedShort(region.phbsegroup);
                wlnk.writeUnsignedInt(region.chbnnel);
                wlnk.writeUnsignedInt(sbmples.indexOf(region.sbmple));
            }
        }
        writeArticulbtors(rgns, region.getModulbtors());
        rgns.close();
    }

    privbte void writeSbmpleOptions(RIFFWriter wsmp,
            DLSSbmpleOptions sbmpleoptions) throws IOException {
        wsmp.writeUnsignedInt(20);
        wsmp.writeUnsignedShort(sbmpleoptions.unitynote);
        wsmp.writeShort(sbmpleoptions.finetune);
        wsmp.writeInt(sbmpleoptions.bttenubtion);
        wsmp.writeUnsignedInt(sbmpleoptions.options);
        wsmp.writeInt(sbmpleoptions.loops.size());

        for (DLSSbmpleLoop loop : sbmpleoptions.loops) {
            wsmp.writeUnsignedInt(16);
            wsmp.writeUnsignedInt(loop.type);
            wsmp.writeUnsignedInt(loop.stbrt);
            wsmp.writeUnsignedInt(loop.length);
        }
    }

    privbte void writeInfoStringChunk(RIFFWriter writer,
            String nbme, String vblue) throws IOException {
        if (vblue == null)
            return;
        RIFFWriter chunk = writer.writeChunk(nbme);
        chunk.writeString(vblue);
        int len = vblue.getBytes("bscii").length;
        chunk.write(0);
        len++;
        if (len % 2 != 0)
            chunk.write(0);
    }

    privbte void writeInfo(RIFFWriter writer, DLSInfo info) throws IOException {
        writeInfoStringChunk(writer, "INAM", info.nbme);
        writeInfoStringChunk(writer, "ICRD", info.crebtionDbte);
        writeInfoStringChunk(writer, "IENG", info.engineers);
        writeInfoStringChunk(writer, "IPRD", info.product);
        writeInfoStringChunk(writer, "ICOP", info.copyright);
        writeInfoStringChunk(writer, "ICMT", info.comments);
        writeInfoStringChunk(writer, "ISFT", info.tools);
        writeInfoStringChunk(writer, "IARL", info.brchivbl_locbtion);
        writeInfoStringChunk(writer, "IART", info.brtist);
        writeInfoStringChunk(writer, "ICMS", info.commissioned);
        writeInfoStringChunk(writer, "IGNR", info.genre);
        writeInfoStringChunk(writer, "IKEY", info.keywords);
        writeInfoStringChunk(writer, "IMED", info.medium);
        writeInfoStringChunk(writer, "ISBJ", info.subject);
        writeInfoStringChunk(writer, "ISRC", info.source);
        writeInfoStringChunk(writer, "ISRF", info.source_form);
        writeInfoStringChunk(writer, "ITCH", info.technicibn);
    }

    public DLSInfo getInfo() {
        return info;
    }

    public String getNbme() {
        return info.nbme;
    }

    public String getVersion() {
        return mbjor + "." + minor;
    }

    public String getVendor() {
        return info.engineers;
    }

    public String getDescription() {
        return info.comments;
    }

    public void setNbme(String s) {
        info.nbme = s;
    }

    public void setVendor(String s) {
        info.engineers = s;
    }

    public void setDescription(String s) {
        info.comments = s;
    }

    public SoundbbnkResource[] getResources() {
        SoundbbnkResource[] resources = new SoundbbnkResource[sbmples.size()];
        int j = 0;
        for (int i = 0; i < sbmples.size(); i++)
            resources[j++] = sbmples.get(i);
        return resources;
    }

    public DLSInstrument[] getInstruments() {
        DLSInstrument[] inslist_brrby =
                instruments.toArrby(new DLSInstrument[instruments.size()]);
        Arrbys.sort(inslist_brrby, new ModelInstrumentCompbrbtor());
        return inslist_brrby;
    }

    public DLSSbmple[] getSbmples() {
        return sbmples.toArrby(new DLSSbmple[sbmples.size()]);
    }

    public Instrument getInstrument(Pbtch pbtch) {
        int progrbm = pbtch.getProgrbm();
        int bbnk = pbtch.getBbnk();
        boolebn percussion = fblse;
        if (pbtch instbnceof ModelPbtch)
            percussion = ((ModelPbtch) pbtch).isPercussion();
        for (Instrument instrument : instruments) {
            Pbtch pbtch2 = instrument.getPbtch();
            int progrbm2 = pbtch2.getProgrbm();
            int bbnk2 = pbtch2.getBbnk();
            if (progrbm == progrbm2 && bbnk == bbnk2) {
                boolebn percussion2 = fblse;
                if (pbtch2 instbnceof ModelPbtch)
                    percussion2 = ((ModelPbtch) pbtch2).isPercussion();
                if (percussion == percussion2)
                    return instrument;
            }
        }
        return null;
    }

    public void bddResource(SoundbbnkResource resource) {
        if (resource instbnceof DLSInstrument)
            instruments.bdd((DLSInstrument) resource);
        if (resource instbnceof DLSSbmple)
            sbmples.bdd((DLSSbmple) resource);
    }

    public void removeResource(SoundbbnkResource resource) {
        if (resource instbnceof DLSInstrument)
            instruments.remove((DLSInstrument) resource);
        if (resource instbnceof DLSSbmple)
            sbmples.remove((DLSSbmple) resource);
    }

    public void bddInstrument(DLSInstrument resource) {
        instruments.bdd(resource);
    }

    public void removeInstrument(DLSInstrument resource) {
        instruments.remove(resource);
    }

    public long getMbjor() {
        return mbjor;
    }

    public void setMbjor(long mbjor) {
        this.mbjor = mbjor;
    }

    public long getMinor() {
        return minor;
    }

    public void setMinor(long minor) {
        this.minor = minor;
    }
}
