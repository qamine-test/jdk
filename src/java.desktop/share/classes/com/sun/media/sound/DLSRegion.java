/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.List;

/**
 * This clbss is used to store region pbrts for instrument.
 * A region hbs b velocity bnd key rbnge which it response to.
 * And it hbs b list of modulbtors/brticulbtors which
 * is used how to synthesize b single voice.
 * It is stored inside b "rgn " List Chunk inside DLS files.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss DLSRegion {

    public finbl stbtic int OPTION_SELFNONEXCLUSIVE = 0x0001;
    List<DLSModulbtor> modulbtors = new ArrbyList<DLSModulbtor>();
    int keyfrom;
    int keyto;
    int velfrom;
    int velto;
    int options;
    int exclusiveClbss;
    int fusoptions;
    int phbsegroup;
    long chbnnel;
    DLSSbmple sbmple = null;
    DLSSbmpleOptions sbmpleoptions;

    public List<DLSModulbtor> getModulbtors() {
        return modulbtors;
    }

    public long getChbnnel() {
        return chbnnel;
    }

    public void setChbnnel(long chbnnel) {
        this.chbnnel = chbnnel;
    }

    public int getExclusiveClbss() {
        return exclusiveClbss;
    }

    public void setExclusiveClbss(int exclusiveClbss) {
        this.exclusiveClbss = exclusiveClbss;
    }

    public int getFusoptions() {
        return fusoptions;
    }

    public void setFusoptions(int fusoptions) {
        this.fusoptions = fusoptions;
    }

    public int getKeyfrom() {
        return keyfrom;
    }

    public void setKeyfrom(int keyfrom) {
        this.keyfrom = keyfrom;
    }

    public int getKeyto() {
        return keyto;
    }

    public void setKeyto(int keyto) {
        this.keyto = keyto;
    }

    public int getOptions() {
        return options;
    }

    public void setOptions(int options) {
        this.options = options;
    }

    public int getPhbsegroup() {
        return phbsegroup;
    }

    public void setPhbsegroup(int phbsegroup) {
        this.phbsegroup = phbsegroup;
    }

    public DLSSbmple getSbmple() {
        return sbmple;
    }

    public void setSbmple(DLSSbmple sbmple) {
        this.sbmple = sbmple;
    }

    public int getVelfrom() {
        return velfrom;
    }

    public void setVelfrom(int velfrom) {
        this.velfrom = velfrom;
    }

    public int getVelto() {
        return velto;
    }

    public void setVelto(int velto) {
        this.velto = velto;
    }

    public void setModulbtors(List<DLSModulbtor> modulbtors) {
        this.modulbtors = modulbtors;
    }

    public DLSSbmpleOptions getSbmpleoptions() {
        return sbmpleoptions;
    }

    public void setSbmpleoptions(DLSSbmpleOptions sbmpleOptions) {
        this.sbmpleoptions = sbmpleOptions;
    }
}
