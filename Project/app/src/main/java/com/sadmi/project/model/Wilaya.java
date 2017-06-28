package com.sadmi.project.model;

/**
 * Created by s on 26/03/17.
 */

public enum Wilaya {
    ADRAR ("ADRAR"),
    AINDEFLA ("AIN DEFLA"),
    AINTEMOUCHENT	("AIN TEMOUCHENT"),
    ALTARF	("AL TARF"),
    ALGER	("ALGER"),
    ANNABA	("ANNABA"),
    BBARRERIDJ ("Bordj Bou Arreridj"),
    BATNA ("BATNA"),
    BECHAR	("BECHAR"),
    BEJAIA	("BEJAIA"),
    BISKRA	("BISKRA"),
    BLIDA	("BLIDA"),
    BOUIRA	("BOUIRA"),
    BOUMERDES	("BOUMERDES"),
    CHLEF	("CHLEF"),
    CONSTANTINE	("CONSTANTINE"),
    DJELFA	("DJELFA"),
    ELBAYADH ("EL BAYADH"),
    ELOUED	("EL OUED"),
    GHARDAIA ("GHARDAIA"),
    GUELMA	("GUELMA"),
    ILLIZI	("ILLIZI"),
    JIJEL	("JIJEL"),
    KHENCHELA ("KHENCHELA"),
    LAGHOUAT ("LAGHOUAT"),
    MASCARA	("MASCARA"),
    MEDEA ("MEDEA"),
    MILA ("MILA"),
    MOSTAGANEM	("MOSTAGANEM"),
    MSILA	("MSILA"),
    NAAMA	("NAAMA"),
    ORAN	("ORAN"),
    OUARGLA	("OUARGLA"),
    OUMELBOUAGHI ("OUM EL BOUAGHI"),
    RELIZANE ("RELIZANE"),
    SAIDA ("SAIDA"),
    SETIF ("SETIF"),
    SIDIBELABBES ("SIDI BELABBES"),
    SKIKDA ("SKIKDA"),
    SOUKAHRAS ("SOUK AHRAS"),
    TAMANGHASSET ("TAMANGHASSET"),
    TEBESSA	("TEBESSA"),
    TIARET	("TIARET"),
    TINDOUF	("TINDOUF"),
    TIPAZA	("TIPAZA"),
    TISSEMSILT	("TISSEMSILT"),
    TIZIOUZOU	("TIZI OUZOU"),
    TLEMCEN	("TLEMCEN");

    private String name="";

    Wilaya(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}