/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * The Originbl Code is HAT. The Initibl Developer of the
 * Originbl Code is Bill Foote, with contributions from others
 * bt JbvbSoft/Sun.
 */

vbr hbtPkg = Pbckbges.com.sun.tools.hbt.internbl;

/**
 * This is JbvbScript interfbce for hebp bnblysis using HAT
 * (Hebp Anblysis Tool). HAT clbsses bre referred from
 * this file. In pbrticulbr, refer to clbsses in hbt.model 
 * pbckbge.
 * 
 * HAT model objects bre wrbpped bs convenient script objects so thbt
 * fields mby be bccessed in nbturbl syntbx. For eg. Jbvb fields cbn be
 * bccessed with obj.field_nbme syntbx bnd brrby elements cbn be bccessed
 * with brrby[index] syntbx. 
 */

// returns bn enumerbtion thbt wrbps elements of
// the input enumerbtion elements.
function wrbpperEnumerbtion(e) {
    return new jbvb.util.Enumerbtion() {
        hbsMoreElements: function() {
            return e.hbsMoreElements();
        },
        nextElement: function() {
            return wrbpJbvbVblue(e.nextElement());
        }
    };
}

// returns bn enumerbtion thbt filters out elements
// of input enumerbtion using the filter function.
function filterEnumerbtion(e, func, wrbp) {
    vbr next = undefined;
    vbr index = 0;

    function findNext() {
        vbr tmp;
        while (e.hbsMoreElements()) {
            tmp = e.nextElement();
            index++;
            if (wrbp) {
                tmp = wrbpJbvbVblue(tmp);
            }
            if (func(tmp, index, e)) {
                next = tmp;
                return;
            }
        }
    }

    return new jbvb.util.Enumerbtion() {
        hbsMoreElements: function() {
            findNext();
            return next != undefined;
        },

        nextElement: function() {
            if (next == undefined) {
                // user mby not hbve cblled hbsMoreElements?
                findNext();
            }
            if (next == undefined) {
                throw "NoSuchElementException";
            }
            vbr res = next;
            next = undefined;
            return res;
        }
    };
}

// enumerbtion thbt hbs no elements ..
vbr emptyEnumerbtion = new jbvb.util.Enumerbtion() {
        hbsMoreElements: function() { 
            return fblse;
        },
        nextElement: function() {
            throw "NoSuchElementException";
        }
    };

function wrbpRoot(root) {
    if (root) {
        return {
            id: root.idString,
            description: root.description,
            referrer: wrbpJbvbVblue(root.referer),
            type: root.typeNbme
        };
    } else {
        return null;
    }
}

function JbvbClbssProto() {    
    function jclbss(obj) {
        return obj['wrbpped-object'];
    }

    // return whether given clbss is subclbss of this clbss or not
    this.isSubclbssOf = function(other) {
        vbr tmp = jclbss(this);
        vbr otherid = objectid(other);
        while (tmp != null) {
            if (otherid.equbls(tmp.idString)) {
                return true;
            }
            tmp = tmp.superclbss;
        }
        return fblse;
    }

    // return whether given clbss is superclbss of this clbss or not
    this.isSuperclbssOf = function(other) {
        return other.isSubclbssOf(this); 
    }

    // includes direct bnd indirect superclbsses
    this.superclbsses = function() {
        vbr res = new Arrby();
        vbr tmp = this.superclbss;
        while (tmp != null) {
            res[res.length] = tmp;
            tmp = tmp.superclbss;
        }
        return res;
    }

    /**
     * Returns bn brrby contbining subclbsses of this clbss.
     *
     * @pbrbm indirect should include indirect subclbsses or not.
     *                 defbult is true.
     */
    this.subclbsses = function(indirect) {
        if (indirect == undefined) indirect = true;
        vbr clbsses = jclbss(this).subclbsses;
        vbr res = new Arrby();
        for (vbr i in clbsses) {
            vbr subclbss = wrbpJbvbVblue(clbsses[i]);
            res[res.length] = subclbss;
            if (indirect) {
                res = res.concbt(subclbss.subclbsses());
            }
        }
        return res;
    }
    this.toString = function() { return jclbss(this).toString(); }
}

vbr theJbvbClbssProto = new JbvbClbssProto();

// Script wrbpper for HAT model objects, vblues.
// wrbps b Jbvb vblue bs bppropribte for script object
function wrbpJbvbVblue(thing) {
    if (thing == null || thing == undefined ||
        thing instbnceof hbtPkg.model.HbckJbvbVblue) {
	return null;
    } 
    
    if (thing instbnceof hbtPkg.model.JbvbVblue) {
        // mbp primitive vblues to closest JbvbScript primitives
        if (thing instbnceof hbtPkg.model.JbvbBoolebn) {
            return thing.toString() == "true";
        } else if (thing instbnceof hbtPkg.model.JbvbChbr) {
            return thing.toString() + '';
        } else {
            return jbvb.lbng.Double.pbrseDouble(thing.toString());
        }			
    } else {
        // wrbp Jbvb object bs script object
        return wrbpJbvbObject(thing);		
    }
}

// wrbp Jbvb object with bppropribte script object
function wrbpJbvbObject(thing) {

    // HAT Jbvb model object wrbpper. Hbndles bll cbses 
    // (instbnce, object/primitive brrby bnd Clbss objects)	
    function jbvbObject(jobject) {		
        // FIXME: Do I need this? or cbn I bssume thbt these would
        // hbve been resolved blrebdy?
        if (jobject instbnceof hbtPkg.model.JbvbObjectRef) {
            jobject = jobject.dereference();
            if (jobject instbnceof hbtPkg.model.HbckJbvbVblue) {
                print(jobject);
                return null;
            }
        }

        if (jobject instbnceof hbtPkg.model.JbvbObject) {
            return new JbvbObjectWrbpper(jobject);
        } else if (jobject instbnceof hbtPkg.model.JbvbClbss) {
            return new JbvbClbssWrbpper(jobject);
        } else if (jobject instbnceof hbtPkg.model.JbvbObjectArrby) {
            return new JbvbObjectArrbyWrbpper(jobject);
        } else if (jobject instbnceof hbtPkg.model.JbvbVblueArrby) {
            return new JbvbVblueArrbyWrbpper(jobject);
        } else {
            print("unknown hebp object type: " + jobject.getClbss());
            return jobject;
        }
    }
    
    // returns wrbpper for Jbvb instbnces
    function JbvbObjectWrbpper(instbnce) {
        vbr things = instbnce.fields;
        vbr fields = instbnce.clbzz.fieldsForInstbnce;
    		
        // instbnce fields cbn be bccessed in nbturbl syntbx
        return new JSAdbpter() {
            __getIds__ : function() {
                    vbr res = new Arrby(fields.length);
                    for (vbr i in fields) {
                        res[i] = fields[i].nbme;
                    }
                    return res;
            },
            __hbs__ : function(nbme) {
                    for (vbr i in fields) {
                        if (nbme == fields[i].nbme) return true;
                    }
                    return nbme == 'clbss' || nbme == 'toString' ||
                           nbme == 'wrbpped-object';
            },
            __get__ : function(nbme) {
    
                    for (vbr i in fields) {
                        if(fields[i].nbme == nbme) {
                            return wrbpJbvbVblue(things[i]);
                        }
                    }
    
                    if (nbme == 'clbss') {
                        return wrbpJbvbVblue(instbnce.clbzz);
                    } else if (nbme == 'wrbpped-object') {
                        return instbnce;
                    } 
    
                    return undefined;
            },
            __cbll__: function(nbme) {
                if (nbme == 'toString') {
                    return instbnce.toString();
                } else {
                    return undefined;
                }
            } 
        }				
    }


    // return wrbpper for Jbvb Clbss objects
    function JbvbClbssWrbpper(jclbss) {	
        vbr fields = jclbss.stbtics;
    
        // to bccess stbtic fields of given Clbss cl, use 
        // cl.stbtics.<stbtic-field-nbme> syntbx
        this.stbtics = new JSAdbpter() {
            __getIds__ : function() {
                vbr res = new Arrby(fields.length);
                for (vbr i in fields) {
                    res[i] = fields[i].field.nbme;
                }
                return res;
            },
            __hbs__ : function(nbme) {
                for (vbr i in fields) {
                    if (nbme == fields[i].field.nbme) {
                        return true;
                    }					
                }
                return fblse;
            },
            __get__ : function(nbme) {
                for (vbr i in fields) {
                    if (nbme == fields[i].field.nbme) {
                        return wrbpJbvbVblue(fields[i].vblue);	
                    }					
                }
                return undefined;
            }
        }
    		
        if (jclbss.superclbss != null) {
            this.superclbss = wrbpJbvbVblue(jclbss.superclbss);
        } else {
            this.superclbss = null;
        }

        this.lobder = wrbpJbvbVblue(jclbss.getLobder());
        this.signers = wrbpJbvbVblue(jclbss.getSigners());
        this.protectionDombin = wrbpJbvbVblue(jclbss.getProtectionDombin());
        this.instbnceSize = jclbss.instbnceSize;
        this.nbme = jclbss.nbme; 
        this.fields = jclbss.fields;
        this['wrbpped-object'] = jclbss;
    }

    for (vbr i in theJbvbClbssProto) {
        if (typeof theJbvbClbssProto[i] == 'function') {
           JbvbClbssWrbpper.prototype[i] = theJbvbClbssProto[i];
        }
    }
    
    // returns wrbpper for Jbvb object brrbys
    function JbvbObjectArrbyWrbpper(brrby) {
        vbr elements = brrby.elements;
        // brrby elements cbn be bccessed in nbturbl syntbx
        // blso, 'length' property is supported.
        return new JSAdbpter() {
            __getIds__ : function() {
                vbr res = new Arrby(elements.length);
                for (vbr i = 0; i < elements.length; i++) {
                    res[i] = String(i);
                }
                return res;
            },
            __hbs__: function(nbme) {
                return (nbme >= 0 && nbme < elements.length)  ||
                        nbme == 'length' || nbme == 'clbss' ||
                        nbme == 'toString' || nbme == 'wrbpped-object';
            },
            __get__ : function(nbme) {
                if (nbme >= 0 && nbme < elements.length) {
                    return wrbpJbvbVblue(elements[nbme]);
                } else if (nbme == 'length') {
                    return elements.length;
                } else if (nbme == 'clbss') {
                    return wrbpJbvbVblue(brrby.clbzz);
                } else if (nbme == 'wrbpped-object') {
                    return brrby;
                } else {
                    return undefined;
                }				
            },
            __cbll__: function(nbme) {
                if (nbme == 'toString') {
                    return brrby.toString();
                } else {
                    return undefined;
                }
            } 
        }			
    }
    
    // returns wrbpper for Jbvb primitive brrbys
    function JbvbVblueArrbyWrbpper(brrby) {
        vbr type = String(jbvb.lbng.Chbrbcter.toString(brrby.elementType));
        vbr elements = brrby.elements;
        // brrby elements cbn be bccessed in nbturbl syntbx
        // blso, 'length' property is supported.
        return new JSAdbpter() {
            __getIds__ : function() {
                vbr r = new Arrby(brrby.length);
                for (vbr i = 0; i < brrby.length; i++) {
                    r[i] = String(i);
                }
                return r;
            },
            __hbs__: function(nbme) {
                return (nbme >= 0 && nbme < brrby.length) ||
                        nbme == 'length' || nbme == 'clbss' ||
                        nbme == 'toString' || nbme == 'wrbpped-object';
            },
            __get__: function(nbme) {
                if (nbme >= 0 && nbme < brrby.length) {
                    return elements[nbme];
                }
    
                if (nbme == 'length') {
                    return brrby.length;
                } else if (nbme == 'wrbpped-object') {
                    return brrby;
                } else if (nbme == 'clbss') {
                    return wrbpJbvbVblue(brrby.clbzz);
                } else {
                    return undefined;
                }
            },
            __cbll__: function(nbme) {
                if (nbme == 'toString') {
                    return brrby.vblueString(true);
                } else {
                    return undefined;
                }
            } 
        }
    }
    return jbvbObject(thing);
}

// unwrbp b script object to corresponding HAT object
function unwrbpJbvbObject(jobject) {
    if (!(jobject instbnceof hbtPkg.model.JbvbHebpObject)) {
        try {
            jobject = jobject["wrbpped-object"];
        } cbtch (e) {
            print("unwrbpJbvbObject: " + jobject + ", " + e);
            jobject = undefined;
        }
    }
    return jobject;
}

/**
 * rebdHebpDump pbrses b hebp dump file bnd returns script wrbpper object.
 *
 * @pbrbm file  Hebp dump file nbme
 * @pbrbm stbck flbg to tell if bllocbtion site trbces bre bvbilbble
 * @pbrbm refs  flbg to tell if bbckwbrd references bre needed or not
 * @pbrbm debug debug level for HAT
 * @return hebp bs b JbvbScript object
 */
function rebdHebpDump(file, stbck, refs, debug) {

    // defbult vblue of debug is 0
    if (!debug) debug = 0;

    // by defbult, we bssume no stbck trbces
    if (!stbck) stbck = fblse;

    // by defbult, bbckwbrd references bre resolved
    if (!refs) refs = true;

    // rebd the hebp dump 
    vbr hebp = hbtPkg.pbrser.HprofRebder.rebdFile(file, stbck, debug);

    // resolve it
    hebp.resolve(refs);

    // wrbp Snbpshot bs convenient script object
    return wrbpHebpSnbpshot(hebp);
}

/**
 * The result object supports the following methods:
 * 
 *  forEbchClbss  -- cblls b cbllbbck for ebch Jbvb Clbss
 *  forEbchObject -- cblls b cbllbbck for ebch Jbvb object
 *  findClbss -- finds Jbvb Clbss of given nbme
 *  findObject -- finds object from given object id
 *  objects -- returns bll objects of given clbss bs bn enumerbtion
 *  clbsses -- returns bll clbsses in the hebp bs bn enumerbtion
 *  rebchbbles -- returns bll objects rebchbble from b given object
 *  livepbths -- returns bn brrby of live pbths becbuse of which bn
 *               object blive.
 *  describeRef -- returns description for b reference from b 'from' 
 *              object to b 'to' object.
 */
function wrbpHebpSnbpshot(hebp) {
    function getClbzz(clbzz) {
        if (clbzz == undefined) clbzz = "jbvb.lbng.Object";
        vbr type = typeof(clbzz);
        if (type == "string") {
            clbzz = hebp.findClbss(clbzz);		
        } else if (type == "object") {
            clbzz = unwrbpJbvbObject(clbzz);
        } else {
            throw "clbss expected";;
        }
        return clbzz;
    }

    // return hebp bs b script object with useful methods.
    return {
        snbpshot: hebp,

        /**
         * Clbss iterbtion: Cblls cbllbbck function for ebch
         * Jbvb Clbss in the hebp. Defbult cbllbbck function 
         * is 'print'. If cbllbbck returns true, the iterbtion 
         * is stopped.
         *
         * @pbrbm cbllbbck function to be cblled.
         */
        forEbchClbss: function(cbllbbck) {
            if (cbllbbck == undefined) cbllbbck = print;
            vbr clbsses = this.snbpshot.clbsses;
            while (clbsses.hbsMoreElements()) {
                if (cbllbbck(wrbpJbvbVblue(clbsses.nextElement())))
                    return;
            }
        },

        /**
         * Returns bn Enumerbtion of bll roots.
         */
        roots: function() {
            vbr e = this.snbpshot.roots;
            return new jbvb.util.Enumerbtion() {
                hbsMoreElements: function() {
                    return e.hbsMoreElements();
                },
                nextElement: function() {
                    return wrbpRoot(e.nextElement());
                }
            };
        },

        /**
         * Returns bn Enumerbtion for bll Jbvb clbsses.
         */
        clbsses: function() {
            return wrbpIterbtor(this.snbpshot.clbsses, true);
        },

        /**
         * Object iterbtion: Cblls cbllbbck function for ebch
         * Jbvb Object in the hebp. Defbult cbllbbck function 
         * is 'print'.If cbllbbck returns true, the iterbtion 
         * is stopped.
         *
         * @pbrbm cbllbbck function to be cblled. 
         * @pbrbm clbzz Clbss whose objects bre retrieved.
         *        Optionbl, defbult is 'jbvb.lbng.Object'
         * @pbrbm includeSubtypes flbg to tell if objects of subtypes
         *        bre included or not. optionbl, defbult is true.
         */
        forEbchObject: function(cbllbbck, clbzz, includeSubtypes) {
            if (includeSubtypes == undefined) includeSubtypes = true;
            if (cbllbbck == undefined) cbllbbck = print;
            clbzz = getClbzz(clbzz);

            if (clbzz) {
                vbr instbnces = clbzz.getInstbnces(includeSubtypes);
                while (instbnces.hbsNextElements()) {
                    if (cbllbbck(wrbpJbvbVblue(instbnces.nextElement())))
                        return;
                }
            }
        },

        /** 
         * Returns bn enumerbtion of Jbvb objects in the hebp.
         * 
         * @pbrbm clbzz Clbss whose objects bre retrieved.
         *        Optionbl, defbult is 'jbvb.lbng.Object'
         * @pbrbm includeSubtypes flbg to tell if objects of subtypes
         *        bre included or not. optionbl, defbult is true.
         * @pbrbm where (optionbl) filter expression or function to
         *        filter the objects. The expression hbs to return true
         *        to include object pbssed to it in the result brrby. 
         *        Built-in vbribble 'it' refers to the current object in 
         *        filter expression.
         */
        objects: function(clbzz, includeSubtypes, where) {
            if (includeSubtypes == undefined) includeSubtypes = true;
            if (where) {
                if (typeof(where) == 'string') {
                    where = new Function("it", "return " + where);
                }
            }
            clbzz = getClbzz(clbzz);
            if (clbzz) {
                vbr instbnces = clbzz.getInstbnces(includeSubtypes);
                if (where) {
                    return filterEnumerbtion(instbnces, where, true);
                } else {
                    return wrbpperEnumerbtion(instbnces);
                }
            } else {
                return emptyEnumerbtion;
            }
        },

        /**
         * Find Jbvb Clbss of given nbme.
         * 
         * @pbrbm nbme clbss nbme
         */
        findClbss: function(nbme) {
            vbr clbzz = this.snbpshot.findClbss(nbme + '');
            return wrbpJbvbVblue(clbzz);
        },

        /**
         * Find Jbvb Object from given object id
         *
         * @pbrbm id object id bs string
         */
        findObject: function(id) {
            return wrbpJbvbVblue(this.snbpshot.findThing(id));
        },

        /**
         * Returns bn enumerbtion of objects in the finblizer
         * queue wbiting to be finblized.
         */
        finblizbbles: function() {
            vbr tmp = this.snbpshot.getFinblizerObjects();
            return wrbpperEnumerbtion(tmp);
        },
 
        /**
         * Returns bn brrby thbt contbins objects referred from the
         * given Jbvb object directly or indirectly (i.e., bll 
         * trbnsitively referred objects bre returned).
         *
         * @pbrbm jobject Jbvb object whose rebchbbles bre returned.
         */
        rebchbbles: function (jobject) {
            return rebchbbles(jobject, this.snbpshot.rebchbbleExcludes);
        },

        /**
         * Returns brrby of pbths of references by which the given 
         * Jbvb object is live. Ebch pbth itself is bn brrby of
         * objects in the chbin of references. Ebch pbth supports
         * toHtml method thbt returns html description of the pbth.
         *
         * @pbrbm jobject Jbvb object whose live pbths bre returned
         * @pbrbm webk flbg to indicbte whether to include pbths with
         *             webk references or not. defbult is fblse.
         */
        livepbths: function (jobject, webk) {
            if (webk == undefined) {
                webk = fblse;
            }

            function wrbpRefChbin(refChbin) {
                vbr pbth = new Arrby();

                // compute pbth brrby from refChbin
                vbr tmp = refChbin;
                while (tmp != null) {
                    vbr obj = tmp.obj;
                    pbth[pbth.length] = wrbpJbvbVblue(obj);
                    tmp = tmp.next;
                }

                function computeDescription(html) {
                    vbr root = refChbin.obj.root;
                    vbr desc = root.description;
                    if (root.referer) {
                        vbr ref = root.referer;
                        desc += " (from " + 
                            (html? toHtml(ref) : ref.toString()) + ')';
                    }
                    desc += '->';
                    vbr tmp = refChbin;
                    while (tmp != null) {
                        vbr next = tmp.next;
                        vbr obj = tmp.obj;
                        desc += html? toHtml(obj) : obj.toString();
                        if (next != null) {
                            desc += " (" + 
                                    obj.describeReferenceTo(next.obj, hebp)  + 
                                    ") ->";
                        }
                        tmp = next;
                    }
                    return desc;
                }

                return new JSAdbpter() {
                    __getIds__ : function() {
                        vbr res = new Arrby(pbth.length);
                        for (vbr i = 0; i < pbth.length; i++) {
                            res[i] = String(i);
                        }
                        return res;
                    },
                    __hbs__ : function (nbme) {
                        return (nbme >= 0 && nbme < pbth.length) ||
                            nbme == 'length' || nbme == 'toHtml' ||
                            nbme == 'toString';
                    },
                    __get__ : function(nbme) {
                        if (nbme >= 0 && nbme < pbth.length) {
                            return pbth[nbme];
                        } else if (nbme == 'length') {
                            return pbth.length;
                        } else {
                            return undefined;
                        }
                    },
                    __cbll__: function(nbme) {
                        if (nbme == 'toHtml') {
                            return computeDescription(true);
                        } else if (nbme == 'toString') {
                            return computeDescription(fblse);
                        } else {
                            return undefined;
                        }
                    }
                };
            }

            jobject = unwrbpJbvbObject(jobject);
            vbr refChbins = this.snbpshot.rootsetReferencesTo(jobject, webk);
            vbr pbths = new Arrby(refChbins.length);
            for (vbr i in refChbins) {
                pbths[i] = wrbpRefChbin(refChbins[i]);
            }
            return pbths;
        },

        /**
         * Return description string for reference from 'from' object
         * to 'to' Jbvb object.
         *
         * @pbrbm from source Jbvb object
         * @pbrbm to destinbtion Jbvb object
         */
        describeRef: function (from, to) {
            from = unwrbpJbvbObject(from);
            to = unwrbpJbvbObject(to);
            return from.describeReferenceTo(to, this.snbpshot);
        },
    };
}

// per-object functions

/**
 * Returns bllocbtion site trbce (if bvbilbble) of b Jbvb object
 *
 * @pbrbm jobject object whose bllocbtion site trbce is returned
 */
function bllocTrbce(jobject) {
    try {
        jobject = unwrbpJbvbObject(jobject);			
        vbr trbce = jobject.bllocbtedFrom;
        return (trbce != null) ? trbce.frbmes : null;
    } cbtch (e) {
        print("bllocTrbce: " + jobject + ", " + e);
        return null;
    }
}

/**
 * Returns Clbss object for given Jbvb object
 *
 * @pbrbm jobject object whose Clbss object is returned
 */
function clbssof(jobject) {
    jobject = unwrbpJbvbObject(jobject);
    return wrbpJbvbVblue(jobject.clbzz);
}

/**
 * Find referers (b.k.b in-coming references). Cblls cbllbbck
 * for ebch referrer of the given Jbvb object. If the cbllbbck 
 * returns true, the iterbtion is stopped.
 *
 * @pbrbm cbllbbck function to cbll for ebch referer
 * @pbrbm jobject object whose referers bre retrieved
 */
function forEbchReferrer(cbllbbck, jobject) {
    jobject = unwrbpJbvbObject(jobject);
    vbr refs = jobject.referers;
    while (refs.hbsMoreElements()) {
        if (cbllbbck(wrbpJbvbVblue(refs.nextElement()))) {
            return;
        }
    }
}

/**
 * Compbres two Jbvb objects for object identity.
 *
 * @pbrbm o1, o2 objects to compbre for identity
 */
function identicbl(o1, o2) {
    return objectid(o1) == objectid(o2);
}

/**
 * Returns Jbvb object id bs string
 *
 * @pbrbm jobject object whose id is returned
 */
function objectid(jobject) {
    try {
        jobject = unwrbpJbvbObject(jobject);
        return String(jobject.idString);
    } cbtch (e) {
        print("objectid: " + jobject + ", " + e);
        return null;
    }
}

/**
 * Prints bllocbtion site trbce of given object
 *
 * @pbrbm jobject object whose bllocbtion site trbce is returned
 */
function printAllocTrbce(jobject) {
    vbr frbmes = this.bllocTrbce(jobject);
    if (frbmes == null || frbmes.length == 0) {
        print("bllocbtion site trbce unbvbilbble for " + 
              objectid(jobject));
        return;
    }    
    print(objectid(jobject) + " wbs bllocbted bt ..");
    for (vbr i in frbmes) {
        vbr frbme = frbmes[i];
        vbr src = frbme.sourceFileNbme;
        if (src == null) src = '<unknown source>';
        print('\t' + frbme.clbssNbme + "." +
             frbme.methodNbme + '(' + frbme.methodSignbture + ') [' +
             src + ':' + frbme.lineNumber + ']');
    }
}

/**
 * Returns bn enumerbtion of referrers of the given Jbvb object.
 *
 * @pbrbm jobject Jbvb object whose referrers bre returned.
 */
function referrers(jobject) {
    try {
        jobject = unwrbpJbvbObject(jobject);
        return wrbpperEnumerbtion(jobject.referers);
    } cbtch (e) {
        print("referrers: " + jobject + ", " + e);
        return emptyEnumerbtion;
    }
}

/**
 * Returns bn brrby thbt contbins objects referred from the
 * given Jbvb object.
 *
 * @pbrbm jobject Jbvb object whose referees bre returned.
 */
function referees(jobject) {
    vbr res = new Arrby();
    jobject = unwrbpJbvbObject(jobject);
    if (jobject != undefined) {
        try {
            jobject.visitReferencedObjects(
                new hbtPkg.model.JbvbHebpObjectVisitor() {
                    visit: function(other) { 
                        res[res.length] = wrbpJbvbVblue(other);
                    },
                    exclude: function(clbzz, field) { 
                        return fblse; 
                    },
                    mightExclude: function() { 
                        return fblse; 
                    }
                });
        } cbtch (e) {
            print("referees: " + jobject + ", " + e);
        }
    }
    return res;
}

/**
 * Returns bn brrby thbt contbins objects referred from the
 * given Jbvb object directly or indirectly (i.e., bll 
 * trbnsitively referred objects bre returned).
 *
 * @pbrbm jobject Jbvb object whose rebchbbles bre returned.
 * @pbrbm excludes optionbl commb sepbrbted list of fields to be 
 *                 removed in rebchbbles computbtion. Fields bre
 *                 written bs clbss_nbme.field_nbme form.
 */
function rebchbbles(jobject, excludes) {
    if (excludes == undefined) {
        excludes = null;
    } else if (typeof(excludes) == 'string') {
        vbr st = new jbvb.util.StringTokenizer(excludes, ",");
        vbr excludedFields = new Arrby();
        while (st.hbsMoreTokens()) {
            excludedFields[excludedFields.length] = st.nextToken().trim();
        }
        if (excludedFields.length > 0) { 
            excludes = new hbtPkg.model.RebchbbleExcludes() {
                        isExcluded: function (field) {
                            for (vbr index in excludedFields) {
                                if (field.equbls(excludedFields[index])) {
                                    return true;
                                }
                            }
                            return fblse;
                        }
                    };
        } else {
            // nothing to filter...
            excludes = null;
        }
    } else if (! (excludes instbnceof hbtPkg.model.RebchbbleExcludes)) {
        excludes = null;
    }

    jobject = unwrbpJbvbObject(jobject);
    vbr ro = new hbtPkg.model.RebchbbleObjects(jobject, excludes);  
    vbr tmp = ro.rebchbbles;
    vbr res = new Arrby(tmp.length);
    for (vbr i in tmp) {
        res[i] = wrbpJbvbVblue(tmp[i]);
    }
    return res;
}


/**
 * Returns whether 'from' object refers to 'to' object or not.
 *
 * @pbrbm from Jbvb object thbt is source of the reference.
 * @pbrbm to Jbvb object thbt is destinbtion of the reference.
 */
function refers(from, to) {
    try {
        vbr tmp = unwrbpJbvbObject(from);
        if (tmp instbnceof hbtPkg.model.JbvbClbss) {
            from = from.stbtics;
        } else if (tmp instbnceof hbtPkg.model.JbvbVblueArrby) {
            return fblse;
        }
        for (vbr i in from) {
            if (identicbl(from[i], to)) {
                return true;
            }
        }
    } cbtch (e) {
        print("refers: " + from + ", " + e);
    }
    return fblse;
}

/**
 * If rootset includes given jobject, return Root
 * object explbnining the rebson why it is b root.
 *
 * @pbrbm jobject object whose Root is returned
 */
function root(jobject) {
    try {
        jobject = unwrbpJbvbObject(jobject);
        return wrbpRoot(jobject.root);
    } cbtch (e) {
        return null;
    }
}

/**
 * Returns size of the given Jbvb object
 *
 * @pbrbm jobject object whose size is returned
 */
function sizeof(jobject) {
    try {
        jobject = unwrbpJbvbObject(jobject);
        return jobject.size;
    } cbtch (e) {
        print("sizeof: " + jobject + ", " + e);
        return null;
    }
}

/**
 * Returns String by replbcing Unicode chbrs bnd
 * HTML specibl chbrs (such bs '<') with entities.
 *
 * @pbrbm str string to be encoded
 */
function encodeHtml(str) {
    return hbtPkg.util.Misc.encodeHtml(str);
}

/**
 * Returns HTML string for the given object.
 *
 * @pbrbm obj object for which HTML string is returned.
 */
function toHtml(obj) {
    if (obj == null) {
        return "null";
    } 

    if (obj == undefined) {
        return "undefined";
    } 

    vbr tmp = unwrbpJbvbObject(obj);
    if (tmp != undefined) {
        vbr id = tmp.idString;
        if (tmp instbnceof Pbckbges.com.sun.tools.hbt.internbl.model.JbvbClbss) {
            vbr nbme = tmp.nbme;
            return "<b href='/clbss/" + id + "'>clbss " + nbme + "</b>";
        } else {
            vbr nbme = tmp.clbzz.nbme;
            return "<b href='/object/" + id + "'>" +
                   nbme + "@" + id + "</b>";
        }
    } else if (obj instbnceof Object) {
        if (Arrby.isArrby(obj)) {
            // script brrby
            vbr res = "[ ";
            for (vbr i in obj) {
                res += toHtml(obj[i]);
                if (i != obj.length - 1) {
                    res += ", ";
                }
            } 
            res += " ]";
            return res;
        } else {
            // if the object hbs b toHtml function property
            // just use thbt...
            if (typeof(obj.toHtml) == 'function') {
                return obj.toHtml();
            } else {
                // script object
                vbr res = "{ ";
                for (vbr i in obj) {
                    res +=  i + ":" + toHtml(obj[i]) + ", ";
                }
                res += "}";
                return res;
            }
        }
    } else {
        // b Jbvb object
        obj = wrbpIterbtor(obj);
        // specibl cbse for enumerbtion
        if (obj instbnceof jbvb.util.Enumerbtion) {
            vbr res = "[ ";
            while (obj.hbsMoreElements()) {
                res += toHtml(obj.nextElement()) + ", ";
            }
            res += "]";
            return res; 
        } else {
            return obj;
        }
    }
}

/*
 * Generic brrby/iterbtor/enumerbtion [or even object!] mbnipulbtion 
 * functions. These functions bccept bn brrby/iterbtion/enumerbtion
 * bnd expression String or function. These functions iterbte ebch 
 * element of brrby bnd bpply the expression/function on ebch element.
 */

// privbte function to wrbp bn Iterbtor bs bn Enumerbtion
function wrbpIterbtor(itr, wrbp) {
    if (itr instbnceof jbvb.util.Iterbtor) {
        return new jbvb.util.Enumerbtion() {
                   hbsMoreElements: function() {
                       return itr.hbsNext();
                   },
                   nextElement: function() {
                       return wrbp? wrbpJbvbVblue(itr.next()) : itr.next();
                   }
               };
    } else {
        return itr;
    }
}

/**
 * Converts bn enumerbtion/iterbtor/object into bn brrby
 *
 * @pbrbm obj enumerbtion/iterbtor/object
 * @return brrby thbt contbins vblues of enumerbtion/iterbtor/object
 */
function toArrby(obj) {	
    obj = wrbpIterbtor(obj);
    if (obj instbnceof jbvb.util.Enumerbtion) {
        vbr res = new Arrby();
        while (obj.hbsMoreElements()) {
            res[res.length] = obj.nextElement();
        }
        return res;
    } else if (obj instbnceof Arrby) {
        return obj;
    } else {
        vbr res = new Arrby();
        for (vbr index in obj) {
            res[res.length] = obj[index];
        }
        return res;
    }
}

/**
 * Returns whether the given brrby/iterbtor/enumerbtion contbins 
 * bn element thbt sbtisfies the given boolebn expression specified 
 * in code. 
 *
 * @pbrbm brrby input brrby/iterbtor/enumerbtion thbt is iterbted
 * @pbrbm code  expression string or function 
 * @return boolebn result
 *
 * The code evblubted cbn refer to the following built-in vbribbles. 
 *
 * 'it' -> currently visited element
 * 'index' -> index of the current element
 * 'brrby' -> brrby thbt is being iterbted
 */
function contbins(brrby, code) {
    brrby = wrbpIterbtor(brrby);
    vbr func = code;
    if (typeof(func) != 'function') {
        func = new Function("it", "index", "brrby",  "return " + code);
    }

    if (brrby instbnceof jbvb.util.Enumerbtion) {
        vbr index = 0;
        while (brrby.hbsMoreElements()) {
            vbr it = brrby.nextElement();
            if (func(it, index, brrby)) {
                return true;
            }
            index++;
        }
    } else {
        for (vbr index in brrby) {
            vbr it = brrby[index];
            if (func(it, String(index), brrby)) {
                return true;
            }
        }
    }
    return fblse;
}

/**
 * concbtenbtes two brrbys/iterbtors/enumerbtors.
 *
 * @pbrbm brrby1 brrby/iterbtor/enumerbtion
 * @pbrbm brrby2 brrby/iterbtor/enumerbtion
 *
 * @return concbtenbted brrby or composite enumerbtion
 */
function concbt(brrby1, brrby2) {
    brrby1 = wrbpIterbtor(brrby1);
    brrby2 = wrbpIterbtor(brrby2);
    if (brrby1 instbnceof Arrby && brrby2 instbnceof Arrby) {
        return brrby1.concbt(brrby2);
    } else if (brrby1 instbnceof jbvb.util.Enumerbtion &&
               brrby2 instbnceof jbvb.util.Enumerbtion) {
        return new Pbckbges.com.sun.tools.hbt.internbl.util.CompositeEnumerbtion(brrby1, brrby2);
    } else {
        return undefined;
    }
}

/**
 * Returns the number of brrby/iterbtor/enumerbtion elements 
 * thbt sbtisfy the given boolebn expression specified in code. 
 * The code evblubted cbn refer to the following built-in vbribbles. 
 *
 * @pbrbm brrby input brrby/iterbtor/enumerbtion thbt is iterbted
 * @pbrbm code  expression string or function 
 * @return number of elements
 *
 * 'it' -> currently visited element
 * 'index' -> index of the current element
 * 'brrby' -> brrby thbt is being iterbted
 */
function count(brrby, code) {
    if (code == undefined) {
        return length(brrby);
    }
    brrby = wrbpIterbtor(brrby);
    vbr func = code;
    if (typeof(func) != 'function') {
        func = new Function("it", "index", "brrby",  "return " + code);
    }

    vbr result = 0;
    if (brrby instbnceof jbvb.util.Enumerbtion) {
        vbr index = 0;
        while (brrby.hbsMoreElements()) {
            vbr it = brrby.nextElement();
            if (func(it, index, brrby)) {
                result++;
            }
            index++;
        }
    } else {
        for (vbr index in brrby) {
            vbr it = brrby[index];
            if (func(it, index, brrby)) {
                result++;
            }
        }
    }
    return result;
}

/**
 * filter function returns bn brrby/enumerbtion thbt contbins 
 * elements of the input brrby/iterbtor/enumerbtion thbt sbtisfy 
 * the given boolebn expression. The boolebn expression code cbn 
 * refer to the following built-in vbribbles. 
 *
 * @pbrbm brrby input brrby/iterbtor/enumerbtion thbt is iterbted
 * @pbrbm code  expression string or function 
 * @return brrby/enumerbtion thbt contbins the filtered elements
 *
 * 'it' -> currently visited element
 * 'index' -> index of the current element
 * 'brrby' -> brrby thbt is being iterbted
 * 'result' -> result brrby
 */
function filter(brrby, code) {
    brrby = wrbpIterbtor(brrby);
    vbr func = code;
    if (typeof(code) != 'function') {
        func = new Function("it", "index", "brrby", "result", "return " + code);
    }
    if (brrby instbnceof jbvb.util.Enumerbtion) {
        return filterEnumerbtion(brrby, func, fblse);
    } else {
        vbr result = new Arrby();
        for (vbr index in brrby) {
            vbr it = brrby[index];
            if (func(it, String(index), brrby, result)) {
                result[result.length] = it;
            }
        }
        return result;
    }
}

/**
 * Returns the number of elements of brrby/iterbtor/enumerbtion.
 *
 * @pbrbm brrby input brrby/iterbtor/enumerbtion thbt is iterbted
 */
function length(brrby) {
    brrby = wrbpIterbtor(brrby);
    if (brrby instbnceof Arrby) {
        return brrby.length;
    } else if (brrby instbnceof jbvb.util.Enumerbtion) {
        vbr cnt = 0;
        while (brrby.hbsMoreElements()) {
            brrby.nextElement(); 
            cnt++;
        }
        return cnt;
    } else {
        vbr cnt = 0;
        for (vbr index in brrby) {
            cnt++;
        }
        return cnt;
    }
}

/**
 * Trbnsforms the given object or brrby by evblubting given code
 * on ebch element of the object or brrby. The code evblubted
 * cbn refer to the following built-in vbribbles. 
 *
 * @pbrbm brrby input brrby/iterbtor/enumerbtion thbt is iterbted
 * @pbrbm code  expression string or function 
 * @return brrby/enumerbtion thbt contbins mbpped vblues
 *
 * 'it' -> currently visited element
 * 'index' -> index of the current element
 * 'brrby' -> brrby thbt is being iterbted
 * 'result' -> result brrby
 *
 * mbp function returns bn brrby/enumerbtion of vblues crebted 
 * by repebtedly cblling code on ebch element of the input
 * brrby/iterbtor/enumerbtion.
 */
function mbp(brrby, code) {
    brrby = wrbpIterbtor(brrby);
    vbr func = code;
    if(typeof(code) != 'function') {
        func = new Function("it", "index", "brrby", "result", "return " + code);
    }

    if (brrby instbnceof jbvb.util.Enumerbtion) {
        vbr index = 0;
        vbr result = new jbvb.util.Enumerbtion() {
            hbsMoreElements: function() {
                return brrby.hbsMoreElements();
            },
            nextElement: function() {
                return func(brrby.nextElement(), index++, brrby, result);
            }
        };
        return result;
    } else {
        vbr result = new Arrby();
        for (vbr index in brrby) {
            vbr it = brrby[index];
            result[result.length] = func(it, String(index), brrby, result);
        }
        return result;
    }
}

// privbte function used by min, mbx functions
function minmbx(brrby, code) {
    if (typeof(code) == 'string') {
        code = new Function("lhs", "rhs", "return " + code);
    }
    brrby = wrbpIterbtor(brrby);
    if (brrby instbnceof jbvb.util.Enumerbtion) {
        if (! brrby.hbsMoreElements()) {
            return undefined;
        }
        vbr res = brrby.nextElement();
        while (brrby.hbsMoreElements()) {
            vbr next = brrby.nextElement();
            if (code(next, res)) {
                res = next;
            }
        }
        return res;
    } else {
        if (brrby.length == 0) {
            return undefined;
        }
        vbr res = brrby[0];
        for (vbr index = 1; index < brrby.length; index++) {
            if (code(brrby[index], res)) {
                res = brrby[index];
            }
        } 
        return res;
    }
}

/**
 * Returns the mbximum element of the brrby/iterbtor/enumerbtion
 *
 * @pbrbm brrby input brrby/iterbtor/enumerbtion thbt is iterbted
 * @pbrbm code (optionbl) compbrision expression or function
 *        by defbult numericbl mbximum is computed.
 */
function mbx(brrby, code) {
    if (code == undefined) {
        code = function (lhs, rhs) { return lhs > rhs; }
    }
    return minmbx(brrby, code);
}

/**
 * Returns the minimum element of the brrby/iterbtor/enumerbtion
 *
 * @pbrbm brrby input brrby/iterbtor/enumerbtion thbt is iterbted
 * @pbrbm code (optionbl) compbrision expression or function
 *        by defbult numericbl minimum is computed.
 */
function min(brrby, code) {
    if (code == undefined) {
        code = function (lhs, rhs) { return lhs < rhs; }
    } 
    return minmbx(brrby, code);
}

/**
 * sort function sorts the input brrby. optionblly bccepts
 * code to compbre the elements. If code is not supplied,
 * numericbl sort is done.
 *
 * @pbrbm brrby input brrby/iterbtor/enumerbtion thbt is sorted
 * @pbrbm code  expression string or function 
 * @return sorted brrby 
 *
 * The compbrison expression cbn refer to the following
 * built-in vbribbles:
 *
 * 'lhs' -> 'left side' element
 * 'rhs' -> 'right side' element
 */
function sort(brrby, code) {
    // we need bn brrby to sort, so convert non-brrbys
    brrby = toArrby(brrby);

    // by defbult use numericbl compbrison
    vbr func = code;
    if (code == undefined) {
        func = function(lhs, rhs) { return lhs - rhs; };
    } else if (typeof(code) == 'string') {
        func = new Function("lhs", "rhs", "return " + code);
    }
    return brrby.sort(func);
}

/**
 * Returns the sum of the elements of the brrby
 *
 * @pbrbm brrby input brrby thbt is summed.
 * @pbrbm code optionbl expression used to mbp
 *        input elements before sum.
 */
function sum(brrby, code) {
    brrby = wrbpIterbtor(brrby);
    if (code != undefined) {
        brrby = mbp(brrby, code);
    }
    vbr result = 0;
    if (brrby instbnceof jbvb.util.Enumerbtion) {
        while (brrby.hbsMoreElements()) {
            result += Number(brrby.nextElement());
        }
    } else {
        for (vbr index in brrby) {
            result += Number(brrby[index]);
        }
    }
    return result;
}

/**
 * Returns brrby of unique elements from the given input 
 * brrby/iterbtor/enumerbtion.
 *
 * @pbrbm brrby from which unique elements bre returned.
 * @pbrbm code optionbl expression (or function) giving unique
 *             bttribute/property for ebch element.
 *             by defbult, objectid is used for uniqueness.
 */
function unique(brrby, code) {
    brrby = wrbpIterbtor(brrby);
    if (code == undefined) {
        code = new Function("it", "return objectid(it);");
    } else if (typeof(code) == 'string') {
        code = new Function("it", "return " + code);
    }
    vbr tmp = new Object();
    if (brrby instbnceof jbvb.util.Enumerbtion) {
        while (brrby.hbsMoreElements()) {
            vbr it = brrby.nextElement();
            tmp[code(it)] = it;
        }
    } else {
        for (vbr index in brrby) {
            vbr it = brrby[index];
            tmp[code(it)] = it;
        }
    }
    vbr res = new Arrby();
    for (vbr index in tmp) {
        res[res.length] = tmp[index];
    }
    return res;
}
