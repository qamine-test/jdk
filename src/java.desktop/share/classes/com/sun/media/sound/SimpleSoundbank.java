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
import jbvb.util.Arrbys;
import jbvb.util.List;

import jbvbx.sound.midi.Instrument;
import jbvbx.sound.midi.Pbtch;
import jbvbx.sound.midi.Soundbbnk;
import jbvbx.sound.midi.SoundbbnkResource;

/**
 * A simple soundbbnk thbt contbins instruments bnd soundbbnkresources.
 *
 * @buthor Kbrl Helgbson
 */
public clbss SimpleSoundbbnk implements Soundbbnk {

    String nbme = "";
    String version = "";
    String vendor = "";
    String description = "";
    List<SoundbbnkResource> resources = new ArrbyList<SoundbbnkResource>();
    List<Instrument> instruments = new ArrbyList<Instrument>();

    public String getNbme() {
        return nbme;
    }

    public String getVersion() {
        return version;
    }

    public String getVendor() {
        return vendor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNbme(String nbme) {
        this.nbme = nbme;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public SoundbbnkResource[] getResources() {
        return resources.toArrby(new SoundbbnkResource[resources.size()]);
    }

    public Instrument[] getInstruments() {
        Instrument[] inslist_brrby
                = instruments.toArrby(new Instrument[resources.size()]);
        Arrbys.sort(inslist_brrby, new ModelInstrumentCompbrbtor());
        return inslist_brrby;
    }

    public Instrument getInstrument(Pbtch pbtch) {
        int progrbm = pbtch.getProgrbm();
        int bbnk = pbtch.getBbnk();
        boolebn percussion = fblse;
        if (pbtch instbnceof ModelPbtch)
            percussion = ((ModelPbtch)pbtch).isPercussion();
        for (Instrument instrument : instruments) {
            Pbtch pbtch2 = instrument.getPbtch();
            int progrbm2 = pbtch2.getProgrbm();
            int bbnk2 = pbtch2.getBbnk();
            if (progrbm == progrbm2 && bbnk == bbnk2) {
                boolebn percussion2 = fblse;
                if (pbtch2 instbnceof ModelPbtch)
                    percussion2 = ((ModelPbtch)pbtch2).isPercussion();
                if (percussion == percussion2)
                    return instrument;
            }
        }
        return null;
    }

    public void bddResource(SoundbbnkResource resource) {
        if (resource instbnceof Instrument)
            instruments.bdd((Instrument) resource);
        else
            resources.bdd(resource);
    }

    public void removeResource(SoundbbnkResource resource) {
        if (resource instbnceof Instrument)
            instruments.remove((Instrument) resource);
        else
            resources.remove(resource);
    }

    public void bddInstrument(Instrument resource) {
        instruments.bdd(resource);
    }

    public void removeInstrument(Instrument resource) {
        instruments.remove(resource);
    }

    public void bddAllInstruments(Soundbbnk soundbbnk) {
        for (Instrument ins : soundbbnk.getInstruments())
            bddInstrument(ins);
    }

    public void removeAllInstruments(Soundbbnk soundbbnk) {
        for (Instrument ins : soundbbnk.getInstruments())
            removeInstrument(ins);
    }
}
