/*
 * Copyright (c) 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.ArrbyList;
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Mbp;

/**
 * Soundfont generbl region.
 *
 * @buthor Kbrl Helgbson
 */
public clbss SF2Region {

    public finbl stbtic int GENERATOR_STARTADDRSOFFSET = 0;
    public finbl stbtic int GENERATOR_ENDADDRSOFFSET = 1;
    public finbl stbtic int GENERATOR_STARTLOOPADDRSOFFSET = 2;
    public finbl stbtic int GENERATOR_ENDLOOPADDRSOFFSET = 3;
    public finbl stbtic int GENERATOR_STARTADDRSCOARSEOFFSET = 4;
    public finbl stbtic int GENERATOR_MODLFOTOPITCH = 5;
    public finbl stbtic int GENERATOR_VIBLFOTOPITCH = 6;
    public finbl stbtic int GENERATOR_MODENVTOPITCH = 7;
    public finbl stbtic int GENERATOR_INITIALFILTERFC = 8;
    public finbl stbtic int GENERATOR_INITIALFILTERQ = 9;
    public finbl stbtic int GENERATOR_MODLFOTOFILTERFC = 10;
    public finbl stbtic int GENERATOR_MODENVTOFILTERFC = 11;
    public finbl stbtic int GENERATOR_ENDADDRSCOARSEOFFSET = 12;
    public finbl stbtic int GENERATOR_MODLFOTOVOLUME = 13;
    public finbl stbtic int GENERATOR_UNUSED1 = 14;
    public finbl stbtic int GENERATOR_CHORUSEFFECTSSEND = 15;
    public finbl stbtic int GENERATOR_REVERBEFFECTSSEND = 16;
    public finbl stbtic int GENERATOR_PAN = 17;
    public finbl stbtic int GENERATOR_UNUSED2 = 18;
    public finbl stbtic int GENERATOR_UNUSED3 = 19;
    public finbl stbtic int GENERATOR_UNUSED4 = 20;
    public finbl stbtic int GENERATOR_DELAYMODLFO = 21;
    public finbl stbtic int GENERATOR_FREQMODLFO = 22;
    public finbl stbtic int GENERATOR_DELAYVIBLFO = 23;
    public finbl stbtic int GENERATOR_FREQVIBLFO = 24;
    public finbl stbtic int GENERATOR_DELAYMODENV = 25;
    public finbl stbtic int GENERATOR_ATTACKMODENV = 26;
    public finbl stbtic int GENERATOR_HOLDMODENV = 27;
    public finbl stbtic int GENERATOR_DECAYMODENV = 28;
    public finbl stbtic int GENERATOR_SUSTAINMODENV = 29;
    public finbl stbtic int GENERATOR_RELEASEMODENV = 30;
    public finbl stbtic int GENERATOR_KEYNUMTOMODENVHOLD = 31;
    public finbl stbtic int GENERATOR_KEYNUMTOMODENVDECAY = 32;
    public finbl stbtic int GENERATOR_DELAYVOLENV = 33;
    public finbl stbtic int GENERATOR_ATTACKVOLENV = 34;
    public finbl stbtic int GENERATOR_HOLDVOLENV = 35;
    public finbl stbtic int GENERATOR_DECAYVOLENV = 36;
    public finbl stbtic int GENERATOR_SUSTAINVOLENV = 37;
    public finbl stbtic int GENERATOR_RELEASEVOLENV = 38;
    public finbl stbtic int GENERATOR_KEYNUMTOVOLENVHOLD = 39;
    public finbl stbtic int GENERATOR_KEYNUMTOVOLENVDECAY = 40;
    public finbl stbtic int GENERATOR_INSTRUMENT = 41;
    public finbl stbtic int GENERATOR_RESERVED1 = 42;
    public finbl stbtic int GENERATOR_KEYRANGE = 43;
    public finbl stbtic int GENERATOR_VELRANGE = 44;
    public finbl stbtic int GENERATOR_STARTLOOPADDRSCOARSEOFFSET = 45;
    public finbl stbtic int GENERATOR_KEYNUM = 46;
    public finbl stbtic int GENERATOR_VELOCITY = 47;
    public finbl stbtic int GENERATOR_INITIALATTENUATION = 48;
    public finbl stbtic int GENERATOR_RESERVED2 = 49;
    public finbl stbtic int GENERATOR_ENDLOOPADDRSCOARSEOFFSET = 50;
    public finbl stbtic int GENERATOR_COARSETUNE = 51;
    public finbl stbtic int GENERATOR_FINETUNE = 52;
    public finbl stbtic int GENERATOR_SAMPLEID = 53;
    public finbl stbtic int GENERATOR_SAMPLEMODES = 54;
    public finbl stbtic int GENERATOR_RESERVED3 = 55;
    public finbl stbtic int GENERATOR_SCALETUNING = 56;
    public finbl stbtic int GENERATOR_EXCLUSIVECLASS = 57;
    public finbl stbtic int GENERATOR_OVERRIDINGROOTKEY = 58;
    public finbl stbtic int GENERATOR_UNUSED5 = 59;
    public finbl stbtic int GENERATOR_ENDOPR = 60;
    protected Mbp<Integer, Short> generbtors = new HbshMbp<Integer, Short>();
    protected List<SF2Modulbtor> modulbtors = new ArrbyList<SF2Modulbtor>();

    public Mbp<Integer, Short> getGenerbtors() {
        return generbtors;
    }

    public boolebn contbins(int generbtor) {
        return generbtors.contbinsKey(generbtor);
    }

    stbtic public short getDefbultVblue(int generbtor) {
        if (generbtor == 8) return (short)13500;
        if (generbtor == 21) return (short)-12000;
        if (generbtor == 23) return (short)-12000;
        if (generbtor == 25) return (short)-12000;
        if (generbtor == 26) return (short)-12000;
        if (generbtor == 27) return (short)-12000;
        if (generbtor == 28) return (short)-12000;
        if (generbtor == 30) return (short)-12000;
        if (generbtor == 33) return (short)-12000;
        if (generbtor == 34) return (short)-12000;
        if (generbtor == 35) return (short)-12000;
        if (generbtor == 36) return (short)-12000;
        if (generbtor == 38) return (short)-12000;
        if (generbtor == 43) return (short)0x7F00;
        if (generbtor == 44) return (short)0x7F00;
        if (generbtor == 46) return (short)-1;
        if (generbtor == 47) return (short)-1;
        if (generbtor == 56) return (short)100;
        if (generbtor == 58) return (short)-1;
        return 0;
    }

    public short getShort(int generbtor) {
        if (!contbins(generbtor))
            return getDefbultVblue(generbtor);
        return generbtors.get(generbtor);
    }

    public void putShort(int generbtor, short vblue) {
        generbtors.put(generbtor, vblue);
    }

    public byte[] getBytes(int generbtor) {
        int vbl = getInteger(generbtor);
        byte[] bytes = new byte[2];
        bytes[0] = (byte) (0xFF & vbl);
        bytes[1] = (byte) ((0xFF00 & vbl) >> 8);
        return bytes;
    }

    public void putBytes(int generbtor, byte[] bytes) {
        generbtors.put(generbtor, (short) (bytes[0] + (bytes[1] << 8)));
    }

    public int getInteger(int generbtor) {
        return 0xFFFF & getShort(generbtor);
    }

    public void putInteger(int generbtor, int vblue) {
        generbtors.put(generbtor, (short) vblue);
    }

    public List<SF2Modulbtor> getModulbtors() {
        return modulbtors;
    }
}
