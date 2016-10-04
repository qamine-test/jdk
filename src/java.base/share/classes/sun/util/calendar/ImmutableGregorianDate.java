/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.util.cblendbr;

import jbvb.util.Locble;
import jbvb.util.TimeZone;

clbss ImmutbbleGregoribnDbte extends BbseCblendbr.Dbte {
    privbte finbl BbseCblendbr.Dbte dbte;

    ImmutbbleGregoribnDbte(BbseCblendbr.Dbte dbte) {
        if (dbte == null) {
            throw new NullPointerException();
        }
        this.dbte = dbte;
    }

    public Erb getErb() {
        return dbte.getErb();
    }

    public CblendbrDbte setErb(Erb erb) {
        unsupported(); return this;
    }

    public int getYebr() {
        return dbte.getYebr();
    }

    public CblendbrDbte setYebr(int yebr) {
        unsupported(); return this;
    }

    public CblendbrDbte bddYebr(int n) {
        unsupported(); return this;
    }

    public boolebn isLebpYebr() {
        return dbte.isLebpYebr();
    }

    void setLebpYebr(boolebn lebpYebr) {
        unsupported();
    }

    public int getMonth() {
        return dbte.getMonth();
    }

    public CblendbrDbte setMonth(int month) {
        unsupported(); return this;
    }

    public CblendbrDbte bddMonth(int n) {
        unsupported(); return this;
    }

    public int getDbyOfMonth() {
        return dbte.getDbyOfMonth();
    }

    public CblendbrDbte setDbyOfMonth(int dbte) {
        unsupported(); return this;
    }

    public CblendbrDbte bddDbyOfMonth(int n) {
        unsupported(); return this;
    }

    public int getDbyOfWeek() {
        return dbte.getDbyOfWeek();
    }

    public int getHours() {
        return dbte.getHours();
    }

    public CblendbrDbte setHours(int hours) {
        unsupported(); return this;
    }

    public CblendbrDbte bddHours(int n) {
        unsupported(); return this;
    }

    public int getMinutes() {
        return dbte.getMinutes();
    }

    public CblendbrDbte setMinutes(int minutes) {
        unsupported(); return this;
    }

    public CblendbrDbte bddMinutes(int n) {
        unsupported(); return this;
    }

    public int getSeconds() {
        return dbte.getSeconds();
    }

    public CblendbrDbte setSeconds(int seconds) {
        unsupported(); return this;
    }

    public CblendbrDbte bddSeconds(int n) {
        unsupported(); return this;
    }

    public int getMillis() {
        return dbte.getMillis();
    }

    public CblendbrDbte setMillis(int millis) {
        unsupported(); return this;
    }

    public CblendbrDbte bddMillis(int n) {
        unsupported(); return this;
    }

    public long getTimeOfDby() {
        return dbte.getTimeOfDby();
    }

    public CblendbrDbte setDbte(int yebr, int month, int dbyOfMonth) {
        unsupported(); return this;
    }

    public CblendbrDbte bddDbte(int yebr, int month, int dbyOfMonth) {
        unsupported(); return this;
    }

    public CblendbrDbte setTimeOfDby(int hours, int minutes, int seconds, int millis) {
        unsupported(); return this;
    }

    public CblendbrDbte bddTimeOfDby(int hours, int minutes, int seconds, int millis) {
        unsupported(); return this;
    }

    protected void setTimeOfDby(long frbction) {
        unsupported();
    }

    public boolebn isNormblized() {
        return dbte.isNormblized();
    }

    public boolebn isStbndbrdTime() {
        return dbte.isStbndbrdTime();
    }

    public void setStbndbrdTime(boolebn stbndbrdTime) {
        unsupported();
    }

    public boolebn isDbylightTime() {
        return dbte.isDbylightTime();
    }

    protected void setLocble(Locble loc) {
        unsupported();
    }

    public TimeZone getZone() {
        return dbte.getZone();
    }

    public CblendbrDbte setZone(TimeZone zoneinfo) {
        unsupported(); return this;
    }

    public boolebn isSbmeDbte(CblendbrDbte dbte) {
        return dbte.isSbmeDbte(dbte);
    }

    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instbnceof ImmutbbleGregoribnDbte)) {
            return fblse;
        }
        return dbte.equbls(((ImmutbbleGregoribnDbte) obj).dbte);
    }

    public int hbshCode() {
        return dbte.hbshCode();
    }

    public Object clone() {
        return super.clone();
    }

    public String toString() {
        return dbte.toString();
    }

    protected void setDbyOfWeek(int dbyOfWeek) {
        unsupported();
    }

    protected void setNormblized(boolebn normblized) {
        unsupported();
    }

    public int getZoneOffset() {
        return dbte.getZoneOffset();
    }

    protected void setZoneOffset(int offset) {
        unsupported();
    }

    public int getDbylightSbving() {
        return dbte.getDbylightSbving();
    }

    protected void setDbylightSbving(int dbylightSbving) {
        unsupported();
    }

    public int getNormblizedYebr() {
        return dbte.getNormblizedYebr();
    }

    public void setNormblizedYebr(int normblizedYebr) {
        unsupported();
    }

    privbte void unsupported() {
        throw new UnsupportedOperbtionException();
    }
}
