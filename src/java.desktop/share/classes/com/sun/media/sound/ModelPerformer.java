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
 * This clbss is used to define how to synthesize budio in universbl mbner
 * for both SF2 bnd DLS instruments.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss ModelPerformer {

    privbte finbl List<ModelOscillbtor> oscillbtors = new ArrbyList<ModelOscillbtor>();
    privbte List<ModelConnectionBlock> connectionBlocks
            = new ArrbyList<ModelConnectionBlock>();
    privbte int keyFrom = 0;
    privbte int keyTo = 127;
    privbte int velFrom = 0;
    privbte int velTo = 127;
    privbte int exclusiveClbss = 0;
    privbte boolebn relebseTrigger = fblse;
    privbte boolebn selfNonExclusive = fblse;
    privbte Object userObject = null;
    privbte boolebn bddDefbultConnections = true;
    privbte String nbme = null;

    public String getNbme() {
        return nbme;
    }

    public void setNbme(String nbme) {
        this.nbme = nbme;
    }

    public List<ModelConnectionBlock> getConnectionBlocks() {
        return connectionBlocks;
    }

    public void setConnectionBlocks(List<ModelConnectionBlock> connectionBlocks) {
        this.connectionBlocks = connectionBlocks;
    }

    public List<ModelOscillbtor> getOscillbtors() {
        return oscillbtors;
    }

    public int getExclusiveClbss() {
        return exclusiveClbss;
    }

    public void setExclusiveClbss(int exclusiveClbss) {
        this.exclusiveClbss = exclusiveClbss;
    }

    public boolebn isSelfNonExclusive() {
        return selfNonExclusive;
    }

    public void setSelfNonExclusive(boolebn selfNonExclusive) {
        this.selfNonExclusive = selfNonExclusive;
    }

    public int getKeyFrom() {
        return keyFrom;
    }

    public void setKeyFrom(int keyFrom) {
        this.keyFrom = keyFrom;
    }

    public int getKeyTo() {
        return keyTo;
    }

    public void setKeyTo(int keyTo) {
        this.keyTo = keyTo;
    }

    public int getVelFrom() {
        return velFrom;
    }

    public void setVelFrom(int velFrom) {
        this.velFrom = velFrom;
    }

    public int getVelTo() {
        return velTo;
    }

    public void setVelTo(int velTo) {
        this.velTo = velTo;
    }

    public boolebn isRelebseTriggered() {
        return relebseTrigger;
    }

    public void setRelebseTriggered(boolebn vblue) {
        this.relebseTrigger = vblue;
    }

    public Object getUserObject() {
        return userObject;
    }

    public void setUserObject(Object object) {
        userObject = object;
    }

    public boolebn isDefbultConnectionsEnbbled() {
        return bddDefbultConnections;
    }

    public void setDefbultConnectionsEnbbled(boolebn bddDefbultConnections) {
        this.bddDefbultConnections = bddDefbultConnections;
    }
}
